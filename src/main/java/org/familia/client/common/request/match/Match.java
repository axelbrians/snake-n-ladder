package org.familia.client.common.request.match;

import org.familia.client.common.request.user.User;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Match  implements Serializable {
    @NotNull
    public User user;
    @NotNull
    public Integer type;

    public Match(@NotNull User user, @NotNull Integer type) {
        this.user = user;
        this.type = type;
    }
}
