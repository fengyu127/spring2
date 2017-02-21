package com.up.manage.service;

import com.up.manage.model.Manage;
import com.up.manage.model.TreeManage;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public interface ImanageService {
    public Manage getManagebyid(int id);
    public List<Manage> getManagebyPid(int pid);
    public TreeManage getallManagebyid(int id);
}
