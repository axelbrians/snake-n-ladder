package org.familia.client.apps.networks.request;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Request implements Serializable {

    @NotNull
    public Serializable payload;

    public Request(@NotNull Serializable payload) {
        this.payload = payload;
    }
}