import com.z.config.AnnoConfig;
import com.z.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        //如果完全只用了配置类方法去做，就只能通过AnnotationConfig 上下文获取容器通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnoConfig.class);
        User getUser = (User) context.getBean("getUser");
        System.out.println(getUser.getName());
    }
}
