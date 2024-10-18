package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
   private final UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }
//   Причины по которым лучше вводить зависимость в конструкторе:
//   1. Явность заивисимости
//   2. Иммутабельность
//   3. Легче использовать модульные тесты. Не нужны доп. библиотеки
//   4. Spring автоматически выявляет циклические зависимости если они объявлены в конструкторе (меньше всего понял этот пункт)

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserFromCarParameters(String model, int series) {
      return userDao.getUserFromCarParameters(model, series);
   }

}
