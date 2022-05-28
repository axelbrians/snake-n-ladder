package org.familia.client.apps.networks.response.user;

import java.io.Serializable;

public class MessageResponse implements Serializable {
    public String sender;
    public String text;

    public MessageResponse(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }
}
