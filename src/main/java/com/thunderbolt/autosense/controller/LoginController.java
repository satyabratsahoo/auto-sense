package com.thunderbolt.autosense.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;


@Controller
public class LoginController {
    final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        return model;

    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView validateLogin(@RequestParam String userid,@RequestParam String password) {
        ModelAndView model = new ModelAndView();
        model.addObject("message_color", "red");
        model.addObject("message", "Invalid Credentials");
        model.addObject("message_icon", "fa fa-exclamation-circle");

        model.setViewName("home");
        return model;
    }

        @RequestMapping(value="/asdasd",method = RequestMethod.GET)
        public ModelAndView cookieTester(@CookieValue(value = "hitCounter",defaultValue = "0")Long hitCounter
                ,HttpServletResponse response) {

        hitCounter++;
        Cookie cookie = new Cookie("hitCounter",hitCounter.toString());
        response.addCookie(cookie);
            ModelAndView model = new ModelAndView();
            model.setViewName("counter");
            System.out.println("cookie called");
            return model;
    }


}
