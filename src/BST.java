import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Returns the number of elements in the binary search tree
    public int size() {
        return size;
    }

    public void findHeight(){
        System.out.println(find(root));
    }
    private int find(Node node){
       int size1=findLeft(node.left,0);
       int size2=findRight(node.right,0);
       return Math.max(size1, size2);
    }
    private int findLeft(Node node, int size){
        size++;
        if(node==null){
            return size;
        }
        if(node.right==null){
            size=findLeft(node.left,size);
        }if(node.left==null){
            size=findLeft(node.right,size);
        }
        else{
            size=findLeft(node.right,size);
            size=findLeft(node.left,size);
        }
        return size;
    }
    private int findRight(Node node, int size){
        if(node==null){
            return size;
        }
        if(node.right==null){
            size=findLeft(node.left,size);
        }if(node.left==null){
            size=findLeft(node.right,size);
        }
        else{
            size=findLeft(node.right,size);
            size=findLeft(node.left,size);
        }
        return size;
    }

    // Inserts a key-value pair into the binary search tree
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    // Private helper method for inserting a key-value pair recursively
    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value); // Insert into the left subtree
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value); // Insert into the right subtree
        } else {
            node.value = value; // Update existing node's value
        }
        return node;
    }

    // Retrieves the value associated with a given key from the binary search tree
    public V get(K key) {
        Node node = get(root, key);
        return node != null ? node.value : null;
    }

    // Private helper method for retrieving a node with a given key recursively
    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return get(node.left, key); // Search in the left subtree
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key); // Search in the right subtree
        } else {
            return node;
        }
    }


    // Deletes a node with a given key from the binary search tree
    public void delete(K key) {
        root = delete(root, key);
    }

    // Private helper method for deleting a node with a given key recursively
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key); // Delete from the left subtree
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key); // Delete from the right subtree
        } else {
            size--;
            if (node.left == null) {
                return node.right; // Replace with right child (or null if no right child)
            } else if (node.right == null) {
                return node.left; // Replace with left child
            } else {
                Node successor = findMinimum(node.right); // Find the minimum node in the right subtree
                node.key = successor.key; // Replace key with successor's key
                node.value = successor.value; // Replace value with successor's value
                node.right = delete(node.right, successor.key); // Delete the successor node from the right subtree
            }
        }
        return node;
    }

    // Finds the minimum node in a given subtree
    private Node findMinimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMinimum(node.left); // Continue searching in the left subtree
    }

    // Returns an iterator over the keys in the binary search tree
    public Iterator<K> iterator() {
        return new InOrderTraversal();
    }

    // Private inner class for implementing the in-order traversal
    private class InOrderTraversal implements Iterator<K> {
        private Node current; // Current node being visited
        private final Stack<Node> stack; // Stack for storing nodes during traversal


        public InOrderTraversal() {
            current = root; // Set the current node to the root of the binary search tree
            stack = new Stack<>(); // Create a new stack to store nodes during traversal
        }

        public boolean hasNext() {
            return !stack.isEmpty() || current != null; // Checks if there are more elements to iterate over
        }

        public K next() {
            while (current != null) {
                stack.push(current); // Push nodes onto the stack while traversing to the leftmost node
                current = current.left;
            }
            if (stack.isEmpty()) {
                throw new NoSuchElementException(); // No more elements to iterate
            }
            Node node = stack.pop(); // Retrieve the next node from the stack
            current = node.right; // Move to the right subtree of the current node
            return node.key; // Return the key of the visited node
        }
    }
}