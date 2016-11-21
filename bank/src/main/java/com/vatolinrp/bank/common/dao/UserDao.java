package com.vatolinrp.bank.common.dao;

import com.vatolinrp.bank.common.model.User;

public interface UserDao
{
  User getUserIfExsist( String name, String password );
}
