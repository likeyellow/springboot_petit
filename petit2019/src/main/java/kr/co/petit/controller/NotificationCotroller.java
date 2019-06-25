package kr.co.petit.controller;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.petit.service.AndroidPushNotificationsService;
import kr.co.petit.service.AndroidPushPeriodicNotifications;



@RestController
public class NotificationCotroller {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;
	
	@GetMapping(value="/send")
	public @ResponseBody ResponseEntity<String> send() throws JSONException, InterruptedException{
		String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();
		
		HttpEntity<String> request = new HttpEntity<>(notifications);
		
		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
		CompletableFuture.allOf(pushNotification).join();
		
		try {
			String firebaseResponse = pushNotification.get();
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		
		}catch(InterruptedException e) {
			logger.debug("got interrupted!");
			throw new InterruptedException();
		
		}catch(ExecutionException e) {
			logger.debug("execution error!");
		
		}
		
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * @PostMapping(value = "/fcmPush") Object fcmPush(@Valid @RequestBody
	 * FcmPushData fcmPushData)throws Exception{
	 * 
	 * HashMap<String, Object> reSult = new HashMap<String, Object>();
	 * 
	 * reSult.put("rt_code", 0);
	 * 
	 * fcmUtil.send(fcmPushData.title, fcmPushData.body, fcmPushData.device);
	 * 
	 * return reSult;
	 * 
	 * }
	 */
}