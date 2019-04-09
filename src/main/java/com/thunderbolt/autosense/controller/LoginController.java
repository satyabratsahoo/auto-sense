package com.thunderbolt.autosense.controller;


import com.thunderbolt.autosense.beans.UserLoginBean;
import com.thunderbolt.autosense.utils.UserLoginUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
    final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value={"/"},method = RequestMethod.GET)
    public ModelAndView homePage(
            @CookieValue(value="_sessionToken",defaultValue = "null") String _sessionToken
    ) throws IOException {

            ModelAndView model = new ModelAndView("home");
            return model;


    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView validateLogin(@RequestParam String userid,@RequestParam String password,
                                      @ModelAttribute("userDetails") UserLoginBean loginBean,
                                      HttpServletResponse response)
            throws IOException {
        ModelAndView model = new ModelAndView();
        loginBean = UserLoginUtils.userLogin(userid,password,"asshole");
        logger.info(loginBean.getMessage_id() + ":"+loginBean.getMessage_code());

        if (!loginBean.getMessage_id().equals("100")){


        model.addObject("message_color", "red");
        model.addObject("message", loginBean.getError_message());
        model.addObject("message_icon", "fa fa-exclamation-circle");

        model.setViewName("home");
        return model;
    }
        else{

            model.addObject("userDetails",loginBean);
            model.setViewName("success");
            Cookie _sessionToken = new Cookie("_sessionToken",loginBean.getValue().getSession_token());
            _sessionToken.setMaxAge(10000);
            response.addCookie(_sessionToken);
            return model;

        }


    }}



