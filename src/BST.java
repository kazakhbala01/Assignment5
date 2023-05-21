public class BST<K extends Comparable<K>,V> {
    private Node root;
    private int size;

    private class Node{
        private K key;
        private V val;
        public Node(K key, V val){
            this.key=key;
            this.val=val;
        }
    }
    public void put(K key, V val){
        root=put(root,key,val);
    }
    private Node put(Node node,K key, V val){
        if(node==null){
            size++;
            return new Node(key,val);
        }
    }
    public V get(K key){

    }
    public void delete(K key){

    }
    public Iterable<K> iterator(){

    }

}
