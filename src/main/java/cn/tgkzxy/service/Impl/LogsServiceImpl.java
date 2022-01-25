package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.LogsMapper;
import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    LogsMapper logsMapper;
    @Override
    public int addLog(Logs logs) {
        return logsMapper.addLog(logs);
    }

    @Override
    public List<Logs> queryAll() {
        return logsMapper.queryAll();
    }

    @Override
    public List<Logs> queryByLimit(Map<String, Integer> map) {
        return logsMapper.queryByLimit(map);
    }
}
