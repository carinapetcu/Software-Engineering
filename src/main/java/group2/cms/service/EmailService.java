package group2.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Value("$(spring.mail.username)")
    private String senderAddr;

    public void send(String to, String subj, String msgText) {
        var msg = new SimpleMailMessage();

        msg.setTo(to);
        msg.setSubject(subj);
        msg.setText(msgText);
        msg.setFrom(senderAddr);

        emailSender.send(msg);
    }
}
