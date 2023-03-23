package spaland.email.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {

    private final Environment env;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername(env.getProperty("AdminMail.id"));
        javaMailSender.setPassword(env.getProperty("AdminMail.password"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("email.smtp.port")));
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", env.getProperty("email.smtp.socketFactory.port"));
        pt.put("mail.smtp.auth", env.getProperty("email.smtp.auth"));
        pt.put("mail.smtp.starttls.enable", env.getProperty("email.smtp.starttls.enable"));
        pt.put("mail.smtp.starttls.required", env.getProperty("email.smtp.starttls.required"));
        pt.put("mail.smtp.socketFactory.fallback", env.getProperty("email.smtp.socketFactory.fallback"));
        pt.put("mail.smtp.socketFactory.class", env.getProperty("email.smtp.socketFactory.class"));
        return pt;
    }

}
