package com.mkyong.common.dao;

import com.mkyong.common.model.Request;

import java.util.List;

public interface RequestDao
{
    List<Request> getAllRequests();

    void create(Request request);

    void update(Request request);

    Request getById(Long id);
}
