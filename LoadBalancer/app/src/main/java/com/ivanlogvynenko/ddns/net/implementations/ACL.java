package com.ivanlogvynenko.ddns.net.implementations;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class ACL {
	final String url;
	private final RestTemplate restTemplate;

	public ACL(String url) {
		this.url = url;
		this.restTemplate = new RestTemplate();
	}

	public boolean add_token(String token) {
		var resp = this.restTemplate
			.postForEntity(this.url + "add_token" + token, token, String.class);
		
		if (resp.getStatusCode().is2xxSuccessful()) 
			return new JSONObject(resp.getBody()).getBoolean("valid");
		else 
			throw new RuntimeException("Error while adding new token");
	}

	public void remove_token(String token) {
		var resp = this.restTemplate.exchange(
			url + "remove_token/" + token,
			HttpMethod.DELETE,
			HttpEntity.EMPTY,
			String.class
		);

		if (!resp.getStatusCode().is2xxSuccessful()) 
			throw new RuntimeException("Error while removing token");
	}

	public boolean check_access(String token, String hash) {
		var resp = this.restTemplate.getForEntity(
			url + "check_access/" + token + "/" + hash, String.class);
		
		if (resp.getStatusCode().is2xxSuccessful()) 
			return new JSONObject(resp.getBody()).getBoolean("valid");
		else throw new RuntimeException("token invalid");
	}

	public boolean grant_access(String token, String hash) {
		var resp = this.restTemplate.exchange(
			url + "grant_access/" + token + "/" + hash,
			HttpMethod.PUT,
			HttpEntity.EMPTY,
			String.class
		);
		if (resp.getStatusCode().is2xxSuccessful()) 
			return new JSONObject(resp.getBody()).getBoolean("valid");
		else throw new RuntimeException("token invalid");
	}
}
