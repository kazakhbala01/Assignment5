public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "Value 5");
        tree.put(2, "Value 2");
        tree.put(7, "Value 7");
        tree.put(1, "Value 1");
        tree.put(3, "Value 3");

        for (var key : tree) {
            System.out.println("Key: " + key + " Value: " + tree.get(key));
        }

        tree.delete(3);
        tree.delete(5);
        System.out.println();

        for (var key : tree) {
            System.out.println("Key: " + key + " Value: " + tree.get(key));
        }
    }
}