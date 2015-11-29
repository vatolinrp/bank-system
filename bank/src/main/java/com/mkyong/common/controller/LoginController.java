package com.mkyong.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class LoginController extends AbstractController
{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception
    {
        ModelAndView model = new ModelAndView("WelcomePage");
        String login = request.getParameter("login");
        if(login.equals("inspector"))
        {
            model = new ModelAndView("InspectorPage");
        }

        if(login.equals("referent"))
        {
            model = new ModelAndView("ReferentPage");
        }

        return model;
    }

}