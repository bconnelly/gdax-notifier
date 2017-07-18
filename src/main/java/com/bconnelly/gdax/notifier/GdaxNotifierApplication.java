package com.bconnelly.gdax.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class GdaxNotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdaxNotifierApplication.class, args);

		EthTicker ticker = new EthTicker();
		int lastTrade = 0;

		while(true){

//			make sure this price is from a new trade
			TickerResponseRepresentation tickerResult = ticker.getLastPrice();

			if(tickerResult.getTrade_id() != lastTrade){
				System.out.println(tickerResult.getPrice());
				lastTrade = tickerResult.getTrade_id();
			}

			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Wait failed");
			}
		}

	}
}
