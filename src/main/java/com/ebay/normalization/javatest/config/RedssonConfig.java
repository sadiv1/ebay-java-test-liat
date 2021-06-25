package com.ebay.normalization.javatest.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Configuration
public class RedssonConfig {

    @Value("${redisson.address}")
    private String urls;

    @Bean(name = "redissonClient",destroyMethod = "shutdown")
    public RedissonClient redissonClientSingle() throws IOException {
        RedissonClient redisson = null;
        Config config = new Config();
        config.useSingleServer().setAddress(urls)
                .setTimeout(2000)
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(2)
                .setIdleConnectionTimeout(5000)
                .setDatabase(1);

        redisson = Redisson.create(config);
        return redisson;
    }

}