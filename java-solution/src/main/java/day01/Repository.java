package day01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Repository<T extends Identifiable> {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    Map<Long, T> store;
    Integer capacity;

    Repository(int capacity){
        store = new ConcurrentHashMap<>(capacity);
    }

    void save(T entity){
        lock.writeLock().lock();
        try {
            Long key = entity.getId();

            if (!store.containsKey(key) && store.size() == capacity) {
                throw new ArrayIndexOutOfBoundsException("");
            }

            store.put(entity.getId(), entity);
        } finally {
            lock.writeLock().unlock();
        }
    }

    T get(Long id){
        lock.readLock().lock();
        try {
            return store.get(id);
        } finally {
            lock.readLock().unlock();
        }
    }
}
