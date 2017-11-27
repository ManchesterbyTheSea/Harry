package com.harry.web.controller;

import com.harry.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by chenhaibo on 2017/11/9.
 **/
@Controller
public class PageController {

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login(Map<String, Object> map) {
        return "login";
    }

    @RequestMapping(value={"/", "/index"})
    public String index(HttpServletRequest request, Map<String, Object> map) {
        User user = (User) request.getSession().getAttribute("userInfo");
        if (user == null) {
            return "login";
        }
        map.put("realname", user.getRealname());
        return "index";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}