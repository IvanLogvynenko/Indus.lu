package com.ivanlogvynenko.ddns.net.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.ivanlogvynenko.ddns.net.URLs;
import com.ivanlogvynenko.ddns.net.implementations.ACL;

@RestController
@RequestMapping(value = "/acl")
public class ACLController {

	private final ACL acl;

	public ACLController() {
		acl = new ACL(URLs.acl_url);
	}

	@PostMapping("/add_token/{token}")
	public ResponseEntity<Boolean> add_token(@PathVariable("token") String token) {
		return ResponseEntity.ok(acl.add_token(token));
	}

	@DeleteMapping("/remove_token/{token}")
	public void remove_token(@PathVariable("token") String token) {
		this.acl.remove_token(token);
	}

	@GetMapping("/check_access/{token}/{hash}") 
	public ResponseEntity<Boolean> check_access(@PathVariable("token") String token, 
			@PathVariable("hash") String hash) {
		return ResponseEntity.ok(this.acl.check_access(token, hash));
	}

	@PutMapping("/grand_access/{token}/{hash}")
	public ResponseEntity<Boolean> grant_access(@PathVariable("token") String token, 
		@PathVariable("hash") String hash) {
			return ResponseEntity.ok(this.acl.grant_access(token, hash));
	}
}
