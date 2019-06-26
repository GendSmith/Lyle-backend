package service;

import bean.User;
import dao.UserDAO;

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
}
