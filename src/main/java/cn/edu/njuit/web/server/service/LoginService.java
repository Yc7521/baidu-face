package cn.edu.njuit.web.server.service;

import cn.edu.njuit.web.server.db.Data;
import cn.edu.njuit.web.server.domain.User;
import cn.edu.njuit.web.server.util.PasswordEncoder;
import org.yc.orm.iot.DataPool;
import org.yc.orm.sql.Session;
import org.yc.orm.sql.SessionFactory;
import org.yc.orm.sql.Statement;

public class LoginService {
    PasswordEncoder encoder;
    SessionFactory sessionFactory;

    public LoginService() {
        encoder = PasswordEncoder.getInstance();
        sessionFactory = Data.sessionFactory;
    }

    public User login(String username, String password) {
        try (final Session session = sessionFactory.getSession()) {
            final var select = session.select(User.class);
            final Statement statement = select.where().by("username", username).end();
            final User user = session.query(User.class, statement);
            if (user == null) {
                return null;
            }
            if (encoder.matches(password, user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // register
    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        try (final Session session = sessionFactory.getSession()) {
            return session.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
