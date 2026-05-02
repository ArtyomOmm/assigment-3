import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТ 1: MyHashTable ===");


        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(10);
        Random random = new Random();


        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(100000);
            String name = "Name" + i;
            table.put(new MyTestingClass(randomId, name), "Value" + i);
        }


        System.out.println("Распределение элементов по бакетам:");
        table.printBucketSizes();


        MyTestingClass testKey = new MyTestingClass(999, "Special");
        table.put(testKey, "Found It!");
        System.out.println("\nПоиск ключа (999, Special): " + table.get(testKey));
        table.remove(testKey);
        System.out.println("После удаления: " + table.get(testKey));

        System.out.println("\n=== ТЕСТ 2: BST (Binary Search Tree) ===");

        BST<Integer, String> tree = new BST<>();


        tree.put(50, "Root");
        tree.put(30, "Left");
        tree.put(70, "Right");
        tree.put(20, "Left-Left");



        System.out.println("Размер дерева (size): " + tree.size());


        System.out.println("Обход дерева (In-order traversal):");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }


        System.out.println("\nУдаляем узел с ключом 30 (у которого два ребенка)");
        tree.delete(30);
        System.out.println("Новый размер дерева: " + tree.size());

        System.out.println("Обход дерева после удаления:");
        for (var elem : tree) {
            System.out.print(elem.getKey() + " ");
        }
    }
}