package com.video.testui.service;

import com.video.testui.model.AccountStatus;
import com.video.testui.util.HttpHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckAccountServiceImp implements CheckAccountService{

    final String URL_API = "https://graph.facebook.com/%s/picture?redirect=false";

    @Override
    public AccountStatus isValid(String id) {
        String url = String.format(URL_API, id);
        try {
            String resp = HttpHelper.makeGetRequest(url);
            if (resp.contains("height") && resp.contains("width")){
                return AccountStatus.LIVE;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AccountStatus.DIE;
    }

    public void post(){
        Map<String, String> paramsInput = new HashMap<>();
        paramsInput.put("username", "anc");
        paramsInput.put("password", "hkjdsf");

        Map<String, String> header = new HashMap<>();
        header.put("cookie", "abc");
        try {
            String resp = HttpHelper.makePostRequest("https://www.facebook.com/profile.php?id=100009652028456", paramsInput, header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
