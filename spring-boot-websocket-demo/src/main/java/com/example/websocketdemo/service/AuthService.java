package com.example.websocketdemo.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    @Value("${service.userinfo.url}")
    private String userInfoUrl;

    private static RestTemplate client = new RestTemplate();

    public String getUsername(final String accessToken) {
        log.info("UserInfoService#getUsername");
        if (accessToken == null || accessToken.length() ==0)
            return null;
        client.getInterceptors().add((request, body, execution)->{
            request.getHeaders().set("Authorization", "Bearer " + accessToken);
            return execution.execute(request, body);
        });

        String json = client.getForObject(userInfoUrl, String.class);
        // log.info("###userinfo json : {}", json);

        JSONObject userinfo = new JSONObject(json);
        final String username = userinfo.getString("username");
        // log.info("###username : {}", username);

        if (username == null || username.length() == 0)
            return null;
        return username; 
    } 
}
