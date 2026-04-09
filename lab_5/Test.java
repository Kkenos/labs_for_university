package lab_5;

public class Test {
    public static void main(String[] args) {
        // Создаём экземпляр нашей хеш-таблицы
        HashMapImpl<String> map = new HashMapImpl<>();

        // Тестируем по шагам из примера
        map.put("hello", "world");
        map.put("name", "ilya");

        // Вызываем get и выводим результаты
        System.out.println(map.getValue("hello"));  // world
        System.out.println(map.getValue("ilya"));   // null

        map.delete("hello");

        System.out.println(map.getValue("hello"));  // null
        System.out.println(map.getValue("name"));   // ilya

        map.put("name", "vasya");
        System.out.println(map.getValue("name"));   // vasya
    }
}
