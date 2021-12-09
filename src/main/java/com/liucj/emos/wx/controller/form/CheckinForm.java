package com.liucj.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel
public class CheckinForm {
    private String address;

    private String country;

    private String province;

    private String city;

    private String district;
}
