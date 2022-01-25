package cn.tgkzxy.mapper;

import cn.tgkzxy.pojo.Blog;
import cn.tgkzxy.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {
    List<Message> queryAllMessageByCid(@Param("cid") int cid);
    List<Map<String,Object>> queryAllMessages();
    int deleteMessageByMid(@Param("mid")int mid);
    int addMessage(Message message);
    List<Map<String,Object>> queryMessageByLimit(Map<String,Integer> map);
    Integer getMessageCount();
    List<String> queryAllMail();

}
