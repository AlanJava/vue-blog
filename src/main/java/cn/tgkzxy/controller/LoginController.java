package cn.tgkzxy.controller;

import cn.tgkzxy.pojo.Admin;
import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.service.AdminService;
import cn.tgkzxy.service.LogsService;
import cn.tgkzxy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    AdminService adminService;
    @Autowired
    LogsService logsService;
    @GetMapping("/login")
    public Admin login(Admin admin){

        String USERNAME = admin.getName();
        //md5加密
        String PASSWORD = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));
        Admin resultAdmin = adminService.queryAdminByName(USERNAME);
        if(ObjectUtils.isEmpty(resultAdmin)){
            return null;
        }
        else if (resultAdmin.getPassword().equals(PASSWORD)){
            //添加token
            admin.setToken(JwtUtil.createToken());
            Date date = new Date();
            long time = date.getTime();
            Logs logs = new Logs();
            logs.setTime(time);
            logs.setAction("登录后台");
            logsService.addLog(logs);
            return admin;
        }
        else {
            return null;
        }
    }
    @GetMapping("/checkToken")
    public boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }
}
