package com.shortlink.link;

/**
 * <b>Created by Akash Hirke</b>
 * <b>It isa Main Class to run project</b>
 * <b>configuration setup</b>
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories(basePackages = "com.shortlink.link")
@ComponentScan("com.shortlink.link")
@Configuration
@EnableAutoConfiguration
@EntityScan("com.shortlink.link")
@SpringBootApplication
public class LinkApplication {

	/**
	 * <b>Application Main Method</b>
	 */
	public static void main(String[] args) {
		SpringApplication.run(LinkApplication.class, args);
	}
}
