package com.up.mybatis.service.Impl;

import com.up.mybatis.dao.StudentMapper;
import com.up.mybatis.model.Student;
import com.up.mybatis.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/7.
 */
@Service("IStudentService")
public class StudentServeceImpl implements IStudentService {

    @Resource
    private StudentMapper mapper;
    @Override
    public Student getStudentByid(int Id) {
        return this.mapper.selectByPrimaryKey(Id);
    }

    @Override
    public  int setclazzById(int id)
    {
        return this.mapper.updateclazzById(id);
    }
}
