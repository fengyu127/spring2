package com.up.manage.service.Impl;

import com.up.manage.dao.ManageMapper;
import com.up.manage.model.Manage;
import com.up.manage.model.TreeManage;
import com.up.manage.service.ImanageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
@Service
public class manageService implements ImanageService{

    @Resource
    private ManageMapper manageMapper;

    @Override
    public Manage getManagebyid(int id) {
        return manageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Manage> getManagebyPid(int pid) {
        return manageMapper.getManagebyPid(pid);
    }



    @Override
    public TreeManage getallManagebyid(int id) {
          Manage manage=this.getManagebyid(id);
          List<Manage> manages=this.getManagebyPid(id);
          TreeManage treeManage=new TreeManage();
          List<TreeManage> treeManages=new ArrayList<TreeManage>();
          if(manages.size()!=0)
          {
              for(int i=0;i<manages.size();i++)
              {
                  TreeManage treeManage1=new TreeManage();
                  treeManage1=getallManagebyid(manages.get(i).getId());
                  treeManages.add(treeManage1);
              }
          }
          treeManage.setList(treeManages);
          treeManage.setManage(manage);
          return treeManage;
    }
}
