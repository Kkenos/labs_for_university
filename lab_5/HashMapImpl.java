package lab_5;


import java.util.LinkedList;
import java.util.ArrayList;



public class HashMapImpl<V> implements HashMap<String, V> {
    private static final int TABLE_SIZE = 300007;
    private static final int A = 31;

    private class Data<V> {
        String key;
        V value;

        Data(String key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<LinkedList<Data<V>>> map;

    HashMapImpl() {
        map = new ArrayList<>(TABLE_SIZE);
        for (int i = 0; i < TABLE_SIZE; i++) {
            map.add(null);
        }
    }

    public long hashByString(String value) {
        long hash = 0;
        for (Character ch : value.toCharArray()) {
            hash = (hash * A + ch) % TABLE_SIZE;
        }
        return hash;
    }

    @Override
    public void put(String key, V value) {
        int index = (int)hashByString(key);
        LinkedList<Data<V>> list = map.get(index);
        if(list == null){
            list = new LinkedList<>();
            map.set(index,list);
        }
        else{
            for(Data<V> i : list){
                if(i.key.equals(key)){
                    i.value = value;
                    return;
                }
            }
        }
        list.add(new Data<>(key, value));
    }

    @Override
    public V getValue(String key) {
        int index = (int) hashByString(key);
        LinkedList<Data<V>> list = map.get(index);
        if(list == null){
            return null;
        }
        else{
            for(Data<V> i : list){
                if(i.key.equals(key)){
                    return i.value;
                }
            }
        }
        return null;
    }

    @Override
    public V delete(String key) {
        int index = (int) hashByString(key);
        LinkedList<Data<V>> list = map.get(index);
        if (list == null){
            return null;
        }
        else{
            for(int i = 0;i<list.size();i++){
                Data<V> data = list.get(i);
                if (data.key.equals(key)){
                    V value = data.value;
                    list.remove(i);
                    return value;
                }
            }
        }
        return null;
    }

}