package com.github.niwaniwa.Web.control

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

    @RequestMapping("/")
    fun index(model: Model) : String {
        model.addAttribute("message", "hoge");
        return "index";
    }

}