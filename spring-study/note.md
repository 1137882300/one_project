## 常用依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop
          https://www.springframework.org/schema/aop/spring-aop.xsd">
```
```xml
**spring**
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.5.RELEASE</version>
</dependency> 
**aop**
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.7</version>
</dependency>
```
## AutoWired
```java
    <!--
    byName 会自动在容器上下文中查找和自己对象set方法后面的值对应的beanId
        注意:需要保证所有bean的id唯一,并且这个bean需要和自动注入的属性的set方法的值一致!
    byType: 会自动在容器上下文中查找和自己对象属性类型相同的bean
        注意:需要保证所有bean的class唯一,并且这个bean需要和自动注入的属性的类型一致!
    -->
    <bean id="people" class="com.z.pojo.com.z.pojo.People" autowire="byName">
        <property name="name" value="傻狗啊"/>    
    </bean>
```
## 注解的AutoWired
- 实体类
```xml
public class People {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;

    private String name;
}

    @Autowired
    @Qualifier(value = "dog2222")//当自动装配环境比较复杂时，可以用@Qualifier指定一个唯一的value
    private Dog dog;

    
    @Resource() 或者 @Resource(name = "name33")
    private String name;
```
- beans.xml
```xml
<!--开启注解的支持-->
    <context:annotation-config></context:annotation-config>

    <bean id="cat" class="com.z.pojo.Cat"/>
    <bean id="dog" class="com.z.pojo.Dog"/>
    <bean id="people" class="com.z.pojo.People"></bean>

    <bean id="dog" class="com.z.pojo.Dog"/>
    <bean id="dog2222" class="com.z.pojo.Dog"/> @Qualifier
```


## 注解说明
- @Autowired ：自动装配通过类型。名字
    如果Autowired不能唯一自动装配上属性，则需通过@Qualifier（value=“xxx”）
- @Nullable : 字段标记了这个注解，说明这个字段可以为null
- @Resource ：自动装配通过名字，类型
- @Component : 组件，放在类上，说明这个类被Spring管理了，就是bean!
    - @Repository : dao
    - @Service : service
    - @Controller : controller
```xml
<bean>
    <context:component-scan base-package="com.z"/>
    <context:annotation-config/>
</bean>
```
## 纯注解
- 实体类
```java
//这个注解的意思就是说这个类被Spring接管了，注册到了容器中
@Component
public class User {
    private String name;
    public String getName() {
        return name;
    }
    @Value("傻狗")//属性注入值
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```
- 配置类
```java
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
```
- 测试类
```java
public static void main(String[] args) {
    //如果完全只用了配置类方法去做，就只能通过AnnotationConfig 上下文获取容器通过配置类的class对象加载！
    ApplicationContext context = new AnnotationConfigApplicationContext(AnnoConfig.class);
    User getUser = (User) context.getBean("getUser");
    System.out.println(getUser.getName());
}
```



## xml和注解最佳实践
 - xml用来管理bean；
 - 注解只负责完成属性的注入；
 - 我们在使用的过程种，只需注意一个问题：必须让注解生效，就需要开启注解的支持





