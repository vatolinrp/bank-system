package com.vatolinrp.bank.common.dao;

import com.vatolinrp.bank.common.model.User;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class UserDaoImpl implements UserDao
{
  @Value("${jdbc.driver}")
  private String driver;

  @Value("${jdbc.url}")
  private String url;

  @Value("${jdbc.login}")
  private String username;

  @Value("${jdbc.password}")
  private String password;

  @Override
  public User getUserIfExsist( String login, String password )
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    User user = null;
    ResultSet resultSet = null;
    try {
      Class.forName( driver );
      connection = DriverManager.getConnection( url, username, password );
      preparedStatement = connection.prepareStatement( "SELECT LOGIN, PASSWORD, ROLE FROM USERS WHERE LOGIN=? AND PASSWORD=?" );
      preparedStatement.setString( 1, login );
      preparedStatement.setString( 2, password );
      resultSet = preparedStatement.executeQuery();
      if ( resultSet.next() ) {
        String loginFetched = resultSet.getString( 1 );
        String passwordFetched = resultSet.getString( 2 );
        String roleFetched = resultSet.getString( 3 );
        user = new User();
        user.setLogin( loginFetched );
        user.setPassword( passwordFetched );
        user.setRole( roleFetched );
      }
    } catch( Exception e ) {
      e.printStackTrace();
    } finally {
      try {
        resultSet.close();
        preparedStatement.close();
        connection.close();
      } catch( Exception e ) {
        e.printStackTrace();
      }
    }
    return user;
  }
}
