**1. What is the difference between String, StringBuilder and StringBuffer?**
A. **String:** Immutable, thread-safe, slower for frequent modifications, best for constant strings.
	**StringBuilder:** Mutable, not thread-safe, fastest for frequent modifications in single-threaded environments.
	**StringBuffer:** Mutable, thread-safe, slower than StringBuilder due to synchronization, suitable for multi-threaded environments.
	
**2. what is the difference creating a String using new and String litteral?**
		A. When you create a String using a string literal, Java optimizes memory usage by storing the string in a special area of the heap memory called the string pool (or interned string pool).
		
		Example:
		
		java
		Copy code
		String str1 = "Hello";
		String str2 = "Hello";
		Both str1 and str2 refer to the same object in the string pool.
		Java checks the string pool first to see if the string already exists. If it does, the reference to the existing string is returned. If not, the string is added to the pool.
		Using new Keyword
		When you create a String using the new keyword, a new object is created on the heap, regardless of whether an equivalent string already exists in the string pool.
		
		Example:
		
		java
		Copy code
		String str1 = new String("Hello");
		String str2 = new String("Hello");
		str1 and str2 refer to different objects in the heap, even though they contain the same sequence of characters.
		Each call to new String("Hello") creates a new object.
		Detailed Comparison
		Memory Management:
		
		String Literal:
		
		Stored in the string pool.
		If the same literal is used multiple times, the JVM reuses the existing object from the pool, saving memory.
		Using new Keyword:
		
		Creates a new object each time, even if the content is the same.
		Stored in the regular heap space, not in the string pool.
		Performance:
		
		String Literal:
		
		Faster because it can take advantage of the string pool.
		No need to create a new object if it already exists in the pool.
		Using new Keyword:
		
		Slower due to the creation of a new object every time.
		Additional overhead of object creation and garbage collection.
		
**3. What is the difference between ArrayList And LinkedList?**
A. 	**ArrayList:**
		1. Uses a dynamic array.
		2. Fast random access (O(1)).
		3. Slow insertions and deletions (O(n) in the worst case).
		4. Less memory overhead per element.

   **LinkedList:**

		1. Uses a doubly linked list.
		2. Slow random access (O(n)).
		3. Fast insertions and deletions at the beginning and end (O(1)).
		4. More memory overhead per element.
	
**5. What is the Difference between HashMap and CucorrentHAshMap?**
A.	**HashMap**
		1. HashMap is not a Thread safe and it is non-synchronized in nature.
		2. Due to non-synchronization, the performance of HashMap is relatively higher, and various threads are capable of performing simultaneously.
		3. If the other threads try to modify or add the contents to an object while a thread iterates the HashMap object, we receive a run-time exception that says ConcurrentModificationException.
		
   **ConcurrentHashMap**
   		1. ConcurrentHashMap is Thread Safe. 
   		2. Its performance gets comparatively lower because some of the threads need to wait.
   		3. ConcurrentHashMap doesn’t generate any such exception when we perform any kinds of moderations during iteration.	
			
**5. What is the Difference between HashTable and CucorrentHAshMap?**

A.  **Hashtable:**

     Synchronizes every method call on a single lock (the map object itself). This can lead to significant contention and performance degradation under high concurrency because only one thread can access the map at a time, even for read operations.

**ConcurrentHashMap:**

    Utilizes fine-grained locking with lock striping. The map is divided into smaller segments, each locked independently. This allows for higher concurrency by permitting multiple threads to read and write to different segments of the map simultaneously.
	
**7. what is the internal flow of all Map implementation class?**	

A.  `1. HashMap`
		Data Structure: Array of linked lists (or a combination of arrays and trees for high-collision buckets).
		Internal Flow:
		Hashing: The key's hashCode is computed and used to determine the bucket (index in the array) where the key-value pair will be stored.
		Collision Handling: Uses chaining, where each bucket contains a linked list or a balanced tree (if the number of entries in the bucket exceeds a certain threshold).
		Insertion: If the bucket is empty, the key-value pair is inserted directly. If the bucket is not empty, the list is traversed to either update an existing key or append the new key-value pair.
		Retrieval: The hash code of the key is used to locate the bucket, and the list or tree in that bucket is searched for the key.
	`2. LinkedHashMap`
		Data Structure: Similar to HashMap but with a doubly linked list connecting the entries.
		Internal Flow:
		Hashing and Collision Handling: Same as HashMap.
		Insertion: Along with storing the key-value pair in the appropriate bucket, the pair is also added to a doubly linked list, maintaining the insertion order.
		Retrieval: Same as HashMap.
		Ordering: Maintains insertion order or access order (if configured).
 	`3. TreeMap`
		Data Structure: Red-black tree.
		Internal Flow:
		Sorting: Keys are stored in a sorted order based on their natural ordering or a provided comparator.
		Insertion: The tree is traversed to find the correct position for the new key-value pair. The red-black tree properties are maintained to ensure balance.
		Retrieval: The tree is traversed to find the key.
		Navigation: Supports operations like finding the closest lower or higher key, which is efficient due to the tree structure.
    `4. Hashtable`
		Data Structure: Array of linked lists (similar to HashMap but synchronized).
		Internal Flow:
		Hashing and Collision Handling: Same as HashMap.
		Synchronization: All methods are synchronized, making it thread-safe but less efficient in concurrent scenarios compared to ConcurrentHashMap.
	`5. ConcurrentHashMap`
		Data Structure: Array of segments (each segment is a hash table).
		Internal Flow:
		Concurrency: Uses a finer-grained locking mechanism called lock striping. Each segment can be locked independently, allowing for better concurrency.
		Hashing: Keys are hashed to determine the segment and the bucket within that segment.
		Insertion: The relevant segment is locked, and the key-value pair is inserted.
		Retrieval: The segment and bucket are identified using the hash code, and the key is searched within the bucket.
		Scaling: More scalable under concurrent access due to reduced contention.
		
**8. Describe the volatile keyword and its use in Java?**

A. The volatile keyword in Java is a type of variable modifier that tells the JVM (Java Virtual Machine) that a variable can be accessed and modified by multiple threads. The volatile keyword is used in multithreaded environments to ensure that changes made to a variable by one thread are immediately visible to other threads.		

   The primary advantage of the volatile keyword is its guarantee of visibility of changes across threads
   

**9. Explain the concepts of synchronized blocks and methods. How do they differ from each other?**

A. Synchronization is used to control the access of multiple threads to shared resources to prevent thread interference and consistency problems. The synchronized keyword in Java can be applied to methods and blocks of code to achieve this control.

	`Synchronized Methods`
  	
  		A synchronized method ensures that only one thread can execute the method at a time for a given instance. When a thread enters a synchronized method, it acquires a lock on the object the method belongs to. If another thread tries to enter any synchronized method on the same object, it will be blocked until the lock is released.

  `Synchronized Blocks`
		A synchronized block allows more fine-grained control over synchronization. Instead of synchronizing the entire method, you can synchronize only a portion of the code. This can improve performance by reducing the scope of the lock.

  
