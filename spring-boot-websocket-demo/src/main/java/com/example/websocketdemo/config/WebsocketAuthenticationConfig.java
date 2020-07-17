package com.example.websocketdemo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.websocketdemo.pojos.ChatUser;
import com.example.websocketdemo.repository.ChatUserRepository;
import com.example.websocketdemo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebsocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private AuthService authService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ChatUserRepository chatUserRepository;
  
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                final Map<String, String> headerParams = getHeaderParamsFromMessage(message);
                StompHeaderAccessor accessor =
                    MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    final String accessToken =headerParams.get("accessToken");
                    // No header present
                    if (accessToken == null || accessToken.length() == 0 ){
                        throw new IllegalArgumentException("No accessToken provided");
                    }
                    final String username = authService.getUsername(accessToken);
                    // invalid username returned by authorization server
                    if (username == null || username.length() == 0){
                        throw new IllegalArgumentException("No accessToken provided");
                    }
                    final String name = headerParams.get("displayName") == null ? "anoynomous" : headerParams.get("displayName");
                    final ChatUser user= new ChatUser();
                    user.setId(UUID.randomUUID().toString().replaceAll("-",""));
                    user.setDisplayName(name);
                    chatUserRepository.add(user);

                    // log.info("###username: {}", username);
                    // log.info("###headers: {}", message.getHeaders().getId());
                    Collection<? extends GrantedAuthority> authorities = 
                        Arrays
                        .asList("CHAT")
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth))
                        .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getId(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(token);
                    accessor.setUser(SecurityContextHolder.getContext().getAuthentication());
                }
                return message;
            }
        });
    }

    private Map<String, String> getHeaderParamsFromMessage(Message<?> message) {
        Map<String, String> params = new HashMap<>();
        try {
            final String messageJsonString = mapper.writeValueAsString(message);
            final JSONObject messageObject = new JSONObject(messageJsonString);
            final String accessToken = messageObject
                .getJSONObject("headers")
                .getJSONObject("nativeHeaders")
                .getJSONArray("accessToken")
                .getString(0);
            log.info("###accessToken: {}", accessToken); 
            if (accessToken != null) {
                params.put("accessToken", accessToken);
            }
            final String displayName = messageObject
                .getJSONObject("headers")
                .getJSONObject("nativeHeaders")
                .getJSONArray("displayName")
                .getString(0);

            log.info("###displayName: {}", displayName); 
            if (displayName != null) {
                params.put("displayName", displayName);
            }
            return params;
        } catch(Exception e){
            // e.printStackTrace();
        }
        return params;
    }
}
