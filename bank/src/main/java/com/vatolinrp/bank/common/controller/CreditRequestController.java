package com.vatolinrp.bank.common.controller;

import com.vatolinrp.bank.common.dao.RequestDao;
import com.vatolinrp.bank.common.model.Request;
import com.vatolinrp.bank.common.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CreditRequestController
{
  @Resource
  private RequestDao requestDao;

  /**
   * gets all requests from database
   */
  public ModelAndView showAllRequests( HttpServletRequest request, HttpServletResponse response, ModelAndView model )
    throws Exception
  {
    String page = String.valueOf( ( request.getSession() ).getAttribute( "page" ) );
    model.setViewName( page );
    StringBuilder sb = new StringBuilder();
    List<Request> requestList = requestDao.getAllRequests();
    sb.append( "<table>" );
    for( Request requestBean : requestList ) {
      sb.append( requestBean.toHtml() );
      sb.append( "<tr><td><a href=\"/bank-1/show/request/" + requestBean.getId() + "\">show</a></td>" );
    }
    sb.append( "</table>" );
    model.addObject( "requests", sb.toString() );
    model.addObject( "name", ( (User)( request.getSession() ).getAttribute( "user" ) ).getLogin() );
    return model;
  }

  @RequestMapping(value = "/save-request", method = RequestMethod.POST)
  public ModelAndView saveRequest( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    String requestContent = request.getParameter( "request-content" );
    String requestId = request.getParameter( "request-id" );
    if ( requestId != "" ) {
      Request requestBean = new Request();
      requestBean.setId( Long.valueOf( requestId ) );
      requestBean.setApprByRef( false );
      requestBean.setContent( requestContent );
      requestDao.update( requestBean );
    } else {
      if ( requestContent != null ) {
        Request requestBean = new Request();
        requestBean.setContent( requestContent );
        requestBean.setApprByRef( false );
        requestDao.create( requestBean );
      }
    }
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/show/request/{requestId}", method = RequestMethod.GET)
  public ModelAndView showRequest( HttpServletRequest request, HttpServletResponse response,
    @PathVariable String requestId )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
    model.addObject( "contentForEdit", requestBean.getContent() );
    model.addObject( "requestId", requestBean.getId() );
    model.addObject( "apprByRef", requestBean.isApprByRef().toString() );
    model.addObject( "contentForView", requestBean.getContentForView() );
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/request-credit-report/{requestId}", method = RequestMethod.POST)
  public ModelAndView getCreditReport( HttpServletRequest request, HttpServletResponse response,
    @PathVariable String requestId )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
    if ( requestBean.isApprByRef().equals( true ) ) {
      requestBean.setCredReport( "this is a credit report" + requestBean.getId() );
      requestDao.update( requestBean );
    }
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/request-account-state/{requestId}", method = RequestMethod.POST)
  public ModelAndView getAccountState( HttpServletRequest request, HttpServletResponse response,
    @PathVariable String requestId )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
    if ( requestBean.isApprByEmp().equals( true ) ) {
      requestBean.setAccState( "this is an account state" + requestBean.getId() );
      requestDao.update( requestBean );
    }
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/request-agree-with-cond/{requestId}", method = RequestMethod.POST)
  public ModelAndView askForCreation( HttpServletRequest request, HttpServletResponse response,
    @PathVariable String requestId )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
    if ( requestBean.isApprByEmp().equals( true ) ) {
      requestBean.setCreated( "client agreed, waiting for employee" );
      requestDao.update( requestBean );
    }
    return showAllRequests( request, response, model );
  }


  @RequestMapping(value = "/request-create/{requestId}", method = RequestMethod.POST)
  public ModelAndView create( HttpServletRequest request, HttpServletResponse response, @PathVariable String requestId )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
    if ( requestBean.isCreated().length() > 1 ) {
      requestBean.setCreated( "created!" );
      requestDao.update( requestBean );
    }
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/decision-from-ref", method = RequestMethod.POST)
  public ModelAndView approveByRef( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    String requestId = request.getParameter( "request-id" );
    String decision = request.getParameter( "decision" );
    if ( requestId != "" ) {
      Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
      requestBean.setApprByRef( Boolean.valueOf( decision ) );
      requestDao.update( requestBean );
    }
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/decision-from-insp", method = RequestMethod.POST)
  public ModelAndView approveByInsp( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    String requestId = request.getParameter( "request-id" );
    String decision = request.getParameter( "decision" );
    String conditions = request.getParameter( "request-conditions" );
    if ( requestId != "" ) {
      if ( decision.equals( "true" ) ) {
        Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
        requestBean.setApprByInsp( Boolean.valueOf( decision ) );
        requestBean.setConditions( conditions );
        requestDao.update( requestBean );
      }
    }
    return showAllRequests( request, response, model );
  }

  @RequestMapping(value = "/decision-from-emp", method = RequestMethod.POST)
  public ModelAndView approveByEmp( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView();
    String requestId = request.getParameter( "request-id" );
    String decision = request.getParameter( "decision" );
    if ( requestId != "" ) {
      Request requestBean = requestDao.getById( Long.valueOf( requestId ) );
      requestBean.setApprByEmp( Boolean.valueOf( decision ) );
      requestDao.update( requestBean );
    }
    return showAllRequests( request, response, model );
  }
}
