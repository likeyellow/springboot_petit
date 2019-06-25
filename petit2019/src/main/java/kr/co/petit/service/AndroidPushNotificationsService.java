package kr.co.petit.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AndroidPushNotificationsService {
	
	private static final String firebase_server_key = "AAAAm3f1dmM:APA91bGG2JLYA4_CPQTzs2zIFQFRxavKUrOkpaFFqvrzWKHbm4CnBBngQDBhWzf1JA4hSvjehW-9LzpV_QkTCncN-yZ4ZaoLoP4mjl2Nc311jg8ozeQBHbL_ZLjB8-FOvTY26YExFite";
	private static final String firebase_api_url = "https://fcm.googleapis.com/fcm/send";

	
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebase_server_key));
		interceptors.add(new HeaderRequestInterceptor("Contnet-Type", "application/json; UTF-8 "));
		restTemplate.setInterceptors(interceptors);
		
		String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);
		
		return CompletableFuture.completedFuture(firebaseResponse);
	}

}
