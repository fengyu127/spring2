package com.admin;

import com.up.controller.FirstController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/23.
 */
@Controller
@RequestMapping(value="admin")
public class controller {
    private static Log logger = LogFactory.getLog(FirstController.class);
    @RequestMapping(value="get")
    public @ResponseBody
    String jumpToIndex(@RequestBody String req, HttpServletResponse response) throws IOException {
        logger.info(req);
        return "demaxiyan";
    }
}
