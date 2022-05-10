package org.familia.client.common.request;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Request implements Serializable {

    @NotNull
    public Serializable payload;

    public Request(@NotNull Serializable payload) {
        this.payload = payload;
    }
}