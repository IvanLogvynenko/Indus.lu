package com.ivanlogvynenko.ddns.net.HashingModule.CollisionResolvers;

import com.ivanlogvynenko.ddns.net.Util;
import com.ivanlogvynenko.ddns.net.HashingModule.HashingFunctions.IHashingFunction;

public class LiniarResolver implements ICollisionResolver {

	@Override
	public int resolveCollision(int data, int collision, IHashingFunction function) {
		if (collision <= 0)
			throw new IllegalArgumentException("collisions can't be 0 or less");

		int result = function.hash(data);
		return result + collision;
			
	}

	@Override
	public int resolveCollision(String data, int collision, IHashingFunction function) {
		return this.resolveCollision(Util.intifyString(data), collision, function);
	}
	
}
