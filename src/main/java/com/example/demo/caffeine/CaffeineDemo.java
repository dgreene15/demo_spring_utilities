package com.example.demo.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CaffeineDemo {
	
    @Autowired
    private CacheManager cacheManager;
	
	/**
	 * 
	 * @Cacheable - caches the result for you based on time.
	 * 
	 * Go the error below until defining the cache name:
	 * Caused by: java.lang.IllegalStateException: No cache could be resolved for 'Builder
	 */
	@Cacheable(value = "address_cache")
	public CachedData getDataWithAnnotation(int id) {
		return new CachedData("myname", "myaddress");
	}
	
	
	/**
	 * 
	 * For more control can use cache manager
	 *
	 */
	public CachedData getDataWithManager(int id) {
		//if(cacheManager.getCache(null))
		System.out.println("Cache Names: " + cacheManager.getCacheNames());
		//if pass just 4 then will auto-box to Integer since accepts an Object
		if(cacheManager.getCache("address_cache").get(Integer.valueOf(4)) != null) {
			System.out.println(cacheManager.getCache("address_cache").get(4).get().toString());
		}
		
		cacheManager.getCache("address_cache").put(5, new CachedData("myname", "myaddress"));
		
		return new CachedData("myname", "myaddress");
	}
	
	public class CachedData {
		String name;
		String address;
		
		public CachedData(String name, String address) {
			super();
			this.name = name;
			this.address = address;
			System.out.println("CachedData Constructor");
		}

		@Override
		public String toString() {
			return "CachedData [name=" + name + ", address=" + address + "]";
		}
		
		
	}
}


