package com.nhimrane.laboratoire5;

public class DaoSingleton {
    private static TachesDao daoInstance = null;
    public static TachesDao getInstance() {
        if (daoInstance==null)
            daoInstance = new TachesDao();
        return daoInstance;
    }
}
