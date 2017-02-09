package com.up.mybatis.service;

import com.up.mybatis.model.Student;

/**
 * Created by Administrator on 2017/2/7.
 */
public interface IStudentService {

    public Student getStudentByid(int Id);
    public  int setclazzById(int id);
}
