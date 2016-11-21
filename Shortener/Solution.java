package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String s : strings)
            result.add(shortener.getId(s));
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long l : keys)
            result.add(shortener.getString(l));
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        System.out.println(strategy.getClass().getSimpleName());

        Set<String> stringSet = new HashSet<>();
        for (long counter = 0L; counter < elementsNumber; counter++)
            stringSet.add(Helper.generateRandomString());

        Shortener shortener = new Shortener(strategy);

        Set<Long> idSet = new HashSet<>();
        Date start1 = new Date();
        idSet = getIds(shortener, stringSet);
        Date finish1 = new Date();
        long getIdsDelay = finish1.getTime() - start1.getTime();
        System.out.println(getIdsDelay);

        Set<String> stringsSet = new HashSet<>();
        Date start2 = new Date();
        stringsSet = getStrings(shortener, idSet);
        Date finish2 = new Date();
        long getStringsDelay = finish2.getTime() - start2.getTime();
        System.out.println(getStringsDelay);

        if (stringsSet.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 1000);
        testStrategy(new OurHashMapStorageStrategy(), 1000);
        testStrategy(new OurHashBiMapStorageStrategy(), 1000);
        testStrategy(new HashBiMapStorageStrategy(), 1000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 1000);
        testStrategy(new FileStorageStrategy(), 100);
    }
}
