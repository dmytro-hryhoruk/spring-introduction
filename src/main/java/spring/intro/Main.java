package spring.intro;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user1 = new User();
        user1.setLogin("userOne");
        user1.setPassword("qwerty");
        userService.add(user1);

        User user2 = new User();
        user2.setLogin("userTwo");
        user2.setPassword("qwerty");
        userService.add(user2);
        System.out.println(userService.listUsers());
    }
}
