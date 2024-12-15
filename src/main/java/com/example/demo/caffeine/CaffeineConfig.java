package com.example.demo.caffeine;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * 
 * Dependencies for Caffeine library.
 * 		<dependency>
 *			<groupId>org.springframework.boot</groupId>
 *			<artifactId>spring-boot-starter-cache</artifactId>
 *		</dependency>
 *		<dependency>
 *			<groupId>com.github.ben-manes.caffeine</groupId>
 *			<artifactId>caffeine</artifactId>
 *		</dependency>
 *
 */

@EnableCaching
@Configuration
public class CaffeineConfig {

	@Bean
	public Caffeine caffeineConfigBean() {
	    return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
	}

	@Bean
	public CacheManager cacheManager(Caffeine caffeine) {
		System.out.println("creating cache manager");
	    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
	    caffeineCacheManager.setCaffeine(caffeine);
	    return caffeineCacheManager;
	}
	
}
