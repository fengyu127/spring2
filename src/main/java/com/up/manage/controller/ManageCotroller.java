package com.up.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/2/21.
 */
@Controller
@RequestMapping("manage")
public class ManageCotroller {

    @RequestMapping("Index")
    public String index()
    {
        return "main/index";
    }
}
