package org.familia.client.common.request.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class User implements Serializable {

    @NotNull
    public String username;

    public User(@NotNull String username) {
        this.username = username;
    }
}
