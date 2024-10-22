package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car1 = new Car("Lada Granta", 123);
      Car car2 = new Car("Ford Focus", 456);
      Car car3 = new Car("Opel Astra", 789);
      car1.setUser(user1);
      user1.setCar(car1);
      car2.setUser(user2);
      user2.setCar(car2);
      car3.setUser(user3);
      user3.setCar(car3);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);


      List<User> list = new ArrayList<>();
      list.add(userService.getUserFromCarParameters("Lada Granta", 123));
      list.add(userService.getUserFromCarParameters("Ford Focus", 456));
      list.add(userService.getUserFromCarParameters("Opel Astra", 789));


//      List<User> users = userService.listUsers();
      for (User user : list) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
