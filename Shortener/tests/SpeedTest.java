package com.javarush.test.level33.lesson15.big01.tests;


import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();
        for (String string : strings)
            ids.add(shortener.getId(string));
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();
        for (Long id : ids)
            strings.add(shortener.getString(id));
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> origIds1 = new HashSet<>();
        Set<Long> origIds2 = new HashSet<>();
        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        long idSpeed1 = getTimeForGettingIds(shortener1, origStrings, origIds1);
        long idSpeed2 = getTimeForGettingIds(shortener2, origStrings, origIds2);

        Assert.assertTrue(idSpeed1 > idSpeed2);

        long stringsSpeed1 = getTimeForGettingStrings(shortener1, origIds1, origStrings);
        long stringsSpeed2 = getTimeForGettingStrings(shortener2, origIds2, origStrings);

        Assert.assertEquals(stringsSpeed1, stringsSpeed2, 5);
    }
}
