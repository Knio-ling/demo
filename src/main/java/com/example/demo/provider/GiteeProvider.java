package com.example.demo.provider;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.giteeuser;

import org.springframework.stereotype.Component;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GiteeProvider {
  public String getAccessToken(AccessTokenDto accessTokenDto){
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    
    RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), mediaType);
    Request request = new Request.Builder()
        .url("https://gitee.com/oauth/token?grant_type=authorization_code&code=" + accessTokenDto.getCode() + "&client_id=" + accessTokenDto.getClient_id() + "&redirect_uri=" +accessTokenDto.getRedirect_uri() + "&client_secret=" + accessTokenDto.getClient_sceret())
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      String string = response.body().string();
      JSONObject jsonObject = JSONObject.parseObject(string);
      String token = jsonObject.getString("access_token");
      return token;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  public giteeuser getUser(String accessToken){
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
      .url("https://gitee.com/api/v5/user")
      .header("Authorization", "token " + accessToken)
      .build();
    try{
      Response response = client.newCall(request).execute();
      String string = response.body().string();
      giteeuser giteeUser = JSON.parseObject(string, giteeuser.class);
      return giteeUser;
    }catch(IOException e){}
    return null;
  }
}
