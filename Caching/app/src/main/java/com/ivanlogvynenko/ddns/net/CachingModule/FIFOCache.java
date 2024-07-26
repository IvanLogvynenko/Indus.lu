package com.ivanlogvynenko.ddns.net.CachingModule;

public class FIFOCache extends Cache {

	@Override
	public int put(String data) {
		if (this.cache.size() == this.maxSize)
			this.cache.remove(this.removeOrder.poll());
		
		int key = this.facade.hash(data);
		this.removeOrder.add(key);

		for (int i = 0; i < maxSize && this.cache.get(key) != null; i++)
			key = this.facade.resolve_collision(data, i);

		this.cache.put(key, data);
		return key;
	}
	
}
