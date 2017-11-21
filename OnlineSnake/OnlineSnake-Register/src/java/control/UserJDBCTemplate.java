package control;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import entity.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

//    public boolean checkLogin(User user) {
//        String sql = "SELECT * FROM tbl_user WHERE username = ? AND password = ?";
//        List<User> result = jdbcTemplateObject.query(sql, new Object[]{user.getUsername(), user.getPassword()}, new UserMapper());
//
//        if (result.size() > 0) {
//            return true;
//        }
//        return false;
//    }

    public boolean create(User user) {
        String SQL = "SELECT * FROM tbl_user WHERE username = ?";
        List<User> result = jdbcTemplateObject.query(SQL, new Object[]{user.getUsername()}, new UserMapper());
        if (result.size() > 0) {
            return false;
        }
        SQL = "INSERT INTO tbl_user (username, password, address, phone) values(?,?,?,?)";
        jdbcTemplateObject.update(SQL, user.getUsername(), user.getPassword(), user.getAddress(), user.getPhone());
        return true;
    }
}
