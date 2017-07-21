package com.bconnelly.gdax.notifier;

import com.bconnelly.gdax.notifier.manager.EthSocketManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;

@SpringBootApplication(scanBasePackages = "com.bconnelly.gdax")
public class GdaxNotifierApplication {

	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		SpringApplication.run(GdaxNotifierApplication.class, args);

		EthSocketManager.listen();


	}
}
