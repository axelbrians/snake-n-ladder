package org.familia.client.apps.networks.request.user;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Message implements Serializable {
    @NotNull
    public String sender;
    public String text;

    public Message(@NotNull String sender, @NotNull String text) {
        this.sender = sender;
        this.text = text;
    }
}
