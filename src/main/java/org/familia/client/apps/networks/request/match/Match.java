package org.familia.client.apps.networks.request.match;

import org.familia.client.apps.networks.request.user.User;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Match  implements Serializable {
    @NotNull
    public User user;
    @NotNull
    public MatchType type;

    public Match(@NotNull User user, @NotNull MatchType type) {
        this.user = user;
        this.type = type;
    }
}
