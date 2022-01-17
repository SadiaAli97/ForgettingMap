# ForgettingMap
Implements a map which holds n associations and when a new entry requires space, the least used element from the map is removed. The map supports two operations - add and find. 

The ForgettingMap takes in any arguments as key and value.
It has the following characteristics -
- It uses a TreeMap, as a TreeMap is sorted according to the natural ordering of its keys
- The treemap implementation is not synchronized in the sense that if a map is accessed by multiple threads, concurrently and at least one of the threads modifies the map structurally, it must be synchronized externally. To achieve thread safe behavior for all the methods, the methods are declared as `synchronised`. When one thread is executing a synchronized method for an object, all other threads that invoke synchronized methods for the same object block (suspend execution) until the first thread is done with the object.
- The forgetting map supports two main operations -
   - add - This allow us to add a new key, value pair to the ForgettingMap.
   - Along with adding the element, it also inserts the key to a `frequencyOfAccess` data structure. As this key isn't yet accessed, its frequency is initialised to 0.
   - The forgettingMap has the ability to store only n associations where n is the parameter passed while constructing an instance of the map. When this size is reached, then to insert a new element, the least used element is removed. This is decided based on the frequency of access of the key. 
   - This natural ordering is use do break ties in case the number of times the elements are accessed is same. In that case, when multiple items have same frequency of access, the key that comes first in natural order is removed.
 - findValueByKey - This returns the value for the key passed as argument.
 - Along with this, it also updates the `frequencyOfAccess` map tp reflect the correct frequency for every key.

Along with the above main operations, it also supports some additional operations to verify that the ForgettingMap supports the requirements

The second part is to write test cases for this.

The following test cases are added -
- Testing the `add` operation, by adding two key, value pairs and asserting that the keys length is two and the map snapshot matches the expected value.
- Testing the `findValueByKey`, this makes assertions to verify that the correct value is returned while accessing a key.
- Testing adding new element when the Map is full, this makes assertion that the new item is added and the least accessed key is removed.
- Testing least used key is removed.


