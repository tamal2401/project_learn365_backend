package org.learn365.course.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PrepareUserCourse<K,V>{

    private Map<K,V> structure=new ConcurrentHashMap<>();


    public void add(K key, V value){
        structure.put(key,value);
    }

    public V get(K key){
        return structure.get(key);
    }

    public void cler(){
        structure.clear();
    }

    public Map<K,V> getMap(){
        return structure;
    }

}
