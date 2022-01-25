package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.AdminMapper;
import cn.tgkzxy.pojo.Admin;
import cn.tgkzxy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin queryAdminByName(String name) {
        return adminMapper.queryAdminByName(name);
    }
}
