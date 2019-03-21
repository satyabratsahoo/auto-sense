package com.thunderbolt.autosense.models;

import org.springframework.web.servlet.ModelAndView;

public class RedirectModelView {
    public static ModelAndView redirectMV(String view){
        ModelAndView model = new ModelAndView();
        model.setViewName(view);
        return model;
    }
}
