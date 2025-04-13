package com.kuokuor.demo.config;

import com.kuokuor.demo.handler.ProcessWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 注册WebSocket处理器
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 支持跨域
        registry.addHandler(processWebSocketHandler(), "/process")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler processWebSocketHandler() {
        return new ProcessWebSocketHandler();
    }

}
