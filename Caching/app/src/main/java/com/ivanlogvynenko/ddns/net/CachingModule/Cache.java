package com.ivanlogvynenko.ddns.net.CachingModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import com.ivanlogvynenko.ddns.net.Facade.FacadeClient;

public abstract class Cache {
	protected Map<Integer, String> cache;
	protected Queue<Integer> removeOrder;

	protected int maxSize;

	protected FacadeClient facade;

	public Cache() {
		this.cache = new HashMap<>();
		this.maxSize = 1000;
		this.facade = new FacadeClient("http://127.0.0.1:5002/", 
				"http://127.0.0.1:5003/");
	}

	public Cache(int maxSize) {
		this.cache = new HashMap<>();
		this.maxSize = maxSize;
	}

	public String get(int key) {
		return this.cache.get(key);
	}

	public abstract int put(String data);

	public Map<Integer, String> getCache() {
		return cache;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
}
