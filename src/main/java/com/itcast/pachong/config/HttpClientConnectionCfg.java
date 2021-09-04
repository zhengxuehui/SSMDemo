package com.itcast.pachong.config;


import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConnectionCfg {

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
          //设置连接管理器
        PoolingHttpClientConnectionManager cm =new PoolingHttpClientConnectionManager();
        //设置连接管理器的最大连接数
        cm.setMaxTotal(200);
        // 设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(20);


        return cm;
    }
}
