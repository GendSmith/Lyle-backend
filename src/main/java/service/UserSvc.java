package service;

import bean.User;
import dao.UserDAO;
import utils.JavaBeanToString;

public class UserSvc extends UserDAO {
    private UserDAO userDao = new UserDAO();

    @Override
    public User get(int id){
        return userDao.get(id);
    }

  @Override
   public boolean add(User user){
        return userDao.add((user));
  }

    @Override
    public String toString () {
        try{
            return JavaBeanToString.toString(this);
        }catch (IllegalAccessException e){
            return super.toString();
        }
    }
}
