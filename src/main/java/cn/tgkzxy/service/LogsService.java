package cn.tgkzxy.service;

import cn.tgkzxy.pojo.Logs;

import java.util.List;
import java.util.Map;

public interface LogsService {
    int addLog(Logs logs);
    List<Logs> queryAll();
    List<Logs> queryByLimit(Map<String,Integer> map);
}
