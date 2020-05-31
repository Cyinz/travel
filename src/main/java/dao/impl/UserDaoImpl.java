package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "SELECT * FROM tab_user WHERE username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user(username,password,email) VALUES (?,?,?)";
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getEmail());
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        User u = null;
        try {
            String sql = "SELECT * FROM tab_user WHERE username = ? AND password = ?";
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return u;
    }
}
