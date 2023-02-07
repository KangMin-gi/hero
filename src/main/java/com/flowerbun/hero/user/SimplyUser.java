package com.flowerbun.hero.user;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SimplyUser {

    private String userName;
    private LocalDateTime createDate;

    public SimplyUser(User user) {
        this.userName = user.getUserName();
        this.createDate = user.getCreateDate();
    }
}
