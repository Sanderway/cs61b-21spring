package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int hashSize;
    private int numPairs;
    private double maxLoad = 0.75;
    private static final int INIT_CAPACITY = 16;

    /**
     * Constructors
     */
    public MyHashMap() {
        this(INIT_CAPACITY);
    }

    public MyHashMap(int initialSize) {
        this.hashSize = initialSize;
        buckets = new Collection[hashSize];
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this(initialSize);
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    public void clear() {
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = null;
        }
        numPairs = 0;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    private int hash(K key, int hashSize) {
        return Math.floorMod(key.hashCode(), hashSize);
    }

    public V get(K key) {
        int i = hash(key, hashSize);
        if (buckets[i] != null) {
            for (Node thisNode : buckets[i]) {
                if (thisNode.key.equals(key)) {
                    return thisNode.value;
                }
            }
        }
        return null;
    }

    public int size() {
        return numPairs;
    }

    private void resize(int tableSize) {
        Collection<Node>[] temp = createTable(tableSize);
        Collection<Node>[] oldBuckets;
        oldBuckets = buckets;
        buckets = temp;
        for (int i = 0; i < hashSize; i++) {
            if (oldBuckets[i] != null) {
                for (Node node : oldBuckets[i]) {
                    putforResize(node.key, node.value,tableSize);
                }
            }
        }
        hashSize = tableSize;
    }
    private boolean putforResize(K key,V value,int hashSize) {
        int i = hash(key, hashSize);
        Node newNode = new Node(key, value);
        boolean addFlag = false;
        if (!this.containsKey(key)) {
            buckets[i].add(newNode);
            addFlag = true;
        }
        else{
            Iterator iter = buckets[i].iterator();
            while(iter.hasNext()){
                Node repeatNode = (Node) iter.next();
                if (repeatNode.key.equals(key)){
                    buckets[i].remove(repeatNode);
                    buckets[i].add(newNode);
                }
            }
        }
        return addFlag;
    }
    public void put(K key, V value) {
        if (numPairs >= maxLoad * hashSize) {
            resize(2 * hashSize);
        }
        if(putforResize(key,value,hashSize)) {
            numPairs++;
        }
    }

    public Set<K> keySet() {
        Set<K> storage = new HashSet<>();
        for (int i = 0; i < hashSize; i++) {
            if (buckets[i] != null) {
                for (Node node : buckets[i]) {
                    storage.add(node.key);
                }
            }
        }
        return storage;
    }

    public V remove(K key) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    public Iterator<K> iterator() {
        return new hashMapIterator();
    }

    private class hashMapIterator implements Iterator<K> {
        Iterator iter = keySet().iterator();

        public hashMapIterator() {

        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public K next() {
            return (K) iter.next();
        }
    }
}
