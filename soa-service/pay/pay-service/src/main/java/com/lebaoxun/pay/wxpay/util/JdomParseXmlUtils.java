package com.lebaoxun.pay.wxpay.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.ClientProtocolException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.lebaoxun.pay.wxpay.entity.QRCodeEntity;
import com.lebaoxun.pay.wxpay.entity.UnifiedorderResult;
import com.lebaoxun.pay.wxpay.entity.WXPayResult;

/**
 * 微信解析xml：带有CDATA格式的
 */
public class JdomParseXmlUtils {
	
	/**
	 * 1、统一下单获取微信返回
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static UnifiedorderResult getUnifiedorderResult(String xml){
		UnifiedorderResult unifieorderResult = new UnifiedorderResult();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if(list!=null&&list.size()>0){
				for (Element element : list) {
					/*
					 * <xml>
						   <return_code><![CDATA[SUCCESS]]></return_code>
						   <return_msg><![CDATA[OK]]></return_msg>
						   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
						   <mch_id><![CDATA[10000100]]></mch_id>
						   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
						   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
						   <result_code><![CDATA[SUCCESS]]></result_code>
						   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
						   <trade_type><![CDATA[JSAPI]]></trade_type>
						</xml>
					 */
					//System.out.println("key是："+element.getName()+"，值是："+element.getText());
					
					if("return_code".equals(element.getName())){
						unifieorderResult.setReturn_code(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						unifieorderResult.setReturn_msg(element.getText());
					}
					
					if("appid".equals(element.getName())){
						unifieorderResult.setAppid(element.getText());
					}
					
					
					if("mch_id".equals(element.getName())){
						unifieorderResult.setMch_id(element.getText());
					}
					
					if("nonce_str".equals(element.getName())){
						unifieorderResult.setNonce_str(element.getText());
					}
					
					if("sign".equals(element.getName())){
						unifieorderResult.setSign(element.getText());
					}
					
					if("result_code".equals(element.getName())){
						unifieorderResult.setResult_code(element.getText());
					}
					
					if("prepay_id".equals(element.getName())){
						unifieorderResult.setPrepay_id(element.getText());
					}
					
					if("trade_type".equals(element.getName())){
						unifieorderResult.setTrade_type(element.getText());
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return unifieorderResult;
	}
	
	
	/**
	 * 2、微信回调后参数解析
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static WXPayResult getWXPayResult(String xml){
		WXPayResult wXPayResult = new WXPayResult();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if(list!=null&&list.size()>0){
				for (Element element : list) {
					if("return_code".equals(element.getName())){
						wXPayResult.setReturn_code(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("appid".equals(element.getName())){
						wXPayResult.setAppid(element.getText());
					}
					
					if("mch_id".equals(element.getName())){
						wXPayResult.setMch_id(element.getText());
					}
					
					if("nonce_str".equals(element.getName())){
						wXPayResult.setNonce_str(element.getText());
					}
					
					if("sign".equals(element.getName())){
						wXPayResult.setSign(element.getText());
					}
					
					if("result_code".equals(element.getName())){
						wXPayResult.setResult_code(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("device_info".equals(element.getName())){
						wXPayResult.setDevice_info(element.getText());
					}
					
					if("err_code".equals(element.getName())){
						wXPayResult.setErr_code(element.getText());
					}
					
					if("err_code_des".equals(element.getName())){
						wXPayResult.setErr_code_des(element.getText());
					}
					
					if("openid".equals(element.getName())){
						wXPayResult.setOpenid(element.getText());
					}
					
					if("is_subscribe".equals(element.getName())){
						wXPayResult.setIs_subscribe(element.getText());
					}
					
					if("trade_type".equals(element.getName())){
						wXPayResult.setTrade_type(element.getText());
					}
					
					if("bank_type".equals(element.getName())){
						wXPayResult.setBank_type(element.getText());
					}
					
					if("total_fee".equals(element.getName())){
						wXPayResult.setTotal_fee(Integer.parseInt(element.getText()));
					}
					
					if("fee_type".equals(element.getName())){
						wXPayResult.setFee_type(element.getText());
					}
					
					if("cash_fee".equals(element.getName())){
						wXPayResult.setCash_fee(Integer.parseInt(element.getText()));
					}
					
					if("cash_fee_type".equals(element.getName())){
						wXPayResult.setCash_fee_type(element.getText());
					}					
					
					if("coupon_fee".equals(element.getName())){
						wXPayResult.setCoupon_fee(Integer.parseInt(element.getText()));
					}
					
					if("coupon_count".equals(element.getName())){
						wXPayResult.setCoupon_count(Integer.parseInt(element.getText()));
					}
					
					if("coupon_id_$n".equals(element.getName())){
						wXPayResult.setCoupon_id_0(element.getText());
					}
					if("coupon_fee_$n".equals(element.getName())){
						wXPayResult.setCoupon_fee_0(Integer.parseInt(element.getText()));
					}
					
					if("transaction_id".equals(element.getName())){
						wXPayResult.setTransaction_id(element.getText());
					}
					
					if("out_trade_no".equals(element.getName())){
						wXPayResult.setOut_trade_no(element.getText());
					}
					
					if("attach".equals(element.getName())){
						wXPayResult.setAttach(element.getText());
					}
					if("time_end".equals(element.getName())){
						wXPayResult.setTime_end(element.getText());
					}
					
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return wXPayResult;
	}

	@SuppressWarnings("unchecked")
	public static SortedMap<Object,Object> getWXPayResultForMap(String xml){
		SortedMap<Object,Object> wXPayResult = new TreeMap<Object,Object>();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if(list!=null&&list.size()>0){
				for (Element element : list) {
					wXPayResult.put(element.getName(), element.getText());
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return wXPayResult;
	}
	/**
	 * 微信公众号 推送事件
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static QRCodeEntity enventInfo(String xml){
		QRCodeEntity qRCodeEntity = new QRCodeEntity();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();
            
			if(list!=null&&list.size()>0){
				for (Element element : list) {
					if("ToUserName".equals(element.getName())){
						qRCodeEntity.setToUserName(element.getText());
					}else if("FromUserName".equals(element.getName())){
						qRCodeEntity.setFromUserName(element.getText());
					}else if("CreateTime".equals(element.getName())){
						qRCodeEntity.setCreateTime(element.getText());
					}else if("MsgType".equals(element.getName())){
						qRCodeEntity.setMsgType(element.getText());
					}else if("Event".equals(element.getName())){
						qRCodeEntity.setEvent(element.getText());
					}else if("EventKey".equals(element.getName())){
						qRCodeEntity.setEventKey(element.getText());
					}else if("KfAccount".equals(element.getName())){
						qRCodeEntity.setKfAccount(element.getText());
					}else if("FromKfAccount".equals(element.getName())){
						qRCodeEntity.setFromKfAccount(element.getText());
					}else if("ToKfAccount".equals(element.getName())){
						qRCodeEntity.setToKfAccount(element.getText());
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return qRCodeEntity;
	}
	/**
	   * 上传媒体文件
	   * @param accessToken 接口访问凭证
	   * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file)
	   * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息
	   * @param mediaFileUrl 媒体文件的url
	   * 上传的媒体文件限制
	  * 图片（image）:1MB，支持JPG格式
	  * 语音（voice）：2MB，播放长度不超过60s，支持AMR格式
	  * 视频（video）：10MB，支持MP4格式
	  * 普通文件（file）：10MB
	   * */
	  public static String uploadMedia(String uploadMediaUrl, String mediaFileUrl, String fileName) {
	    // 定义数据分隔符
	    String boundary = "---"
	    		+ ""
	    		+ "---------7da2e536604c8";
	    StringBuffer buffer = new StringBuffer();
	    try {
	      URL uploadUrl = new URL(uploadMediaUrl);
	      HttpsURLConnection uploadConn = (HttpsURLConnection) uploadUrl.openConnection();
	      uploadConn.setDoOutput(true);
	      uploadConn.setDoInput(true);
	      uploadConn.setRequestMethod("POST");
	      // 设置请求头Content-Type
	      uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	      // 获取媒体文件上传的输出流（往微信服务器写数据）
	      OutputStream outputStream = uploadConn.getOutputStream();

	      URL mediaUrl = new URL(mediaFileUrl);
	      HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
	      meidaConn.setDoOutput(true);
	      meidaConn.setRequestMethod("GET");

	      // 从请求头中获取内容类型
	      String contentType = meidaConn.getHeaderField("Content-Type");
	      System.out.println(contentType);
	      BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
	      // 根据内容类型判断文件扩展名
	      //String fileExt = WeixinUtil.getFileEndWitsh(contentType);
	      // 请求体开始
	      //outputStream.write(("--" + boundary + "\r\n").getBytes());Content-Type:application/octet-stream\r\n\r\n
	      outputStream.write(("Content-Disposition: form-data; id=\"media\"; name=\"media\";filelength=\""+bis.available()+"\"; filename=\"\""+fileName+";\r\n").getBytes());
	      outputStream.write(("content-type:"+contentType+"\r\n").getBytes());

	      // 获取媒体文件的输入流（读取文件）
	      
	      byte[] buf = new byte[8096];
	      int size = 0;
	      while ((size = bis.read(buf)) != -1) {
	        // 将媒体文件写到输出流（往微信服务器写数据）
	        outputStream.write(buf, 0, size);
	      }
	      // 请求体结束
	      //outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
	      outputStream.close();
	      bis.close();
	      meidaConn.disconnect();

	      // 获取媒体文件上传的输入流（从微信服务器读数据）
	      InputStream inputStream = uploadConn.getInputStream();
	      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	     
	      String str = null;
	      while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	      }
	      bufferedReader.close();
	      inputStreamReader.close();
	      // 释放资源
	      inputStream.close();
	      inputStream = null;
	      uploadConn.disconnect();

	      // 使用JSON-lib解析返回结果
	      //JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
	      // 测试打印结果
	      System.out.println("打印测试结果"+buffer.toString());
	     
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	      String error = String.format("上传媒体文件失败：%s", e);
	      System.out.println(error);
	    }
	    return buffer.toString();
	  }

	/**
	 * 上传微信视频专用
	 * 
	 * @param url
	 * @param filePath
	 * @param title
	 * @param introduction
	 * @return
	 */
	public static String postFile(String url, String filePath, String title,
			String introduction) {
		File file = new File(filePath);
		if (!file.exists())
			return null;
		String result = null;
		try {
			URL url1 = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) url1.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			conn.setRequestProperty("Cache-Control", "max-age=0");
			String boundary = "-----------------------------"
					+ System.currentTimeMillis();
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);

			OutputStream output = conn.getOutputStream();
			output.write(("--" + boundary + "\r\n").getBytes());
			// output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n",file.getName()).getBytes());
			output.write(String
					.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"; filelength=\"%s\"\r\n",
							file.getName(), file.length()).getBytes());
			output.write("Content-Type: image/jpeg \r\n\r\n".getBytes());
			byte[] data = new byte[1024];
			int len = 0;
			FileInputStream input = new FileInputStream(file);
			while ((len = input.read(data)) > -1) {
				output.write(data, 0, len);
			}
			/*output.write(("--" + boundary + "\r\n").getBytes());
			output.write("Content-Disposition: form-data; name=\"media\";\r\n\r\n"
					.getBytes());*/
			/*output.write(String.format(
					"{\"title\":\"%s\", \"introduction\":\"%s\"}", title,
					introduction).getBytes());*/
			output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
			output.flush();
			output.close();
			input.close();
			InputStream resp = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((len = resp.read(data)) > -1)
				sb.append(new String(data, 0, len, "utf-8"));
			resp.close();
			result = sb.toString();
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param fileUrl
	 * @return boolean true 表示文件存在 false 表示文件不存在
	 */
	public static boolean fileExists(String fileUrl) {
		File file = new File(fileUrl);
		return file.exists();
	}
}
