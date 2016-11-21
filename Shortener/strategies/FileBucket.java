package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("tmp", null);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try {
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(entry);
            fos.close();
            outputStream.close();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        Entry entry = null;
        if (getFileSize() > 0)
        {
            try {
                FileInputStream fis = new FileInputStream(path.toFile());
                ObjectInputStream ois = new ObjectInputStream(fis);
                entry = (Entry) ois.readObject();
                ois.close();
                fis.close();
            }
            catch (Exception e) { ExceptionHandler.log(e); }
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        }
        catch (Exception e) { ExceptionHandler.log(e); }
    }
}
