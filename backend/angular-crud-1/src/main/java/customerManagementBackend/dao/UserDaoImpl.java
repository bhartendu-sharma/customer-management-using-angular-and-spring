package customerManagementBackend.dao;

import customerManagementBackend.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //   ~~~~~~~~~~~~~~************* Save Or Register new User along with 3DES(Triple Data Encryption Standard) encryption*****************~~~~~~~~~~~~~~
    @Override
    public void save(User user) {
        user.setPassword(user.getPassword());
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User getUser(String email) {
        boolean userFound = false;
        Transaction transaction = null;
        User userDataFrommDB = null;
        try (Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            // get an user object
            userDataFrommDB = (User) session.createQuery("FROM User U WHERE U.email = :email").setParameter("email", email)
                    .uniqueResult();
            System.out.println("userDataFrommDB : "+userDataFrommDB);

            if (userDataFrommDB != null)
            {
                return userDataFrommDB;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }


    //   ~~~~~~~~~~~~~~************* Authenticate Registered User Maker or Checker *****************~~~~~~~~~~~~~~
    @Override
    public User loginUser(User user) {
        boolean userFound = false;
        String email= user.getEmail();
        String password=user.getPassword();

        Transaction transaction = null;
        User userDataFrommDB = null;
        try (Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            // get an user object
            userDataFrommDB = (User) session.createQuery("FROM User U WHERE U.email = :email").setParameter("email", email)
                    .uniqueResult();
            System.out.println("userDataFrommDB : "+userDataFrommDB);

            if (userDataFrommDB != null && userDataFrommDB.getPassword().equals(password))
             {
//                HttpSession httpSession=
                System.out.println("Login Successfully!");
                return user;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }


}
