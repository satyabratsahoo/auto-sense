package com.thunderbolt.autosense.controller.web;


import com.thunderbolt.autosense.beans.UserLoginBean;
import com.thunderbolt.autosense.utils.CallableProcs;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
    final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value={"/"},method = RequestMethod.GET)
    public ModelAndView homePage(
            @CookieValue(value="_sessionToken",defaultValue = "null") String _sessionToken
    )  {

            ModelAndView model = new ModelAndView("home");
            return model;


    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView validateLogin(@RequestParam String userid, @RequestParam String password,
                                      @ModelAttribute("userDetails") UserLoginBean loginBean,
                                      HttpServletResponse response,
                                      RedirectAttributes redir,
                                      HttpServletRequest request)
            throws IOException {

        ModelAndView model = new ModelAndView();
        loginBean = CallableProcs.userLogin(userid,password,request.getRemoteAddr());
//        logger.info(loginBean.getMessage_id() + ":"+loginBean.getMessage_code());


        if (!loginBean.getMessage_id().equals("100")){

            model.addObject("message_color", "red");
            model.addObject("message", loginBean.getError_message());
            model.addObject("message_icon", "fa fa-exclamation-circle");
            model.setViewName("home");
            return model;
    }
        else{

            model.setViewName("redirect:success");
            redir.addFlashAttribute("userDetails",loginBean);
            Cookie _sessionToken = new Cookie("_sessionToken",loginBean.getValue().getSession_token());
            _sessionToken.setMaxAge(10000);
            response.addCookie(_sessionToken);

            return model;
        }

    }

    @RequestMapping(value={"success"},method = RequestMethod.GET)
    public ModelAndView successPage(
            @CookieValue(value="_sessionToken",defaultValue = "null") String _sessionToken,
            @ModelAttribute("userDetails") UserLoginBean loginBean,
            HttpServletRequest request
    ) throws IOException {

        ModelAndView model = new ModelAndView();


        if (!_sessionToken.isEmpty()){
            loginBean = CallableProcs.userSession(_sessionToken);

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
                return model;
            }
        }
        else {
            model.addObject("message_color", "red");
            model.addObject("message", "Invalid Session ! Please log in");
            model.addObject("message_icon", "fa fa-exclamation-circle");

            model.setViewName("home");
            return model;
        }

    }

}



