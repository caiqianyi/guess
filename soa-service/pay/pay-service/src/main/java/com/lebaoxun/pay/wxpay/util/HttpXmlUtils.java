package com.lebaoxun.pay.wxpay.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lebaoxun.pay.wxpay.entity.Unifiedorder;


/**
 * post提交xml格式的参数
 */
public class HttpXmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpXmlUtils.class);

    /**
     * 开始post提交参数到接口
     * 并接受返回
     *
     * @param url
     * @param xml
     * @param method
     * @param contentType
     * @return
     */
    public static String xmlHttpProxy(String url, String xml, String method, String contentType) {
        InputStream is = null;
        OutputStreamWriter os = null;

        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "text/xml");
            conn.setRequestProperty("Pragma:", "no-cache");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestMethod("POST");
            os = new OutputStreamWriter(conn.getOutputStream());
            os.write(new String(xml.getBytes(contentType)));
            os.flush();

            //返回值
            is = conn.getInputStream();
            return getContent(is, "utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解析返回的值
     *
     * @param is
     * @param charset
     * @return
     */
    public static String getContent(InputStream is, String charset) {
        String pageString = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            isr = new InputStreamReader(is, charset);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            pageString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb = null;
        }
        return pageString;
    }

    /**
     * 返回处理结果给财付通服务器。
     *
     * @param msg: Success or fail。
     * @throws IOException
     */
    public static void backToWX(String msg, HttpServletResponse response) throws IOException {
        String strHtml = msg;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(strHtml);
        out.flush();
        out.close();

    }

    /**
     * 构造xml参数
     *
     * @param unifiedorder
     * @return
     */
    public static String xmlInfo(Unifiedorder unifiedorder) {
        //构造xml参数的时候，至少又是个必传参数

        if (unifiedorder != null) {
            StringBuffer bf = new StringBuffer();
            bf.append("<xml>");

            bf.append("<appid>");
            bf.append(unifiedorder.getAppid());
            bf.append("</appid>");

            bf.append("<mch_id>");
            bf.append(unifiedorder.getMch_id());
            bf.append("</mch_id>");

            bf.append("<nonce_str>");
            bf.append(unifiedorder.getNonce_str());
            bf.append("</nonce_str>");

            bf.append("<sign>");
            bf.append(unifiedorder.getSign());
            bf.append("</sign>");

            bf.append("<body>");
            bf.append(unifiedorder.getBody());
            bf.append("</body>");
//
//			bf.append("<detail><![CDATA[");
//			bf.append(unifiedorder.getDetail());
//			bf.append("]]></detail>");
//
            bf.append("<attach>");
            bf.append(unifiedorder.getAttach());
            bf.append("</attach>");

            bf.append("<out_trade_no>");
            bf.append(unifiedorder.getOut_trade_no());
            bf.append("</out_trade_no>");

            bf.append("<total_fee>");
            bf.append(unifiedorder.getTotal_fee());
            bf.append("</total_fee>");

            bf.append("<spbill_create_ip>");
            bf.append(unifiedorder.getSpbill_create_ip());
            bf.append("</spbill_create_ip>");

            String openId = unifiedorder.getOpenid();
            if (null != openId && !"".equals(openId)) {
                bf.append("<openid>");
                bf.append(openId);
                bf.append("</openid>");
            }

//			bf.append("<detail>");
//			bf.append(unifiedorder.getDetail());
//			bf.append("</detail>");
//
//			bf.append("<time_expire><![CDATA[");
//			bf.append(unifiedorder.getTime_expire());
//			bf.append("]]></time_expire>");

            bf.append("<notify_url>");
            bf.append(unifiedorder.getNotify_url());
            bf.append("</notify_url>");

            bf.append("<trade_type>");
            bf.append(unifiedorder.getTrade_type());
            bf.append("</trade_type>");

            String scene_info = unifiedorder.getScene_info();
            if (null != scene_info && !"".equals(scene_info)) {
                bf.append("<scene_info>");
                bf.append(scene_info);
                bf.append("</scene_info>");
            }

            bf.append("</xml>");
            return bf.toString();
        }

        return "";
    }
    
    public static String queryXmlInfo(Unifiedorder unifiedorder) {
        //构造xml参数的时候，至少又是个必传参数

        if (unifiedorder != null) {
            StringBuffer bf = new StringBuffer();
            bf.append("<xml>");

            bf.append("<appid>");
            bf.append(unifiedorder.getAppid());
            bf.append("</appid>");

            bf.append("<mch_id>");
            bf.append(unifiedorder.getMch_id());
            bf.append("</mch_id>");

            bf.append("<nonce_str>");
            bf.append(unifiedorder.getNonce_str());
            bf.append("</nonce_str>");

            bf.append("<sign>");
            bf.append(unifiedorder.getSign());
            bf.append("</sign>");

            if(StringUtils.isNotBlank(unifiedorder.getOut_trade_no())){
            	bf.append("<out_trade_no>");
            	bf.append(unifiedorder.getOut_trade_no());
            	bf.append("</out_trade_no>");
            }
            if(StringUtils.isNotBlank(unifiedorder.getTransaction_id())){
            	bf.append("<transaction_id>");
            	bf.append(unifiedorder.getTransaction_id());
            	bf.append("</transaction_id>");
            }
            bf.append("</xml>");
            return bf.toString();
        }

        return "";
    }


    /**
     * post请求并得到返回结果
     *
     * @param requestUrl
     * @param requestMethod
     * @param output
     * @return
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String output) {
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);
            if (null != output) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return buffer.toString();
        } catch (Exception ex) {
            logger.error("调用请求失败,异常信息:{},堆栈信息:{}", ex.getMessage(), ex.getStackTrace());
        }

        return "";
    }

    /**
     * post请求并得到返回结果
     *
     * @param requestUrl
     * @param requestMethod
     * @param output
     * @return 字节流
     */
    public static byte[] httpsRequestByQrcode(String requestUrl, String requestMethod, String output) {
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);
            if (null != output) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回字节流
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return outStream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * 开始post提交参数到接口
     * 并接受返回
     *
     * @param url
     * @param json
     * @param method
     * @param contentType
     * @return
     */
    public static String jsonHttpProxy(String reqUrl, String xml, String method, String contentType) {
        try {
            //创建连接
            URL url = new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            System.out.println(xml);
            out.writeBytes(xml);
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
