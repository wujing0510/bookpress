package com.yikeo.bookpress.modules.sys.web;

import com.yikeo.bookpress.common.web.BaseController;
import com.yikeo.bookpress.modules.sys.dto.RegisterVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("sys.web.account")
@RequestMapping("account")
public class AccountController extends BaseController {

    @RequestMapping(value = "login")
    public String login(Model model) {
        return "sys/account/login";
    }

    @RequestMapping(value = "register")
    public String register(Model model) {
        model.addAttribute("registerVo", new RegisterVo());
        return "sys/account/register";
    }

    @RequestMapping(value = "findPws")
    public String findPws(Model model) {
        return "sys/account/findPws";
    }
}
