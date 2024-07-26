package com.ivanlogvynenko.ddns.net.HashingController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanlogvynenko.ddns.net.HashingModule.Hasher;

@RestController
@RequestMapping(value="/")
public class HashingController {
	private final Hasher hasher;

	public HashingController() {
		this.hasher = new Hasher();
	}

	@GetMapping("hash_int/{value}")
	public ResponseEntity<Integer> hash_int(@PathVariable("value") String value) {
		return ResponseEntity.ok(this.hasher.hash(Integer.parseInt(value)));
	}

	@GetMapping("hash_string/{value}")
	public ResponseEntity<Integer> hash_string(@PathVariable("value") String value) {
		return ResponseEntity.ok(this.hasher.hash(value));
	}

	@GetMapping("resolve_collision_int/{value}/{collision}")
	public ResponseEntity<Integer> resolve_collision_int(@PathVariable("value") String value, 
		@PathVariable("collision") String collision) {
		return ResponseEntity.ok(this.hasher.resolveCollision(Integer.parseInt(value), Integer.parseInt(collision)));
	}

	@GetMapping("resolve_collision_string/{value}/{collision}")
	public ResponseEntity<Integer> resolve_collision_string(@PathVariable("value") String value,
			@PathVariable("collision") String collision) {
		return ResponseEntity.ok(this.hasher.resolveCollision(value, Integer.parseInt(collision)));
	}
}
