package com.caiqianyi.commons.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @comment 提供http访问
 * @author xhl
 * @version V1.0
 * @date 2015年8月7日  下午4:20:59
 */
public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);  
    private static RequestConfig requestConfig;

    private static String encoding = "UTF-8";
    static CloseableHttpClient httpClient;

    static {
        //服务器响应超时:读取超时
        Integer socketTimeout = 60 * 1000;
        //服务器请求超时:连接超时
        Integer connectTimeout = 5 * 1000;
        Integer connectionRequestTimeout = 3 * 1000;
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(20);
        cm.setMaxTotal(200);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
    }

    /**
     * @param url      请求url
     * @param postData POST请求时form表单封装的数据 没有时传null
     * @return response返回的文本数据
     * @throws Exception
     */
    public static String doPostRequest(String url, Map<String, Object> postData) throws Exception {

        return doPostRequest(url, postData, null);
    }

    /**
     * @param url      请求url
     * @param postData POST请求时form表单封装的数据 没有时传null
     * @param header   头信息,没有时传null
     * @return response返回的文本数据
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static String doPostRequest(String url, Map<String, Object> postData, Map header) throws IOException {
        HttpResponse response = null;
        try {

            //Fix by meng
            if (null == postData) {
                postData = new HashMap<String, Object>();
            }
            //post参数传递
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            for (Map.Entry<String, Object> entry : postData.entrySet()) {
                if (null != entry.getValue()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            // 目标地址
            HttpPost httpPost = new HttpPost(url);

            //httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(params, encoding)); // 设置参数给Post

            // 得到返回的response.
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return getResult(entity);
        } catch (Exception e) {
            logger.info("--------HttpClient请求失败-----");
            e.printStackTrace();
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity()); //会自动释放连接
            }
        }
        return null;
    }

    /**
     * @param url 请求url
     * @return response返回的文本数据
     * @throws Exception
     */
    public static String doGetRequest(String url) throws Exception {
        return doGetRequest(url, null);
    }

    /**
     * @param url    请求url
     * @param header 头信息,没有时传null
     * @return response返回的文本数据
     * @throws Exception
     */
    public static String doGetRequest(String url, Map<String,Object> header) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        // 得到返回的response.
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return getResult(entity);
    }

    /**
     * @param url 请求url
     * @return response返回的文本数据
     * @throws Exception
     */
    public static HttpEntity doGetEntity(String url) throws Exception {
        return doGetEntity(url, null);
    }


    /**
     * @param url    请求url
     * @param header 头信息,没有时传null
     * @return response返回的文本数据
     * @throws Exception
     */
    public static HttpEntity doGetEntity(String url, Map<String,Object> header) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        // 得到返回的response.
        HttpResponse response = httpClient.execute(httpGet);
        return response.getEntity();
    }

    /**
     * 获取远程的图片到指定路劲
     *
     * @param url      请求url
     * @param des      保存目录,不含“//”后缀
     * @param fileName 文件名称
     * @return bo
     */
    public static boolean doGetRemoteImg(String url, String des, String fileName) {
        boolean bo = false;
        InputStream is = null;
        FileOutputStream output = null;
        try {
            is = doGetEntity(url).getContent();
            String fullFileName = des + "//" + fileName;
            File file = new File(fullFileName);
            output = FileUtils.openOutputStream(file);
            IOUtils.copy(is, output);
            bo = true;
        } catch (Exception e) {
            bo = false;
        } finally {
            if (null != output) {
                IOUtils.closeQuietly(output);
            }
            if (null != is) {
                IOUtils.closeQuietly(is);
            }
        }
        return bo;
    }

    /**
     * 返回结果转String
     *
     * @param entity HttpEntity
     * @return String
     */
    private static String getResult(HttpEntity entity) {
        StringBuilder sbResult = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    entity.getContent(), encoding));
            String line;
            while ((line = reader.readLine()) != null) {
                sbResult.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbResult.toString();
    }
    
    @SuppressWarnings("rawtypes")
    public static String doPostRequestWx(String url, Map<String, Object> postData, Map header) throws IOException {
        HttpResponse response = null;
        try {

            //Fix by meng
            if (null == postData) {
                postData = new HashMap<String, Object>();
            }
            //post参数传递
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            for (Map.Entry<String, Object> entry : postData.entrySet()) {
                if (null != entry.getValue()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            // 目标地址
            HttpPost httpPost = new HttpPost(url);

            //httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(params, encoding)); // 设置参数给Post

            // 得到返回的response.
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return getResult(entity);
        } catch (Exception e) {
            logger.info("--------HttpClient请求失败-----");
            e.printStackTrace();
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity()); //会自动释放连接
            }
        }
        return null;
    }
    
    public static void main(String[] args) throws Exception{
//		String url = "http://127.0.0.1:8080/shopping/wd/buyCard";
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("encryptedContent", "53a3b6122750cdb27a5b73d0ec75bb9f45c6a416e87fbaa1a563079a06c0fb4f7c9b58a844eecfb1c9ec397b2b7acc241cdf3f11a3cf3c6c513ba0042d0c7fe50d1ef6b5cfa82cce824dcefbb21edb0b87c913c78ac10a8aee1b7e4c187c4f389cf072e063fa9732b8e4dc6881c8c02e2a31c83d86cdbb9216d1274589a91f4e1d51f79ab06ece40b538337dcb95d878e95ffb2a4509fc086c0a2da7702597948e111a9aaff4be47e2aec42d2bb37df6");
//		map.put("sign", "78B184C69C7541140239DD92909BC3AB");
//		String str = HttpClientUtil.doPostRequest(url, map);//(url, map);
//		System.out.println(str);
		int a;
		a=1/0;
		System.out.println(a);
    }
    
}
