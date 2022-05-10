package org.familia.client.common.response.user;

import org.familia.client.common.status.Status;

import java.io.Serializable;

public class UserResponse implements Serializable {

    public String username;

    public UserResponse(String username) {
        this.username = username;
    }
}
