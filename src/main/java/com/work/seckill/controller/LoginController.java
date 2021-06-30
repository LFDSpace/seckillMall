package com.work.seckill.controller;

import com.work.seckill.base.result.Result;
import com.work.seckill.service.UserService;
import com.work.seckill.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Resource
    UserService userService;
    @Resource
    ThymeleafViewResolver thymeleafViewResolver;

    @GetMapping("/v1/visitor/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model){
        String html;
        WebContext ctx = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("login",ctx);
        return html;
    }

//@Valid用于验证注解是否符合要求
    @PostMapping("/v1/visitor/login")
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVO){
        log.info(loginVO.toString());
        String token = userService.login(response,loginVO);
        return Result.success(token);
    }
}
