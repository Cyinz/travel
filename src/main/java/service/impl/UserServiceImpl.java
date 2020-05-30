package service.impl;


import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            //用户名已存在
            return false;
        }
        //保存用户信息
        userDao.save(user);
        return true;
    }
}
