package cn.tgkzxy.controller;

import cn.tgkzxy.pojo.Blog;
import cn.tgkzxy.pojo.Logs;
import cn.tgkzxy.pojo.Message;
import cn.tgkzxy.service.LogsService;
import cn.tgkzxy.service.MessageService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    LogsService logsService;
    @GetMapping("/{cid}")
    public String queryAllMessageByCid(@PathVariable("cid") int cid){
        List<Message> messages = messageService.queryAllMessageByCid(cid);
        return JSON.toJSONString(messages);
    }
    //获取message总数
    @GetMapping("/count")
    @ResponseBody
    public Map<String,Integer> getMessageCount(){
        Integer messageCount = messageService.getMessageCount();
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        countMap.put("messageCount",messageCount);
        return countMap;
    }

    @GetMapping("/{startIndex}/{pageSize}")
    @ResponseBody
    public String queryMessageByLimit(@PathVariable("startIndex") Integer startIndex,
                                   @PathVariable("pageSize")Integer pageSize){
        Map<String,Integer> limitMap = new HashMap<String,Integer>();
        limitMap.put("startIndex",startIndex);
        limitMap.put("pageSize",pageSize);
        List<Map<String,Object>> messages = messageService.queryMessageByLimit(limitMap);

        return JSON.toJSONString(messages);
    }

    @GetMapping("/all")
    public String queryAllMessages(){
        List<Map<String,Object>> messages = messageService.queryAllMessages();
        return JSON.toJSONString(messages);
    }
    @DeleteMapping("/{mid}")
    public String delete(@PathVariable("mid") int mid){
        int i = messageService.deleteMessageByMid(mid);
        String code = "400";
        if (i==1) {
            code = "200";
            //日志
            Date date = new Date();
            long time = date.getTime();
            Logs logs = new Logs();
            logs.setTime(time);
            logs.setAction("删除评论");
            logsService.addLog(logs);
        }
        return JSON.toJSONString(code);
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam("cid") int cid,
                             @RequestParam("message") String message,
                             @RequestParam("mail") String mail,
                             @RequestParam("touristName") String touristName){
        Date date = new Date();
        long time = date.getTime();
        Message newMessage = new Message();
        newMessage.setCid(cid);
        newMessage.setMessage(message);
        newMessage.setMail(mail);
        newMessage.setTouristName(touristName);
        newMessage.setTime(time);
        int i = messageService.addMessage(newMessage);
        return JSON.toJSONString(newMessage);
    }
}
