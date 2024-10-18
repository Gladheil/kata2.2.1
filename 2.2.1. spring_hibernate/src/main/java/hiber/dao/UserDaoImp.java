package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      String hql = "SELECT u FROM User u LEFT JOIN FETCH u.car";
      return sessionFactory.getCurrentSession().createQuery(hql, User.class).getResultList();
   }

   @Override
   public User getUserFromCarParameters(String model, int series) {
      String hql = "SELECT u FROM User u LEFT JOIN FETCH u.car where u.car.model = :model and u.car.series = :series";
      return sessionFactory.getCurrentSession().createQuery(hql, Car.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult()
              .getUser();
   }

}
