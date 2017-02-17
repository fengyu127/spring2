package com.up.controller;


import com.up.dao.StudentEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
@RequestMapping(value = "admin")
public class controller {
    private static Log logger = LogFactory.getLog(controller.class);

    @RequestMapping(value = "get")
    public
    @ResponseBody
    String jumpToIndex(@RequestBody String req, HttpServletResponse response) throws IOException {
        logger.info(req);


        SessionFactory sessionFactory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        // 2.创建 Session
        Session session = sessionFactory.openSession();
        // 3.开启事务
        Transaction transaction = session.beginTransaction();
        // 4.执行数据库操作
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("德玛西亚");
        logger.info("dddffff");
        session.save(studentEntity);
        // 5.提交事务
        transaction.commit();
        // 6.关闭 Session
        session.close();

        // 7.关闭 SessionFactory
        sessionFactory.close();
        return "demaxiyan";
    }
}
