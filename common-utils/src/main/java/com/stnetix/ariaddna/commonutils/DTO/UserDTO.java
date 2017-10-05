package com.stnetix.ariaddna.commonutils.DTO;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by alexkotov on 13.09.17.
 */
public class UserDTO implements Serializable {
    private UUID uuid;

    private String nickname;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
