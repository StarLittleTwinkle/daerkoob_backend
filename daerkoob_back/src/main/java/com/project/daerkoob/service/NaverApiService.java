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
    final String baseUrl = "https://openapi.naver.com/v1/search/book.json?query="; //이 baseurl이 정말 중요하다 이것으로 어떤형태로 반환되는지 , 어떠한 것을 얻는지가 결정되기 때문에 정말 중요하다.

    public String search(String clientId, String secret, String _url) { //일단은 void로
        HttpURLConnection con = null; //일단 naver api서버와 연결해서 request를 보내게 될 객체를 선언해놓음
        String result = ""; //응답 받을 string 선언
        try {
            URL url = new URL(baseUrl + _url); //baseUrl인 "https://openapi.naver.com/v1/search/book.json?query=" naver api의 기본적인 baseUrl인 내가 query와 각종 설정을 할려고
            //생성한 _url을 보냄 , 이 _url에는 내가 입력한 title&display=?&start=? 이런식의 url이 입력되고 이게 완성되면 "https://openapi.naver.com/v1/search/book.json?query=title&display=?&start=?" 이러한 형태를 띄게 됨
            con = (HttpURLConnection) url.openConnection(); //해당 주소에 연결할 수 있도록 url객체를 선언하였으니 이제 내용을 받아올 수 있도록 url.openConnection을 통해서 연결 된 객체를 얻어옴
            con.setRequestMethod("GET"); //request의 기본적인 설정 , get방식으로 request 를 보내는 것
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", secret); //일반적인 주소로만 접근할 수 없는 것이 clientId, secret 코드가 필요하다 , 그래서 꼭 이렇게 ide에서 가공해서 request를 보내야한다 , 물론 이거는 request를 보내는 척하고
            //정보를 가져오는 것임
            int responseCode = con.getResponseCode(); //이제 getResponseCode()로 여기에서 응답을 얻어올 수 있는지 즉 , 해당 서버가 살아있는지를 확인한다. 그니까 이 서버가 주는 200, 404 이러한 정보를 얻어오는 것
            if (responseCode == HttpURLConnection.HTTP_OK) result = readBody(con.getInputStream()); //ok이면 그냥 해당 url에서 정보를 읽어올 수 있도록 con.getInputStream() 을 readBody method에 넘겨주어서
            //응답을 가져 올 수 있도록 입력스트림을 주는 것, (입력 스트림은 원하는 곳에서 정보를 읽어올 수 있음 ex) FileInputStream input = new FileInputStream("user/jaeyeon/desktop/t.txt") 이러면 t.txt에서 정보를 읽어올 수 있도록
            //interface를 제공함
            else result = readBody(con.getErrorStream()); //만약 연결이 안되면 error를 읽어옴 (error message 를 읽어오는 것 naver api 서버에서 제공하는)
        } catch (Exception e) { //그것 조차 안되면 exception을 반환하면 연결이 오류난 것
            System.out.println("연결 오류 : " + e);
        } finally {
            con.disconnect(); //무조건 연결은 끊어주고
        }
        return result; //얻어온 응답을 string 형태로 반환
    }

    public boolean checkLink(String _url){ //link가 404 not found error 날 때 처리
        HttpURLConnection con = null;
        String result = "";
        try {
            URL url = new URL(_url);
            con = (HttpURLConnection) url.openConnection();
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){ result = "possible";}
            else {result = "impossible";}
        } catch (Exception e) {
            System.out.println("연결 오류 : " + e + _url);
        }
         finally {
            con.disconnect();
        }
        if (result.equals("possible")){
            return true;
        }
        else{
            return false;
        }
    }

    public String readBody(InputStream body) { //이건 그냥 HttpURLConnection 객체를 통해서 request를 마치 웹사이트인 것 처럼 요청을 보내서 그로부터 얻은 응답을 읽어서 반환하는 method임
//         InputStreamReader streamReader = new InputStreamReader(body);  //받아온 con.getInputStream()을 문자열로 읽어올 수 있도록 InputStreamReader로 객체를 생성, 물론 받아온 inputStream에 연결해서, fileInputStream과 흡사하다.
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(body))) { //try with resource방법으로 무조건 close가 되도록 관리해주고 있다 , 원래 bufferedReader로 읽어줘야지 더 빠르기 때문에 한단계를 더 거쳐준다.
            //솔직히 BufferedReader lineReader = new BufferedReader(new InputStreamReader(body)) 이런식으로 선언해주어도 됨
            StringBuilder responseBody = new StringBuilder(); //builder로 받아온 응답 즉 con.getInputStream에서 얻어온 응답을 builder로 가공하는 것
            String line;
            while ((line = lineReader.readLine()) != null) { //받아온 응답의 끝을 만날 때까지 반복하면서
                responseBody.append(line); //string builder에 append함
            }
            return responseBody.toString(); //그러고서 마지막에 다 string builder로 con.getInputStream을 다 읽어 왔으니 이것을 return 해준다.
        } catch (IOException e) { //중간에 IOException이 발생하면 상위 method에 exception을 던져줌
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}

