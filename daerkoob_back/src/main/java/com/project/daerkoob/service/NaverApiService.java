package com.project.daerkoob.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NaverApiService {
    final String baseUrl = "https://openapi.naver.com/v1/search/book.json?query=";

    public String search(String clientId, String secret, String _url) { //일단은 void로
        HttpURLConnection con = null;
        String result = "";
        try {
            URL url = new URL(baseUrl + _url);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", secret);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) result = readBody(con.getInputStream());
            else result = readBody(con.getErrorStream());
        } catch (Exception e) {
            System.out.println("연결 오류 : " + e);
        } finally {
            con.disconnect();
        }
        return result;
    }

    public String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}

