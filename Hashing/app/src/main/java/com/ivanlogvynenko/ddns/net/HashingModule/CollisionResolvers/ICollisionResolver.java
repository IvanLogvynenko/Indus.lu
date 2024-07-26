package com.ivanlogvynenko.ddns.net.HashingModule.CollisionResolvers;

import com.ivanlogvynenko.ddns.net.HashingModule.HashingFunctions.IHashingFunction;

public interface ICollisionResolver {
	public int resolveCollision(int data, int collision, IHashingFunction function);
	public int resolveCollision(String data, int collision, IHashingFunction function);

}