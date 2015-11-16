package com.neeq.contractManagementSys.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private Person user;

    public CurrentUser(Person user) {
        super(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public Person getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getRole() {
        return user.getRole();
    }

	
}
