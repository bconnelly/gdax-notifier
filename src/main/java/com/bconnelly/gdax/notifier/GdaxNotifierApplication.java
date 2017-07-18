package com.bconnelly.gdax.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class GdaxNotifierApplication {

	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		SpringApplication.run(GdaxNotifierApplication.class, args);

		EthSocketService.listen();


	}
}
