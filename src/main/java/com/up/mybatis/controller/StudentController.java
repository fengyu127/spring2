package com.up.mybatis.controller;

import com.google.gson.Gson;
import com.up.mybatis.model.Student;
import com.up.mybatis.service.IStudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {

    private static Log logger = LogFactory.getLog(StudentController.class);

    @Resource
    private IStudentService IStudentService;

    @RequestMapping(value = "getbyid", produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String jumpToIndex(@RequestBody String req, HttpServletResponse response) throws IOException {
        logger.info(req);
        int id = Integer.valueOf(req);
        Student student = IStudentService.getStudentByid(id);
        logger.info(student.getName());
        Gson gson = new Gson();
        return gson.toJson(student);
    }

    @RequestMapping(value = "setclazz")
    public
    @ResponseBody
    void setclazz(@RequestBody String req, HttpServletResponse response) throws IOException {
        logger.info(req);
        int id = Integer.valueOf(req);


        int student = IStudentService.setclazzById(id);
    }

    @RequestMapping(value = "slist", produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String getAllStudent() throws IOException {
        logger.info("get");
        List<Student> students = IStudentService.getAllStudent();
        Gson gson = new Gson();
        logger.info(gson.toJson(students));
        return gson.toJson(students);
    }

    @RequestMapping("Index")
    public String index(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            String value = cookies[0].getValue();
            if (value.equals("index")) {
                Cookie cookie = new Cookie("istable", "second");
                res.addCookie(cookie);
                return "second";
            } else {
                Cookie cookie = new Cookie("istable", "index");
                res.addCookie(cookie);
                return "index";
            }
        } else {
            Cookie cookie = new Cookie("istable", "second");
            res.addCookie(cookie);
            return "second";
        }


    }


    @RequestMapping("Session")
    public String session(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("istable") != null) {
            if (session.getAttribute("istable").equals("index")) {
                session.setAttribute("istable", "second");
                return "index";
            } else {
                session.setAttribute("istable", "index");
                return "second";
            }
        } else {
            session.setAttribute("istable", "index");
            return "second";
        }
    }


    @RequestMapping("table")
    public
    @ResponseBody
    String table() {

        String s = "[\n" +
                "  {\n" +
                "    field: \"id\",\n" +
                "    title: \"ID\"\n" +
                "  },\n" +
                "  {\n" +
                "    field: \"name\",\n" +
                "    title: \"姓名\"\n" +
                "  },\n" +
                "  {\n" +
                "    field: \"clazz\",\n" +
                "    title: \"班级\"\n" +
                "  }\n" +
                "]";
        JSONArray o = new JSONArray(s);
        logger.info("ddd");
        return o.toString();

    }


}

