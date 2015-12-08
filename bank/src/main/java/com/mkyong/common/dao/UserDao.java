package com.mkyong.common.dao;

import com.mkyong.common.model.User;

public interface UserDao
{
    User getUserIfExsist(String name, String password);
}
