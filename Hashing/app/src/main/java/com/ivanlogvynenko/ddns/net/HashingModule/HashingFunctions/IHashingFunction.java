package com.ivanlogvynenko.ddns.net.HashingModule.HashingFunctions;

public interface IHashingFunction {
	public int hash(int data);
	public int hash(String data);
}
