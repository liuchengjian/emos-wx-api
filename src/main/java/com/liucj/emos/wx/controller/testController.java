package com.liucj.emos.wx.controller;

import com.liucj.emos.wx.common.util.R;
import com.liucj.emos.wx.controller.form.TestSayHelloForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
@Api("web端提交数据")
public class testController {
    @PostMapping("/sayHello")
    @ApiOperation("测试")
    public R sayHello(@Valid @RequestBody TestSayHelloForm form) {
        return R.ok().put("message", "hello," + form.getName());
    }
}
