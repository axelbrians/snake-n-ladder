package org.familia.client.common.response;

import org.familia.client.common.status.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class Response implements Serializable {

    @Nullable
    public String message;

    @NotNull
    public Status status;

    @NotNull
    public Serializable data;

    public Response(String message, Status status, Serializable data) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
