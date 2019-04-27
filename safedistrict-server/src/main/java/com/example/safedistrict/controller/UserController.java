package com.example.safedistrict.controller;

import com.example.safedistrict.entity.ResultInfo;
import com.example.safedistrict.entity.User;
import com.example.safedistrict.service.UserServer;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserServer userServer;

    @RequestMapping("/login")
    public Object login(String userName,String  userPsd) {
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(userName)){
            rsInfo.setMessage("管理员昵称不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }else if (StringUtil.isEmpty(userPsd)){
            rsInfo.setMessage("管理员密码不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }else {
            List<User> userList = userServer.selectByPrimaryKey(userName,userPsd);
            if (userList != null && userList.size()>0){
                rsInfo.setResult(userList);
                rsInfo.setMessage("登录成功！");
                rsInfo.setCode("0");
            }else {
                rsInfo.setMessage("密码错误或者管理员账号不存在！");
                rsInfo.setCode("-1");
            }
            return rsInfo;
        }
    }

    @RequestMapping("/saveUser")
    public Object saveUser(String userName,String  userPsd){
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(userName)){
            rsInfo.setMessage("管理员昵称不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }else if (StringUtil.isEmpty(userPsd)){
            rsInfo.setMessage("管理员密码不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }else {
            List<User> userList = userServer.selectByPrimaryKey(userName,userPsd);
            if (userList != null && userList.size()>0){
                rsInfo.setMessage("管理员账号已存在！");
                rsInfo.setCode("-1");
            }else {
                User user = new User();
                user.setUserName(userName);
                user.setUserPsd(userPsd);
                userServer.insert(user);
                rsInfo.setMessage("添加成功！");
                rsInfo.setCode("0");
            }
        }
        return rsInfo;
    }

    @RequestMapping("/updateUser")
    public Object updateUser(User user){
        ResultInfo rsInfo = new ResultInfo();
        String str = user.getUserId() + "";
        if (str != null && "".equals(str)) {
            rsInfo.setMessage("管理员id不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(user.getUserName())){
            rsInfo.setMessage("管理员昵称不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(user.getUserPsd())){
            rsInfo.setMessage("管理员密码不能为空！");
            rsInfo.setCode("-1");
        }else {
            List<User> userList = userServer.selectById(user.getUserId());
            if (userList != null && userList.size()>0){
                userServer.updateByPrimaryKey(user);
                rsInfo.setMessage("修改成功！");
                rsInfo.setCode("0");
            }else {
                rsInfo.setMessage("管理员账号不存在！");
                rsInfo.setCode("-1");
            }
        }
        return rsInfo;
    }

    @RequestMapping("/deleteUser")
    public Object deleteUser(User user){
        ResultInfo rsInfo = new ResultInfo();
        String str = user.getUserId() + "";
        if (str != null && "".equals(str)) {
            rsInfo.setMessage("管理员id不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(user.getUserName())){
            rsInfo.setMessage("管理员昵称不能为空！");
            rsInfo.setCode("-1");
        }else {
            List<User> userList = userServer.selectById(user.getUserId());
            if (userList != null && userList.size()>0){
                userServer.deleteByPrimaryKey(userList.get(0).getUserId());
                rsInfo.setCode("0");
                rsInfo.setMessage("删除成功！");
            }else {
                rsInfo.setMessage("管理员账号不存在！");
                rsInfo.setCode("-1");
            }
        }
        return rsInfo;
    }
}
