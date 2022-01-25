package cn.tgkzxy.service;

import cn.tgkzxy.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageService {
    List<Message> queryAllMessageByCid(@Param("cid") int cid);
    List<Map<String,Object>> queryAllMessages();
    int deleteMessageByMid(int mid);
    int addMessage(Message message);
    List<Map<String,Object>> queryMessageByLimit(Map<String,Integer> map);
    Integer getMessageCount();

}
