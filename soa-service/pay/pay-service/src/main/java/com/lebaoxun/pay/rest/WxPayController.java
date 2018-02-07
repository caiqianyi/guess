package com.lebaoxun.pay.rest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.pay.wxpay.entity.Unifiedorder;
import com.lebaoxun.pay.wxpay.util.HttpXmlUtils;
import com.lebaoxun.pay.wxpay.util.JdomParseXmlUtils;
import com.lebaoxun.pay.wxpay.util.ParseXMLUtils;
import com.lebaoxun.pay.wxpay.util.RandCharsUtils;
import com.lebaoxun.pay.wxpay.util.WXSignUtils;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

/**
 * 
 * @author 蔡骞毅
 * 2017/12/18
 */
@RestController
public class WxPayController {

	private Logger logger = LoggerFactory.getLogger(WxPayController.class);
	
	@Resource
	private Environment env;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	/**
	 * 微信公众号支付付款
	 * 
	 * @return JsonObject
	 * @throws Exception
	 */
	@RequestMapping(value="/wxpay/payment", method = RequestMethod.POST)
	SuccessMessage payment(@RequestParam("spbill_create_ip")String spbill_create_ip, @RequestParam("orderNo")String orderNo, 
			@RequestParam("descr")String descr, @RequestParam("totalFee")Integer totalFee, 
			@RequestParam("attach")String attach, @RequestParam("account")String account, 
			@RequestParam("openid")String openid) {
		String tradeType = "JSAPI";
		String host = env.getProperty("pay.notify.host");
		String notify_url = host+ "/wxpay/notify/";
		// 参数组
		String appid =  env.getProperty("wechatpay."+account+".appid");
		String mch_id = env.getProperty("wechatpay."+account+".mchid");
		String secret = env.getProperty("wechatpay."+account+".secret");
		
		String nonce_str = RandCharsUtils.getRandomString(16);

		String out_trade_no = orderNo;
		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", descr);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", totalFee);
		parameters.put("spbill_create_ip", spbill_create_ip);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", tradeType);
		parameters.put("attach", attach);
		parameters.put("openid", openid);
		
		String sign = WXSignUtils.createSign("UTF-8", parameters, secret);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setBody(descr);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setAttach(attach);
		unifiedorder.setOpenid(openid);
		unifiedorder.setSign(sign);

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String method = "POST";

		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

		Map<String, Object> retMap = ParseXMLUtils.jdomParseXml(weixinPost);
		String return_code=retMap.get("return_code").toString();
		
		logger.debug("retMap={}",retMap);
		logger.debug("请求统一下单接口返回结果 ==return_code={}",return_code);
		if("FAIL".equals(return_code)){
			throw new I18nMessageException("-1","支付失败");
		}
		//支付结果
		String result_code=retMap.get("result_code").toString();
		logger.debug("请求统一下单接口返回支付结果 ==return_code={}",result_code);
		if("FAIL".equals(result_code)){
			String err_code=retMap.get("err_code").toString();//返回错误码
			String err_code_des=retMap.get("err_code_des").toString();//错误描述信息
			/**
			 * NOTENOUGH 余额不足
			 * ORDERPAID 商户订单已支付
			 * ORDERCLOSED 订单已关闭
			 * OUT_TRADE_NO_USED 商户订单号重复
			 */
			if("NOTENOUGH".equals(err_code)||
					"ORDERCLOSED".equals(err_code)||
					"ORDERPAID".equals(err_code)||
					"OUT_TRADE_NO_USED".equals(err_code)){
				throw new I18nMessageException("-1",err_code_des);
			}
			throw new I18nMessageException("-1","支付失败");
		}
		String prepay_id=retMap.get("prepay_id").toString();
		SortedMap<Object, Object> parameters1 = new TreeMap<Object, Object>();
		parameters1.put("appId", appid);
		long timetoken = System.currentTimeMillis() / 1000;
		parameters1.put("timeStamp", timetoken + "");
		parameters1.put("nonceStr", nonce_str);
		parameters1.put("package", "prepay_id="+prepay_id);
		parameters1.put("signType", "MD5");
		
		String resign = WXSignUtils.createSign("UTF-8", parameters1,secret);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("appId", appid);
		ret.put("timeStamp", timetoken + "");
		ret.put("nonceStr", nonce_str);
		ret.put("package", "prepay_id="+prepay_id);
		ret.put("signType", "MD5");
		ret.put("paySign", resign);
		return new SuccessMessage(ret);
	}
	
	/**
	 * 微信H5付款
	 * @param wapUrl
	 * @param wapName
	 * @param spbill_create_ip
	 * @param orderNo
	 * @param descr
	 * @param totalFee
	 * @param attach
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/wxpay/payment/h5", method = RequestMethod.POST)
	SuccessMessage h5Payment(String wapUrl, String wapName, String spbill_create_ip, String orderNo, 
			String descr, Integer totalFee, 
			String attach, String account) throws Exception {
		
		String tradeType = "MWEB";
		String host = env.getProperty("pay.notify.host");
		String notify_url = host+ "/wxpay/h5/notify/";
		// 参数组
		String appid =  env.getProperty("wechatpay."+account+".appid");
		String mch_id = env.getProperty("wechatpay."+account+".mchid");
		String secret = env.getProperty("wechatpay."+account+".secret");
		
		String nonce_str = RandCharsUtils.getRandomString(16);
		String body = "购买房卡";

		String out_trade_no = orderNo;
		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", body);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", totalFee);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", tradeType);
		parameters.put("spbill_create_ip", spbill_create_ip);
		parameters.put("attach", attach);
		
		Map<String,String> scene_info = new TreeMap<String, String>();
		scene_info.put("type", "Wap");
		scene_info.put("wap_url", wapUrl);
		scene_info.put("wap_name", wapName);
		parameters.put("scene_info", scene_info);
		
		String sign = WXSignUtils.createSign("UTF-8", parameters, secret);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setAttach(attach);

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String method = "POST";

		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

		Map<String, Object> retMap = ParseXMLUtils.jdomParseXml(weixinPost);
		String return_code = retMap.remove("return_code").toString();
		logger.error("请求统一下单接口返回结果 =="+return_code);
		if("FAIL".equals(return_code)){
			throw new I18nMessageException("-1","支付失败");
		}
		//支付结果
		String result_code = retMap.remove("result_code").toString();
		logger.error("请求统一下单接口返回支付结果 =="+result_code);
		if("FAIL".equals(result_code)){
			String err_code=retMap.remove("err_code").toString();//返回错误码
			String err_code_des=retMap.remove("err_code_des").toString();//错误描述信息
			logger.error("请求统一下单接口返回结果 =="+err_code+"错误信息为=="+err_code_des);
			/**
			 * NOTENOUGH 余额不足
			 * ORDERPAID 商户订单已支付
			 * ORDERCLOSED 订单已关闭
			 * OUT_TRADE_NO_USED 商户订单号重复
			 */
			if("NOTENOUGH".equals(err_code)||
					"ORDERCLOSED".equals(err_code)||
					"ORDERPAID".equals(err_code)||
					"OUT_TRADE_NO_USED".equals(err_code)){
				throw new I18nMessageException("-1",err_code_des);
			}
			throw new I18nMessageException("-1","支付失败");
		}
		return new SuccessMessage(retMap);
	}
	
	@RequestMapping(value="/wxpay/payment/qrcode", method = RequestMethod.POST)
	SuccessMessage qrcodePayment(String spbill_create_ip, String orderNo, 
			String descr, Integer totalFee, 
			String attach, String account) throws Exception {
		String tradeType = "pay_wxqrcode";
		String host = env.getProperty("pay.notify.host");
		String notify_url = host+ "/wxpay/notify/";
		// 参数组
		String appid =  env.getProperty("wechatpay."+account+".appid");
		String mch_id = env.getProperty("wechatpay."+account+".mchid");
		String secret = env.getProperty("wechatpay."+account+".secret");
		
		String nonce_str = RandCharsUtils.getRandomString(16);

		String out_trade_no = orderNo;
		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", descr);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", totalFee);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", tradeType);
		parameters.put("spbill_create_ip", spbill_create_ip);
		parameters.put("attach", attach);
		
		String sign = WXSignUtils.createSign("UTF-8", parameters, secret);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(descr);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setAttach(attach);

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String method = "POST";

		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

		Map<String, Object> retMap = ParseXMLUtils.jdomParseXml(weixinPost);
		String return_code=retMap.get("return_code").toString();
		logger.error("请求统一下单接口返回结果 =="+return_code);
		if("FAIL".equals(return_code)){
			throw new I18nMessageException("-1","支付失败");
		}
		//支付结果
		String result_code=retMap.get("result_code").toString();
		logger.error("请求统一下单接口返回支付结果 =="+result_code);
		if("FAIL".equals(result_code)){
			String err_code=retMap.get("err_code").toString();//返回错误码
			String err_code_des=retMap.get("err_code_des").toString();//错误描述信息
			logger.error("请求统一下单接口返回结果 =="+err_code+"错误信息为=="+err_code_des);
			/**
			 * NOTENOUGH 余额不足
			 * ORDERPAID 商户订单已支付
			 * ORDERCLOSED 订单已关闭
			 * OUT_TRADE_NO_USED 商户订单号重复
			 */
			if("NOTENOUGH".equals(err_code)||
					"ORDERCLOSED".equals(err_code)||
					"ORDERPAID".equals(err_code)||
					"OUT_TRADE_NO_USED".equals(err_code)){
				throw new I18nMessageException("-1",err_code_des);
			}
			throw new I18nMessageException("-1","支付失败");
		}
		String prepay_id=retMap.get("prepay_id").toString();
		String code_url=retMap.get("code_url").toString();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("prepay_id", prepay_id);
		ret.put("code_url", code_url);
		return new SuccessMessage(ret);
	}
	
	@RequestMapping(value="/wxpay/notify")
	String notify(@RequestBody String body) {
		return notify("pay_wechat", body);
	}
	
	@RequestMapping(value="/wxpay/h5/notify")
	String h5Notify(@RequestBody String body) {
		return notify("pay_h5", body);
	}
	
	String notify(String paytype, String body) {
		try{
			// 微信异步通知 信息为空
			if (StringUtils.isEmpty(body)) {
				throw new I18nMessageException("-1", "未获取到微信返回的结果");
			}
			SortedMap<Object, Object> wxPayResult = JdomParseXmlUtils
					.getWXPayResultForMap(body);
			if (!"SUCCESS".equalsIgnoreCase((String)wxPayResult.get("return_code"))) {
				throw new I18nMessageException("-1", "交易失败");
			}
			/*SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", wxPayResult.getAppid());
			parameters.put("bank_type", wxPayResult.getBank_type());
			parameters.put("cash_fee", wxPayResult.getCash_fee());
			parameters.put("cash_fee_type", wxPayResult.getCash_fee_type());
			parameters.put("fee_type", wxPayResult.getFee_type());
			parameters.put("is_subscribe", wxPayResult.getIs_subscribe());
			parameters.put("mch_id", wxPayResult.getMch_id());
			parameters.put("nonce_str", wxPayResult.getNonce_str());

			parameters.put("out_trade_no", wxPayResult.getOut_trade_no());
			parameters.put("result_code", wxPayResult.getResult_code());
			parameters.put("return_code", wxPayResult.getReturn_code());
			parameters.put("time_end", wxPayResult.getTime_end());
			parameters.put("total_fee", wxPayResult.getTotal_fee());
			parameters.put("trade_type", wxPayResult.getTrade_type());
			parameters.put("transaction_id", wxPayResult.getTransaction_id());
			parameters.put("attach", wxPayResult.getAttach());
	        parameters.put("openid", wxPayResult.getOpenid());

			if (!StringUtils.isEmpty(wxPayResult.getCoupon_id_0())
					&& !StringUtils.isEmpty(String.valueOf(wxPayResult
							.getCoupon_count()))
					&& !StringUtils.isEmpty(String.valueOf(wxPayResult
							.getCoupon_fee()))
					&& !StringUtils.isEmpty(String.valueOf(wxPayResult
							.getCoupon_fee_0()))) {// 判断是否用代金券
				parameters.put("coupon_id_0", wxPayResult.getCoupon_id_0());
				parameters.put("coupon_count", wxPayResult.getCoupon_count());
				parameters.put("coupon_fee", wxPayResult.getCoupon_fee());
				parameters.put("coupon_fee_0", wxPayResult.getCoupon_fee_0());
			}*/
			String mch_id = (String) wxPayResult.get("mch_id");
			logger.debug("mch_id={}",mch_id);
			String account =  env.getProperty("wechatpay."+mch_id);
			String secret = env.getProperty("wechatpay."+account+".secret");
			
			String out_trade_no = (String)wxPayResult.get("out_trade_no");
			logger.debug("account={},secret={}",account,secret);
			
			String sign = WXSignUtils.createSign("UTF-8", wxPayResult, secret);
			
			// 签名成功
			if (sign.equals(wxPayResult.get("sign"))) {
				Long buyTime = DateUtils.parseDate((String)wxPayResult.get("time_end"), new String[]{"yyyyMMddHHmmss"}).getTime();
				String total_fee = new BigDecimal((String)wxPayResult.get("total_fee")).divide(new BigDecimal("100")).toString();
				logger.debug("total_fee={},time_end={}",total_fee,wxPayResult.get("time_end"));
				sendNotify(out_trade_no, total_fee, (String)wxPayResult.get("transaction_id"), buyTime+"", paytype, mch_id);
				return "success";
			}
			logger.error("[wxpay] notify.error {}","sign failure");
			query(out_trade_no, account, "1");
			return "success";
		}catch(I18nMessageException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "fail";
	}
	
	@RequestMapping(value="/wxpay/query", method = RequestMethod.GET)
	SuccessMessage query(@RequestParam("out_trade_no")String out_trade_no, @RequestParam("account")String account,
			@RequestParam(value="send",required=false)String send) {
		// 参数组
		String appid =  env.getProperty("wechatpay."+account+".appid");
		String mch_id = env.getProperty("wechatpay."+account+".mchid");
		String secret = env.getProperty("wechatpay."+account+".secret");
		
		String nonce_str = RandCharsUtils.getRandomString(16);

		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", out_trade_no);
		
		String sign = WXSignUtils.createSign("UTF-8", parameters, secret);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setSign(sign);

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.queryXmlInfo(unifiedorder);
		String wxUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
		String method = "POST";

		logger.debug("xmlInfo={}",xmlInfo);
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

		Map<String, Object> retMap = ParseXMLUtils.jdomParseXml(weixinPost);
		String return_code=retMap.get("return_code").toString();
		
		logger.debug("retMap={}",retMap);
		logger.debug("请求统一下单接口返回结果 ==return_code={}",return_code);
		if("FAIL".equals(return_code)){
			throw new I18nMessageException("-1","支付失败");
		}
		//支付结果
		String result_code=retMap.get("result_code").toString();
		logger.debug("请求统一下单接口返回支付结果 ==return_code={}",result_code);
		if("FAIL".equals(result_code)){
			String err_code=retMap.get("err_code").toString();//返回错误码
			String err_code_des=retMap.get("err_code_des").toString();//错误描述信息
			/**
			 * NOTENOUGH 余额不足
			 * ORDERPAID 商户订单已支付
			 * ORDERCLOSED 订单已关闭
			 * OUT_TRADE_NO_USED 商户订单号重复
			 */
			if("NOTENOUGH".equals(err_code)||
					"ORDERCLOSED".equals(err_code)||
					"ORDERPAID".equals(err_code)||
					"OUT_TRADE_NO_USED".equals(err_code)){
				throw new I18nMessageException("-1",err_code_des);
			}
			throw new I18nMessageException("-1","支付失败");
		}
		if(StringUtils.isNotBlank(send)){
			String total_fee = retMap.get("total_fee").toString();
			String transaction_id = retMap.get("transaction_id").toString(),
					time_end = retMap.get("time_end").toString();
			try {
				String totalFee = new BigDecimal(Integer.parseInt(total_fee)/100).toString();
				Long buyTime = DateUtils.parseDate(time_end, new String[]{"yyyyMMddHHmmss"}).getTime();
				sendNotify(out_trade_no, totalFee, transaction_id, buyTime+"", "pay_wechat", mch_id);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new SuccessMessage(retMap);
	}
	
	private void sendNotify(String out_trade_no,String total_fee,String rransaction_id,
			String buyTime,String trade_type,String merc_no){
		Map<String,String> message = new HashMap<String,String>();
		message.put("out_trade_no", out_trade_no);
		message.put("total_fee", total_fee);
		message.put("trade_no", rransaction_id);
		message.put("buyTime", ""+buyTime);
		message.put("platform", "wxpay");
		message.put("trade_type", trade_type);
		message.put("merc_no", merc_no);
		logger.info("rabbit|sendContractDirect|message={}",message);
		rabbitmqSender.sendContractDirect("pay.success.queue",
				new Gson().toJson(message));
	}
	
}
