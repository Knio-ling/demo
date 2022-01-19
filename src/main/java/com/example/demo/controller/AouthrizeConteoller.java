package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.giteeuser;
import com.example.demo.provider.GiteeProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AouthrizeConteoller {

    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${gitee.client.id}")
    private String clientId;
    @Value("${gitee.client.secret}")
    private String clientSecret;
    @Value("${gitee.redirect.url}")
    private String redirectUrl;
    
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_sceret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUrl);
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        giteeuser user = giteeProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
