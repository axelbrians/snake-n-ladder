package org.familia.client.networks;

import org.familia.client.providers.DataProvider;

import java.io.ObjectInputStream;

public class InputSocketThread extends Thread {
    private ObjectInputStream objectInputStream;
    public DataProvider dataProvider;

    public InputSocketThread(ObjectInputStream objectInputStream, DataProvider dataProvider) {
        this.objectInputStream = objectInputStream;
        this.dataProvider = dataProvider;
    }
}
