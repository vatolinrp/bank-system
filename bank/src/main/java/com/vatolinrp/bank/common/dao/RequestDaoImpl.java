package com.vatolinrp.bank.common.dao;

import com.vatolinrp.bank.common.model.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class RequestDaoImpl implements RequestDao
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
  public List<Request> getAllRequests()
  {
    Connection connection = null;
    Statement statement = null;
    List<Request> requestList = new ArrayList<>();
    Request request = null;
    ResultSet resultSet = null;
    try {
      Class.forName( driver );
      connection = DriverManager.getConnection( url, username, password );
      statement = connection.createStatement();
      boolean isSuccess = statement.execute( "select ID, CONTENT, APPROVED_BY_REFERENT, CRED_REPORT,"
        + " ACC_STATE, APPROVED_BY_EMPLOYEE, APPROVED_BY_INSPECTOR, CONDITIONS, CREATED from requests" );
      if ( isSuccess ) {
        resultSet = statement.getResultSet();
        while ( resultSet.next() ) {
          request = new Request();
          request.setId( resultSet.getLong( 1 ) );
          request.setContent( resultSet.getString( 2 ) );
          request.setApprByRef( new Boolean( resultSet.getString( 3 ) ) );
          request.setCredReport( resultSet.getString( 4 ) );
          request.setAccState( resultSet.getString( 5 ) );
          request.setApprByEmp( new Boolean( resultSet.getString( 6 ) ) );
          request.setApprByInsp( new Boolean( resultSet.getString( 7 ) ) );
          request.setConditions( resultSet.getString( 8 ) );
          request.setCreated( resultSet.getString( 9 ) );
          requestList.add( request );
        }
      }
    } catch( Exception e ) {
      e.printStackTrace();
    } finally {
      try {
        resultSet.close();
        statement.close();
        connection.close();
      } catch( Exception e ) {
        e.printStackTrace();
      }
    }
    return requestList;
  }

  @Override
  public void create( Request request )
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      Class.forName( driver );
      connection = DriverManager.getConnection( url, username, password );
      preparedStatement = connection.prepareStatement( "insert into REQUESTS (content, Approved_By_Referent,"
        + " APPROVED_BY_EMPLOYEE, APPROVED_BY_INSPECTOR, CREATED) VALUES (?,?,?,?,?)" );
      preparedStatement.setString( 1, request.getContent() );
      preparedStatement.setString( 2, "false" );
      preparedStatement.setString( 3, "false" );
      preparedStatement.setString( 4, "false" );
      preparedStatement.setString( 5, "false" );
      preparedStatement.executeQuery();
    } catch( Exception e ) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch( Exception e ) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void update( Request request )
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      Class.forName( driver );
      connection = DriverManager.getConnection( url, username, password );
      preparedStatement = connection.prepareStatement( "UPDATE REQUESTS SET CONTENT=?, APPROVED_BY_REFERENT=?, "
        + "CRED_REPORT=?, ACC_STATE=?, APPROVED_BY_EMPLOYEE=?, APPROVED_BY_INSPECTOR=?, "
        + "CONDITIONS=?, CREATED=? WHERE ID=?" );
      preparedStatement.setString( 1, request.getContent() );
      preparedStatement.setString( 2, request.isApprByRef().toString() );
      preparedStatement.setString( 3, request.getCredReport() );
      preparedStatement.setString( 4, request.getAccState() );
      preparedStatement.setString( 5, request.isApprByEmp().toString() );
      preparedStatement.setString( 6, request.isApprByInsp().toString() );
      preparedStatement.setString( 7, request.getConditions() );
      preparedStatement.setString( 8, request.isCreated() );
      preparedStatement.setLong( 9, request.getId() );
      preparedStatement.executeQuery();
    } catch( Exception e ) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch( Exception e ) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Request getById( Long id )
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    List<Request> requestList = new ArrayList<>();
    Request request = null;
    ResultSet resultSet = null;
    try {
      Class.forName( driver );
      connection = DriverManager.getConnection( url, username, password );
      preparedStatement = connection.prepareStatement( "select ID, CONTENT, APPROVED_BY_REFERENT, CRED_REPORT,"
        + " ACC_STATE, APPROVED_BY_EMPLOYEE, APPROVED_BY_INSPECTOR, CONDITIONS, CREATED from requests where id=?" );
      preparedStatement.setLong( 1, id );
      resultSet = preparedStatement.executeQuery();
      if ( resultSet.next() ) {
        request = new Request();
        request.setId( resultSet.getLong( 1 ) );
        request.setContent( resultSet.getString( 2 ) );
        request.setApprByRef( new Boolean( resultSet.getString( 3 ) ) );
        request.setCredReport( resultSet.getString( 4 ) );
        request.setAccState( resultSet.getString( 5 ) );
        request.setApprByEmp( new Boolean( resultSet.getString( 6 ) ) );
        request.setApprByInsp( new Boolean( resultSet.getString( 7 ) ) );
        request.setConditions( resultSet.getString( 8 ) );
        request.setCreated( resultSet.getString( 9 ) );
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
    return request;
  }
}
