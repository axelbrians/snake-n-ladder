package org.familia.client.apps.networks.response.user;

import java.io.Serializable;

public class UserResponse implements Serializable {

    public String username;

    public UserResponse(String username) {
        this.username = username;
    }
}
