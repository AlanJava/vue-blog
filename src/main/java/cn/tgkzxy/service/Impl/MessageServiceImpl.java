package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.MessageMapper;
import cn.tgkzxy.pojo.Message;
import cn.tgkzxy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public List<Message> queryAllMessageByCid(int cid) {
        return messageMapper.queryAllMessageByCid(cid);
    }

    @Override
    public List<Map<String,Object>> queryAllMessages() {
        return messageMapper.queryAllMessages();
    }

    @Override
    public int deleteMessageByMid(int mid) {
        return messageMapper.deleteMessageByMid(mid);
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    @Override
    public List<Map<String,Object>> queryMessageByLimit(Map<String, Integer> map) {
        return messageMapper.queryMessageByLimit(map);
    }

    @Override
    public Integer getMessageCount() {
        return messageMapper.getMessageCount();
    }
}
