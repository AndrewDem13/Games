package com.javarush.test.level33.lesson15.big01;


import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

public class Shortener
{
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy)
    {
        this.storageStrategy = storageStrategy;
    }

    // будет возвращать идентификатор id для заданной строки.
    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string))
            return storageStrategy.getKey(string);
        else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    // будет возвращать строку для заданного идентификатора или null, если передан неверный идентификатор.
    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
