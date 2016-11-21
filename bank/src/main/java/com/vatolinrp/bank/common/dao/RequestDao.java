package com.vatolinrp.bank.common.dao;

import com.vatolinrp.bank.common.model.Request;

import java.util.List;

public interface RequestDao
{
  List<Request> getAllRequests();

  void create( Request request );

  void update( Request request );

  Request getById( Long id );
}
