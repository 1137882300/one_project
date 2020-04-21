package com.z.config;

import com.z.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//这个注解也会被spring容器托管，注册到容器中。
//@Configuration代表这是一个配置类，就和我们之前写的bean.xml一样
@Configuration
@ComponentScan("com.z.pojo")
@Import(AnnoConfig2.class)
public class AnnoConfig {

    //注册一个bean，就相当于我们之前写的一个bean标签
    //这个方法的名字，就相当于bean标签中的id属性
    //这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();//这个返回就是要注入到bean的对象
    }
}
