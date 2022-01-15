import java.util.*;
import java.util.stream.Collectors;
/**
 * ForgettingMap - Thread Safe Map which holds associations between key and content
 * It supports two associations - add and find operation
 * It can hold no more than x associations at a time, and when at capacity, to add a new entry, 
 * the least used element from the Map is removed.
 *
 **/

public class ForgettingMap<k,v> {
	
  private Map<k, v> forgettingMap = new TreeMap<>();
  private Map<k, Integer> frequencyOfAccess = new TreeMap<>();
  private int forgettingMapSize;
  
  public ForgettingMap(int size) {
    forgettingMapSize = size;
  }
  
  public synchronized void add (k key, v value) {
	int mapSize = forgettingMap.size();
    if (mapSize == forgettingMapSize) {
      k leastUsedKey = findLeastAccessedKey();
      
      // remove least used key if present else remove any object at any key
       forgettingMap.remove(leastUsedKey);
       frequencyOfAccess.remove(leastUsedKey); 
    }
    // add the new key value pair to the map and initialize the key in frequencyOfAccess to 0
    forgettingMap.put(key, value);
    frequencyOfAccess.put(key, 0);
  }

  private k findLeastAccessedKey () {
    return Collections.min(frequencyOfAccess.entrySet(), Map.Entry.comparingByValue()).getKey();
  }

  public synchronized v findValueByKey (k key) {
	  if (frequencyOfAccess.get(key) == null) {
		  System.out.println("No such key is present");
	  }
	  frequencyOfAccess.put(key, frequencyOfAccess.get(key)+1);
	  return forgettingMap.get(key);
  }
  
  public synchronized int getNumberOfKeys() {
	  return forgettingMap.keySet().size();
  }
  
  public synchronized String getCurrentMapSnapshot() {
	return String.join(",", forgettingMap.keySet().stream().map(key -> key.toString() + "-" + forgettingMap.get(key).toString()).collect(Collectors.toList()));
  }
  
  public synchronized boolean isKeyPresent(k key) {
	  return forgettingMap.containsKey(key);
  }
}

