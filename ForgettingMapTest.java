import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ForgettingMapTest {

	@Test
	public void testAdd() {
		ForgettingMap<String, String> map = new ForgettingMap<>(2); 
		map.add("key1", "value1");
		map.add("key2", "value2");
		assertEquals(map.getNumberOfKeys(), 2);
		assertEquals(map.getCurrentMapSnapshot(), "key1-value1,key2-value2");
	}
	
	@Test
	public void testFindValueByKey() {
		ForgettingMap<String, String> map = new ForgettingMap<>(2); 
		map.add("key1", "value1");
		map.add("key2", "value2");
		assertEquals(map.findValueByKey("key1"), "value1");
		assertEquals(map.findValueByKey("key2"), "value2");
	}


	@Test
	public void testAddElementToFullMap() {
		ForgettingMap<String, String> map = new ForgettingMap<>(7);
		
		// add 7 items to the Forgetting Map without accessing any key in between
		for (int i=0;i<7;i++) {
			String key = "key" + i;
			String value = "value" + i;
			map.add(key, value);
		}
		
		// access all the keys but key6
		for (int i=0;i<7;i++) {
			if (i == 6) {
				continue;
			}
			String key = "key" + i;
			map.findValueByKey(key);
		}
		// add a new element now will remove the key6 as that is the least accessed one
		map.add("testKey", "testValue");
		assertFalse(map.isKeyPresent("key6"));
	}
	
	@Test
	public void testLeastUsedKeyIsRemoved() {
		ForgettingMap<String, String> map = new ForgettingMap<>(2);
		
		// add two elements to forgettingMap
		for (int i=0;i<2;i++) {
			String key = "key" + i;
			String value = "value" + i;
			map.add(key, value);
		}
		
		// access key2 twice and key1 once
		for (int i=0;i<2;i++) {
			String key = "key" + i;
			if (i==1) {
				map.findValueByKey(key);
			}
			map.findValueByKey(key);
		}
		
		// add a new key to the forgettingMap
		map.add("key2", "value2");
		
		assertEquals(map.getNumberOfKeys(), 2);
		assertEquals(map.getCurrentMapSnapshot(), "key1-value1,key2-value2");
	}
	
}
