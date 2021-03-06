package com.project.elsign.controller;


import com.project.elsign.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller for navigation containing simple methods get
 */
@Controller
public class NavigationController {

    @Autowired
    private DocumentService documentService;


    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String main(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("locales.messages", locale);
        model.addAttribute("keys", bundle.getKeys());
        model.addAttribute("documents", documentService.getAllDocuments());
        return "/welcome";
    }



    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "/about";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        return "/profile";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account() {
        return "redirect:/account/" + SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
