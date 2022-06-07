package org.familia.client.networks;

import org.familia.client.providers.DataProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection {
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Socket socket;

    private final DataProvider dataProvider;

    public SocketConnection(DataProvider dataProvider) throws IOException {
        socket = new Socket(SocketConstant.HOST, SocketConstant.PORT);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.dataProvider = dataProvider;

        runThreadClient();
    }

    private void runThreadClient() {
        InputSocketThread inputSocketThread = new InputSocketThread(objectInputStream, dataProvider);
        inputSocketThread.start();
    }
}
