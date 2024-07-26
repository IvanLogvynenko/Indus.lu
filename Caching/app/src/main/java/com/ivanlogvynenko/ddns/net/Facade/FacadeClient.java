package com.ivanlogvynenko.ddns.net.Facade;

import org.springframework.web.client.RestTemplate;

public class FacadeClient {
	private String hashing_url, hard_storage_url;
	private RestTemplate restTemplate;

	public FacadeClient(String facade_url, String hard_storage_url) {
		this.hashing_url = facade_url;
		this.hard_storage_url = hard_storage_url;
		this.restTemplate = new RestTemplate();
	}

    public void saveData(String data, int key) {
		this.restTemplate.put(this.hard_storage_url + "save/" + data + "/" + key, null);
    }

    public void getData(int key) {
		var resp = this.restTemplate.getForEntity(
			this.hard_storage_url + "get/" + key, String.class);
		
		
    }

    public int hash(String data) {
        var resp = this.restTemplate.getForEntity(
			this.hashing_url + "hash_string/" + data, Integer.class);

		if (resp.getStatusCode().is2xxSuccessful())
			return resp.getBody();
		else return -1;
    }

    public int resolve_collision(String data, int collision) {
		var resp = this.restTemplate.getForEntity(
			this.hashing_url + "resolve_collision_string/" + data + "/" + collision,
			Integer.class
		);

		if (resp.getStatusCode().is2xxSuccessful())
			return resp.getBody();
		else return -1;
    }
}
