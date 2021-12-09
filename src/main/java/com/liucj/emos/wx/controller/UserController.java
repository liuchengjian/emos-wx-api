package com.liucj.emos.wx.controller;

import cn.hutool.json.JSONUtil;
import com.liucj.emos.wx.common.util.R;
import com.liucj.emos.wx.config.shiro.JwtUtil;
import com.liucj.emos.wx.controller.form.LoginForm;
import com.liucj.emos.wx.controller.form.RegisterForm;
import com.liucj.emos.wx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api("用户模块Web接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${emos.jwt.cache-expire}")
    private int cacheExpire;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public R register(@Valid @RequestBody RegisterForm form) {
        int id = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        if (-1 == id) {
            return R.error("无法绑定超级管理员账号");
        }
        String token = jwtUtil.createToken(id);
        Set<String> permsSet = userService.searchUserPermissions(id);
        saveCacheToken(token, id);
        return R.ok("用户注册成功").put("token", token).put("permission", permsSet);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@Valid @RequestBody LoginForm form) {
        int id = userService.login(form.getCode());
        if (-1 == id) {
            return R.error("没有该用户，请先去注册！");
        }
        String token = jwtUtil.createToken(id);
        Set<String> permsSet = userService.searchUserPermissions(id);
        saveCacheToken(token, id);
        return R.ok("登录成功").put("token", token).put("permission", permsSet);
    }


    private void saveCacheToken(String token, int userId) {
        redisTemplate.opsForValue().set(token, userId + "", cacheExpire, TimeUnit.DAYS);
    }
}
