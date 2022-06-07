package org.familia.client.networks;

import java.io.ObjectInputStream;

public class InputSocketThread extends Thread {
    private ObjectInputStream objectInputStream;

    public InputSocketThread(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
}
