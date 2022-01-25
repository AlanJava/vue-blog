package cn.tgkzxy.controller;

import cn.tgkzxy.service.Impl.MailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailSendController {
    @Autowired
    MailSend mailSend;
    @PostMapping("/send")
    public String sendMail(){
        return mailSend.send();
    }
}
