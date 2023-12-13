package bstmap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;
        private int size;

        public BSTNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        root = null;
    }

    public boolean containsKey(K key) {
        if (get(key) == null && key != null && root != null) return true;
        return get(key) != null;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (key == null || node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(BSTNode node) {
        if (node == null) return 0;
        return node.size;
    }

    public void put(K key, V value) {
        if (key == null) return;
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) return new BSTNode(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Set<K> keySet() {
//        return keySet(root);
        throw new UnsupportedOperationException();
    }

//    private Set<K> keySet(BSTNode node){
//        if (node == null) return null;
//        Set<K> thisKey = new HashSet<>();
//        thisKey.add(node.key);
//        thisKey.addAll(keySet(node.left));
//        thisKey.addAll(keySet(node.right));
//        return thisKey;
//    }
    @Override
    public V remove(K key) {
//        if (get(key)==null) return null;
        throw new UnsupportedOperationException();
    }
//    private BSTNode remove(BSTNode node,K key){
//        if (node == null) return null;
//        int cmp = key.compareTo(node.key);
//        if(cmp<0) node.left = remove(node.left,key);
//        if(cmp>0) node.right = remove(node.right,key);
//        else
//    }
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
