package cn.tgkzxy.service.Impl;

import cn.tgkzxy.mapper.BlogMapper;
import cn.tgkzxy.mapper.MessageMapper;
import cn.tgkzxy.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class MailSend {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    BlogMapper blogMapper;

    //每天19点
    @Scheduled(cron = "0 0 19 * * ?")
    public String send(){
        List<String> mails = messageMapper.queryAllMail();
        Blog lastBlog = blogMapper.queryLast();
        for (String mail:mails
             ) {
            if (!ObjectUtils.isEmpty(mail)) {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("猛男博客");//主体
                simpleMailMessage.setText("最近我发布了不少新文章;\n最新文章:"+lastBlog.getTitle()+";\n点击链接看看吧,http://www.tgkzxy.cn");
                simpleMailMessage.setTo(mail);
                simpleMailMessage.setFrom("763162583@qq.com");
                mailSender.send(simpleMailMessage);
            }
        }
        return "发送成功";
    }
}
