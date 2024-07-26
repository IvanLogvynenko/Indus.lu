package com.ivanlogvynenko.ddns.net.HashingModule;

import com.ivanlogvynenko.ddns.net.HashingModule.CollisionResolvers.ICollisionResolver;
import com.ivanlogvynenko.ddns.net.HashingModule.CollisionResolvers.LiniarResolver;
import com.ivanlogvynenko.ddns.net.HashingModule.HashingFunctions.IHashingFunction;
import com.ivanlogvynenko.ddns.net.HashingModule.HashingFunctions.SimpleHashing;

//Facade again
public class Hasher {
	private IHashingFunction function;
	private ICollisionResolver collisionResolver;

	public Hasher() {
		this.function = new SimpleHashing();
		this.collisionResolver = new LiniarResolver();
	}

	public Hasher(IHashingFunction function) {
		this.function = function;
	}

	public int hash(int data) {
		return this.function.hash(data);
	}

	public int resolveCollision(int data, int collision) {
		return this.collisionResolver.resolveCollision(data, collision, function);
	}

	public int hash(String data) {
		return this.function.hash(data);
	}

	public int resolveCollision(String data, int collision) {
		return this.collisionResolver.resolveCollision(data, collision, function);
	}

	public ICollisionResolver getCollisionResolver() {
		return collisionResolver;
	}
	public IHashingFunction getFunction() {
		return function;
	}
	public void setCollisionResolver(ICollisionResolver collisionResolver) {
		this.collisionResolver = collisionResolver;
	}
	public void setFunction(IHashingFunction function) {
		this.function = function;
	}
}
