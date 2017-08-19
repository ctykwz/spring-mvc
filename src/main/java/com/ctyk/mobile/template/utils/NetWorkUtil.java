package com.ctyk.mobile.template.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;

/**
 * 一般的http访问
 * Created by wei.yang on 2017/8/19.
 */
public class NetWorkUtil {

    private static final String CHART_SET = "UTF-8";

    private static final int DEFAULT_TIMEOUT = 20000;

    /**
     * 网络请求
     *
     * @param url         url
     * @param contentType content-type
     * @param params      参数
     * @param method      请求方法
     * @return 访问结果
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws IOException              io异常
     * @throws KeyManagementException   keyManager异常
     */
    public static String openUrl(String url, String contentType, JSONObject params, String method) throws
            NoSuchAlgorithmException, IOException, KeyManagementException {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        if ("GET".equalsIgnoreCase(method)) {
            url = url + encodeParams(params);
            httpURLConnection = getUrlConnection(url, method, contentType);
        } else {
            httpURLConnection = getUrlConnection(url, method, contentType);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(encodeParams(params).getBytes(CHART_SET));
        }
        httpURLConnection.connect();
        if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
            inputStream = httpURLConnection.getInputStream();
        } else {
            inputStream = httpURLConnection.getErrorStream();
        }
        String result = getResult(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
        return result;
    }

    /**
     * 访问带有授权的网络请求
     *
     * @param url         url
     * @param contentType content-type
     * @param params      参数
     * @param method      请求方法
     * @param attrs       授权
     * @return 访问结果
     * @throws IOException              访问异常
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws KeyManagementException   keyManager异常
     */
    public static String openUrlWithAttrs(String url, String contentType, JSONObject params, String method, JSONObject attrs) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        if ("GET".equalsIgnoreCase(method)) {
            url = url + encodeParams(params);
            httpURLConnection = getUrlConnectionWithAttr(url, method, contentType, attrs);
        } else {
            httpURLConnection = getUrlConnectionWithAttr(url, method, contentType, attrs);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(encodeParams(params).getBytes(CHART_SET));
        }
        httpURLConnection.connect();
        if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
            inputStream = httpURLConnection.getInputStream();
        } else {
            inputStream = httpURLConnection.getErrorStream();
        }
        String result = getResult(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
        return result;
    }

    /**
     * 获取connection
     *
     * @param url         url
     * @param method      请求方法
     * @param contentType content-type
     * @return HttpUrlConnection
     * @throws IOException              io异常
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws KeyManagementException   keyManager异常
     */
    private static HttpURLConnection getUrlConnection(String url, String method, String contentType) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        URL requestUrl = new URL(url);
        HttpURLConnection urlConnection;
        if ("https".equalsIgnoreCase(requestUrl.getProtocol())) {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            HttpsURLConnection connection = (HttpsURLConnection) requestUrl.openConnection();
            connection.setSSLSocketFactory(context.getSocketFactory());
            connection.setHostnameVerifier((s, sslSession) -> true);
            urlConnection = connection;
        } else {
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
        }
        urlConnection.setRequestMethod(method.toUpperCase());
        if ("POST".equalsIgnoreCase(method)) {
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
        }
        urlConnection.setRequestProperty("Content-Type", contentType);
        urlConnection.setConnectTimeout(DEFAULT_TIMEOUT);
        urlConnection.setReadTimeout(DEFAULT_TIMEOUT);
        return urlConnection;
    }

    /**
     * 获取带有授权的连接
     *
     * @param url         url
     * @param method      方法
     * @param contentType content-type
     * @param attrs       参数
     * @return HttpUrlConnection
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws IOException              io异常
     * @throws KeyManagementException   keyManager异常
     */
    private static HttpURLConnection getUrlConnectionWithAttr(String url, String method, String contentType, JSONObject attrs)
            throws NoSuchAlgorithmException, IOException, KeyManagementException {
        HttpURLConnection connection = getUrlConnection(url, method, contentType);
        Set<String> keys = attrs.keySet();
        for (String key : keys) {
            String value = attrs.getString(key);
            if (!Strings.isNullOrEmpty(value)) {
                connection.setRequestProperty(key, value);
            }
        }
        return connection;
    }

    /**
     * 参数编码
     *
     * @param params 参数
     * @return 编码后的参数
     * @throws UnsupportedEncodingException 编码方式不支持
     */
    private static String encodeParams(JSONObject params) throws UnsupportedEncodingException {
        if (params == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        boolean firstParam = true;
        Set<String> keys = params.keySet();
        for (String key : keys) {
            if (firstParam) {
                builder.append("?");
                firstParam = false;
            } else {
                builder.append("&");
            }
            String value = params.getString(key);
            builder.append(URLEncoder.encode(key, CHART_SET))
                    .append(URLEncoder.encode("=", CHART_SET))
                    .append(URLEncoder.encode(value, CHART_SET));
        }
        return builder.toString();
    }

    /**
     * 获取访问结果
     *
     * @param inputStream 输入流
     * @return 字符串类结果
     * @throws IOException 访问出现异常
     */
    private static String getResult(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            builder.append(new String(buffer, 0, len, CHART_SET));
        }
        return builder.toString();
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}
