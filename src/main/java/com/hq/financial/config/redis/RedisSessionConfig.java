package com.hq.financial.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @program: financial
 * @description: 共享session
 * @author: Mr.Huang
 * @create: 2019-01-07 14:48
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class RedisSessionConfig {
}
