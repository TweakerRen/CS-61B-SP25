import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>, Iterable<K> {
    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        size = 0;
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    public BSTNode put(K k, V v, BSTNode n){
        if (n == null) {
            n = new BSTNode(k, v);
            size++;
            return n;
        }
        if (k.compareTo(n.key) < 0)
            n.left = put(k, v, n.left);
        if (k.compareTo(n.key) > 0)
            n.right = put(k, v, n.right);
        if (k.compareTo(n.key) == 0)
            n.value = v;
        return n;
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    public V get(K k, BSTNode n) {
        if (n == null)
            return null;
        if (k.compareTo(n.key) < 0)
            return get(k, n.left);
        if (k.compareTo(n.key) > 0)
            return get(k, n.right);
        if (k.compareTo(n.key) == 0)
            return n.value;
        return null;
    }
    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    public boolean containsKey(K k, BSTNode n) {
        if (n == null)
            return false;
        if (k.compareTo(n.key) < 0)
            return containsKey(k, n.left);
        if (k.compareTo(n.key) > 0)
            return containsKey(k, n.right);
        if (k.compareTo(n.key) == 0)
            return true;
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> storage = new TreeSet<>();
        for (K key : this) {
            storage.add(key);
        }
        return storage;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    private class BSTMapIter implements Iterator<K> {
        private Stack<BSTNode> stack = new Stack<>();
        public BSTMapIter() {
            pushLeft(root);
        }

        public void pushLeft(BSTNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode item = stack.pop();
            K result = item.key;
            if (item.right != null) {
                pushLeft(item.right);
            }
            return result;
        }
    }
}
