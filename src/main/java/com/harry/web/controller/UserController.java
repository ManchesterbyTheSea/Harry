package com.harry.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.harry.web.model.User;
import com.harry.web.service.UserService;
import com.harry.web.utils.ApiRestfulResponse;
import com.harry.web.utils.ApiResult;
import com.harry.web.utils.ApiResultCode;
import com.harry.web.utils.PasswordHashUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 用户控制器
 *
 * Created by chenhaibo on 2017/11/9.
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> login(@Valid User user, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return ApiRestfulResponse.error(ApiResultCode.C1001, "参数错误");
        }
        String username = user.getUsername();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword());

        String info = "";
        try {
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return ApiRestfulResponse.success("/index");
            }
            logger.info("对用户[" + username + "]进行登录验证..验证开始");

            subject.login(token);

            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
//            redirectAttributes.addFlashAttribute("message", "未知账户");
            info = "未知账户";
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
//            redirectAttributes.addFlashAttribute("message", "密码不正确");
            info = "密码不正确";
        }catch(LockedAccountException lae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
//            redirectAttributes.addFlashAttribute("message", "账户已锁定");
            info = "账户已锁定";
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
//            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
            info = "输入错误次数过多，锁定10分钟";
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
//            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            info = "用户名或密码不正确";
        }
        if (subject.isAuthenticated()) {
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            if (authUserInfo != null) {
                subject.getSession().setAttribute("userInfo", authUserInfo);
            }
            return ApiRestfulResponse.success("/index");
        } else {
            token.clear();
            return ApiRestfulResponse.error(ApiResultCode.C1001, info);
        }
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");

        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> register(@Valid User user) {
        if (user == null) {
            return ApiRestfulResponse.success("注册失败，参数错误！");
        }
        String username = user.getUsername();
        User account = userService.selectByUsername(username);
        if (account != null) {
            return ApiRestfulResponse.success("注册失败，该用户已存在！");
        }

        String password = PasswordHashUtil.passwordHash(user.getPassword(), username);
        user.setPassword(password);
        int result = userService.insert(user);
        if (result >= 0) {
            return ApiRestfulResponse.success("注册成功！");
        }
        return ApiRestfulResponse.success("注册失败，未知错误！");
    }
}
