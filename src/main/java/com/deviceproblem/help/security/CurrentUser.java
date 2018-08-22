package com.deviceproblem.help.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class CurrentUser extends User {

    private com.deviceproblem.help.model.User user;

    public CurrentUser(  com.deviceproblem.help.model.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }

    public  com.deviceproblem.help.model.User getUser() {
        return user;
    }

    public int getId() {
        return user.getId();
    }
}
