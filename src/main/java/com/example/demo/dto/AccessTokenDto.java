package com.example.demo.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    private String client_id;
    private String client_sceret;
    private String code;
    private String redirect_uri;
    private String state;
    public Object giteeProvider;
}
