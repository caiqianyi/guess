package com.caiqianyi.pay.rest;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.caiqianyi.pay.zfb.AlipayConfig;
import com.caiqianyi.pay.zfb.AplipayReqest;
import com.caiqianyi.pay.zfb.utils.AlipayNotify;
import com.caiqianyi.pay.zfb.utils.AlipaySubmit;
import com.caiqianyi.soa.amqp.core.DirectRabbitConfig;
import com.caiqianyi.soa.amqp.core.sender.IRabbitmqSender;
import com.google.gson.Gson;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
	
	private Logger logger = LoggerFactory.getLogger(AlipayController.class);
	@Resource
	private AlipaySubmit alipaySubmit;
	
	@Resource
	private AlipayNotify alipayNotify;
	
	@Resource
	private AlipayConfig alipayConfig;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Autowired
	public HttpServletRequest request;
	
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value="/payment", method = RequestMethod.POST)
	SuccessMessage payment(@RequestBody String body){
		logger.debug("alipay payment|body={}",body);
		
		AplipayReqest aplipayReqest = new Gson().fromJson(body, AplipayReqest.class);
		logger.debug("alipay payment|aplipayReqest={}",new Gson().toJson(aplipayReqest));
		return new SuccessMessage(alipaySubmit.buildRequest(aplipayReqest));
	}
	
	@RequestMapping(value="/notify", method = RequestMethod.POST)
	String aliNotify() {
		
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		try{
			//根据合作身份者id来区分那个区服
			String sellerId=new String(request.getParameter("seller_id").getBytes("ISO-8859-1"),"UTF-8");
			//logger.info("异步通知交易状态---"+trade_status);
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			String serverCode=alipayConfig.getInfoConfig(sellerId);
			if(!alipayNotify.verify(params,serverCode)){//验证失败
				throw new I18nMessageException("500","验证失败");
			}
			
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if("TRADE_FINISHED".equals(trade_status)){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//如果有做过处理，不执行商户的业务程序
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				throw new I18nMessageException("500","验证失败");
			}
			
			if(!"TRADE_SUCCESS".equals(trade_status)){
				throw new I18nMessageException("500","验证失败");
			}
			
			//判断该笔订单是否在商户网站中已经做过处理
			//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			//如果有做过处理，不执行商户的业务程序
			
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			String outFee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			long gmt_payment = datetimeFormat.parse(new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8")).getTime();
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("out_trade_no", out_trade_no);
			map.put("outFee", outFee);
			map.put("trade_no", trade_no);
			map.put("gmt_payment", gmt_payment+"");
			
			rabbitmqSender.sendContractDirect(DirectRabbitConfig.AlipayNotifyQueue, map);
			
			return "success";
		}catch(Exception e){
			logger.debug("notify:{}",e);
		}
		return "fail";
	}
	
	
}