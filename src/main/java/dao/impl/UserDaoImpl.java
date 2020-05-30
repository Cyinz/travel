package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM tab_user WHERE username = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

        return user;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user(username,password,name,birthday,sex,telephone,email) VALUES (?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
    }
}
