package com.ivanlogvynenko.ddns.net.implementations;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


public class TokenManager {
	String url;
	private final RestTemplate restTemplate;

	public TokenManager(String url) {
		this.url = url;
		this.restTemplate = new RestTemplate();
	}

	public String get_new_token() throws RuntimeException {
		var response = this.restTemplate.getForEntity(url + "get_new_token", String.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			JSONObject result = new JSONObject(response.getBody());
			return result.getString("token");
		}
		else throw new RuntimeException("Failed on recieving token");
	}

	public boolean remove_token(String token) throws RuntimeException {
		var response = this.restTemplate.exchange(
			url + "remove_token/" + token,
			HttpMethod.DELETE,
			HttpEntity.EMPTY,
			String.class
		);

		if (response.getStatusCode().is2xxSuccessful())
			return new JSONObject(response.getBody()).getBoolean("valid");
		else throw new RuntimeException("Token was not found");
	}

	public boolean validate_token(String token) throws RuntimeException {
		var response = this.restTemplate.getForEntity(url + "check_token/" + token, String.class);

		if (response.getStatusCode().is2xxSuccessful())
			return new JSONObject(response.getBody()).getBoolean("valid");
		else throw new RuntimeException("Validation failed");
	}
}
