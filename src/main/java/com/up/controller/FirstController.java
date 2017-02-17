package com.up.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping(value = "first")
public class FirstController {
    private static Log logger = LogFactory.getLog(FirstController.class);

    @RequestMapping(value = "in")
    public
    @ResponseBody
    String jumpToIndex(@RequestBody String req, HttpServletResponse response) throws IOException {
        logger.info(req);
        return "demaxiyan";
    }
}