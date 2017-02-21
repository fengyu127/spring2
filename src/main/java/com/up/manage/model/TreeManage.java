package com.up.manage.model;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public class TreeManage {
   private List<TreeManage> list;
   private Manage manage;

   public Manage getManage() {
      return manage;
   }

   public void setManage(Manage manage) {
      this.manage = manage;
   }

   public List<TreeManage> getList() {
      return list;
   }

   public void setList(List<TreeManage> list) {
      this.list = list;
   }
}
