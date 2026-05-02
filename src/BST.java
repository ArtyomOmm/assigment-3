import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.BSTElement<K, V>> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }


    public static class BSTElement<K, V> {
        private K key;
        private V value;

        public BSTElement(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }


        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = new Node(key, val);
        } else {
            parent.right = new Node(key, val);
        }
        size++;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }
        return null;
    }


    public void delete(K key) {
        Node parent = null;
        Node current = root;


        while (current != null && current.key.compareTo(key) != 0) {
            parent = current;
            if (key.compareTo(current.key) < 0) current = current.left;
            else current = current.right;
        }

        if (current == null) return;


        if (current.left == null || current.right == null) {
            Node newCurrent = (current.left == null) ? current.right : current.left;

            if (parent == null) {
                root = newCurrent;
            } else if (current == parent.left) {
                parent.left = newCurrent;
            } else {
                parent.right = newCurrent;
            }
        }

        else {
            Node successorParent = null;
            Node successor = current.right;


            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }


            current.key = successor.key;
            current.val = successor.val;


            if (successorParent != null) {
                successorParent.left = successor.right;
            } else {
                current.right = successor.right;
            }
        }
        size--;
    }


    @Override
    public Iterator<BSTElement<K, V>> iterator() {
        return new Iterator<BSTElement<K, V>>() {
            private Stack<Node> stack = new Stack<>();
            private Node current = root;

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() || current != null;
            }

            @Override
            public BSTElement<K, V> next() {

                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }


                Node node = stack.pop();
                BSTElement<K, V> element = new BSTElement<>(node.key, node.val);


                current = node.right;

                return element;
            }
        };
    }
}
