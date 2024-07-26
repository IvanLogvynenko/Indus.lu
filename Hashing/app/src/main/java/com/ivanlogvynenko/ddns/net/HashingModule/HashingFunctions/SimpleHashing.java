package com.ivanlogvynenko.ddns.net.HashingModule.HashingFunctions;

import com.ivanlogvynenko.ddns.net.Util;

public class SimpleHashing implements IHashingFunction {

	@Override
	public int hash(int data) {
		return data;
	}

	@Override
	public int hash(String data) {
		return this.hash(Util.intifyString(data));
	}
	
}
