import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Создаем таблицу с бОльшим количеством бакетов для 10000 элементов
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(100);
        Random random = new Random();

        // Добавляем 10000 случайных элементов
        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(100000);
            String randomName = "Student" + random.nextInt(1000);
            MyTestingClass key = new MyTestingClass(randomId, randomName);
            table.put(key, "Value " + i);
        }

        // Проверяем распределение
        table.printBucketSizes();
    }
}