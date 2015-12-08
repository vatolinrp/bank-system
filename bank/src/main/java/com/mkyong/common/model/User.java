package com.mkyong.common.model;

import java.io.Serializable;

public class User implements Serializable
{
    private String login;
    private String password;
    private String role;

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (!login.equals(user.login))
            return false;
        if (!password.equals(user.password))
            return false;
        return role.equals(user.role);

    }

    @Override
    public int hashCode()
    {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "User{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", role='" + role + '\'' + '}';
    }
}
