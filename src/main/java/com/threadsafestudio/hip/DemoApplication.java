package com.threadsafestudio.hip;

import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import se.hip.sdk.bootstrap.Hip;
import se.hip.sdk.listener.PropertyListener;

@EnableAutoConfiguration(exclude = {CamelAutoConfiguration.class, ActiveMQAutoConfiguration.class})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		final SpringApplicationBuilder builder = new SpringApplicationBuilder();
		builder.listeners(new PropertyListener());
		builder.sources(Hip.class, DemoApplication.class);

		final SpringApplication app = builder.build();

		app.run();
	}
}
