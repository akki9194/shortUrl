package com.shortlink.link.Controller;

/**
 * @author Akash Hirke
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    /**
     * <b>Initially its call to load home page</b>
     * <b>It is set modal and view</b>
     * @return Home Page address
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }
}
