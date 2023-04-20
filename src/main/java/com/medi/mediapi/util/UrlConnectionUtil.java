package com.medi.mediapi.util;

import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class UrlConnectionUtil {

    @Bean
    public HttpURLConnection UrlConnection(String url) throws IOException {
        //1. URL 객체 생성
        URL targetUrl = new URL(url);

        //2. URL에서 URL Connection 객체 얻기
        HttpURLConnection urlCon = (HttpURLConnection) targetUrl.openConnection();

        //3. 연결 정보 set
        urlCon.setRequestMethod("GET");
        urlCon.setRequestProperty("Content-length", "0");
        urlCon.setUseCaches(false);
        urlCon.setAllowUserInteraction(false);
        urlCon.setConnectTimeout(3000);
        urlCon.setReadTimeout(3000);
        urlCon.connect();

        return urlCon;
    }

}
