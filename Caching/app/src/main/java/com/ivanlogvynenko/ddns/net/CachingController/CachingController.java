package com.ivanlogvynenko.ddns.net.CachingController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanlogvynenko.ddns.net.CachingModule.Cache;
import com.ivanlogvynenko.ddns.net.CachingModule.FIFOCache;

@RestController
@RequestMapping(value = "/cache")
public class CachingController {
	private final Cache cache;

	public CachingController() {
		this.cache = new FIFOCache();
	}

	@PutMapping("/{data}")
	public void put(@PathVariable("data") String data) {
		this.cache.put(data);
	}

	@GetMapping("/{key}")
	public ResponseEntity<String> get(@PathVariable("key") Integer key) {
		if (this.cache.get(key) == null)
			return ResponseEntity.badRequest().body("");
		else return ResponseEntity.ok(this.cache.get(key));
	}
}
