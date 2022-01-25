package cn.tgkzxy.mapper;

import cn.tgkzxy.pojo.Logs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogsMapper {
    int addLog(Logs logs);
    List<Logs> queryAll();
    List<Logs> queryByLimit(Map<String,Integer> map);
}
