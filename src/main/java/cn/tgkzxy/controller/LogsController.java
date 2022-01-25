package cn.tgkzxy.controller;

import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.service.LogsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LogsController {
    @Autowired
    LogsService logsService;
    @GetMapping("/logs/all")
    public String getAll(){
        List<Logs> logs = logsService.queryAll();
        return JSON.toJSONString(logs);
    }
    @GetMapping("/logs/limit")
    public String getByLimit(){
        Map<String,Integer> limitMap = new HashMap<String,Integer>();
        limitMap.put("startIndex",0);
        limitMap.put("pageSize",7);
        List<Logs> logs = logsService.queryByLimit(limitMap);
        Collections.reverse(logs);
        return JSON.toJSONString(logs);
    }
}
