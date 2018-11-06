package com.yikeo.bookpress.modules.sys.web;

import com.yikeo.bookpress.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("sys.web.index")
@RequestMapping
public class IndexController extends BaseController {

    @RequestMapping("")
    public String index(Model model) {
        return "sys/index";
    }
}
