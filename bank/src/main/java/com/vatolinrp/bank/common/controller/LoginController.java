package com.vatolinrp.bank.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vatolinrp.bank.common.dao.UserDao;
import com.vatolinrp.bank.common.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController
{
  @Resource
  private UserDao userDao;

  @Resource
  private CreditRequestController creditRequestController;

  @RequestMapping(value = {
    "/", "/goback"
  }, method = RequestMethod.GET)
  public ModelAndView showFrom( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    HttpSession session = request.getSession( false );
    if ( session != null ) {
      session.invalidate();
    }
    return new ModelAndView( "WelcomePage" );
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ModelAndView doLogin( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    String login = request.getParameter( "login" );
    String password = request.getParameter( "password" );
    User user = userDao.getUserIfExsist( login, password );

    ModelAndView model = new ModelAndView();
    if ( user != null ) {
      HttpSession httpSession = request.getSession();
      httpSession.setAttribute( "user", user );
      switch( user.getRole() ) {
        case "clerk": {
          httpSession.setAttribute( "page", "ClerkPage" );
          return creditRequestController.showAllRequests( request, response, model );
        }
        case "referent": {
          httpSession.setAttribute( "page", "ReferentPage" );
          return creditRequestController.showAllRequests( request, response, model );
        }
        case "employee": {
          httpSession.setAttribute( "page", "EmployeePage" );
          return creditRequestController.showAllRequests( request, response, model );
        }
        case "inspector": {
          httpSession.setAttribute( "page", "InspectorPage" );
          return creditRequestController.showAllRequests( request, response, model );
        }
      }
    }
    return new ModelAndView( "WelcomePage" );
  }

}
