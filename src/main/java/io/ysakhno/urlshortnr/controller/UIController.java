package io.ysakhno.urlshortnr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Small controller for the UI stuff.
 *
 * @author Yuri Sakhno
 */
@Controller
public class UIController {

    /**
     * The mapping for the index page.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
