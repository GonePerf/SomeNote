import SQL.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class UserDAO extends DAO<User, Long>{
    public User findById(Long aLong) {
        openCurrentSession();
        User user = (User) getCurrentSession().get(User.class,aLong);
        closeCurrentSession();
        return user;
    }



    public List<User> findAll() {
        openCurrentSession();
        List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
        closeCurrentSession();
        return users;
    }
    public void addUser(User user){
        openCurrentSessionwithTransaction();
        getCurrentSession().save(user);
        closeCurrentSessionwithTransaction();
    }
}
