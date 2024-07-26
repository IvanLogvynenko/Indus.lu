package com.ivanlogvynenko.ddns.net.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.ivanlogvynenko.ddns.net.URLs;
import com.ivanlogvynenko.ddns.net.implementations.TokenManager;

@RestController
@RequestMapping(value = "/token_manager")
public class TokenController {
	private final TokenManager tm;

	public TokenController() {
		this.tm = new TokenManager(URLs.token_manager_url);
	}

	@GetMapping("/get_new_token")
	public ResponseEntity<String> get_new_token() {
		try {
			return ResponseEntity.ok(tm.get_new_token());
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/remove_token/{token}")
	public ResponseEntity<String> remove_token(@PathVariable("token") String token) {
		try {
			if (tm.remove_token(token))
				return ResponseEntity.ok("Token removed");
			else throw new IllegalCallerException("Something went wrong");
		}
		catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/check_token/{token}")
	public ResponseEntity<Boolean> check_token(@PathVariable("token") String token) {
		try {
			return ResponseEntity.ok(tm.validate_token(token));
		}
		catch (RuntimeException e) {
			return ResponseEntity.status(400).body(false);
		}
	}
}
