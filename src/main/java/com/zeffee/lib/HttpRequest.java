package com.zeffee.lib;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by zeffee on 2017/6/17.
 */
public class HttpRequest {
    public static String get(String url) {
        try {
            return sendHttpRequest(HttpMethod.GET, url);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String post(String url, String params) {
        try {
            return sendHttpRequest(HttpMethod.POST, url, params);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static String sendHttpRequest(HttpMethod httpMethod, String url) throws Exception {
        return getHttpResponse(getClientHttpRequest(httpMethod, url));
    }

    private static String sendHttpRequest(HttpMethod httpMethod, String url, String params) throws Exception {
        ClientHttpRequest clientHttpRequest = getClientHttpRequest(httpMethod, url);
        setRequestParam(clientHttpRequest, params);
        return getHttpResponse(clientHttpRequest);
    }

    private static String getHttpResponse(ClientHttpRequest chr) throws Exception {
        ClientHttpResponse res = chr.execute();
        InputStream is = res.getBody(); //获得返回数据,注意这里是个流
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private static ClientHttpRequest getClientHttpRequest(HttpMethod httpMethod, String url) throws Exception {
        URI uri = new URI(url);
        SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
        ClientHttpRequest chr = schr.createRequest(uri, httpMethod);
        return chr;
    }

    private static void setRequestParam(ClientHttpRequest chr, String params) throws Exception {
        chr.getBody().write(params.getBytes());
    }
}
