package com.up.mybatis.controller;

import com.up.mybatis.model.Student;
import com.up.mybatis.service.IStudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/7.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {

    private static Log logger = LogFactory.getLog(StudentController.class);

    @Resource
    private IStudentService IStudentService;

    @RequestMapping(value = "getbyid")
    public
    @ResponseBody
    String jumpToIndex(@RequestBody String req, HttpServletResponse response) throws IOException {
        logger.info(req);
        int id = Integer.valueOf(req);
        Student student = IStudentService.getStudentByid(id);
        logger.info(student.getName());
        return student.getName();

    }
}

