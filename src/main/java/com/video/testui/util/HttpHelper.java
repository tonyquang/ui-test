package com.video.testui.util;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class HttpHelper {
    public static String makePostRequest(String url, Map<String, String> paramsInput,
                                         Map<String, String> headersInput) throws IOException {

//        HttpHost proxy = new HttpHost("proxy.com", 80, "http");
//        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//        HttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
        HttpClient httpclient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy()).build();
        HttpPost httppost = new HttpPost(url);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(paramsInput.size());
        paramsInput.forEach((k, v) -> params.add(new BasicNameValuePair(k, v)));

        headersInput.forEach(httppost::addHeader);

        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();


        return IOUtils.toString(entity.getContent());
    }

    public static String makeGetRequest(String url) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //Execute and get the response.
        HttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        return IOUtils.toString(entity.getContent());
    }
}
