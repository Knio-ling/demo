package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.giteeuser;
import com.example.demo.provider.GiteeProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AouthrizeConteoller {

    @Autowired
    private GiteeProvider giteeProvider;
    
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("3af92bdef23405b4bf970b5dfbc76ff05d71e65a6a38bacdf2ff561533f8008d");
        accessTokenDto.setClient_sceret("7f3d1e07dee8e8324992ef4659ab50667ca1f3434b5434dc17fbae8fb37484ad");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:80/callback");
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        giteeuser user = giteeProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
