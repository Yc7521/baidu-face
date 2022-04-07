package cn.edu.njuit.web.server.db;

import cn.edu.njuit.web.server.domain.User;
import cn.edu.njuit.web.server.service.LoginService;
import junit.framework.TestCase;
import org.yc.orm.sql.Session;
import org.yc.orm.sql.gen.clauses.CreateTable;
import org.yc.orm.util.meta.Meta;

import java.sql.SQLException;

import static cn.edu.njuit.web.server.db.Data.logger;
import static cn.edu.njuit.web.server.db.Data.sessionFactory;

public class DataTest extends TestCase {

    static void createTable() {
        // try to create the table user
        final Session session = sessionFactory.getSession();
        try {
            CreateTable<User> createTable = new CreateTable<>(Meta.of(User.class));
            session.execute(createTable.drop());
            session.execute(createTable.generate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createUser() {
        LoginService loginService = new LoginService();
        User user = loginService.login("test", "test");
        if (user == null) {
            try (final Session session = sessionFactory.getSession()) {
                session.removeAll(User.class);
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }
            final User register = loginService.register("test", "test");
            logger.info("register: %s".formatted(register));
        } else {
            logger.info("has user: %s".formatted(user));
        }
    }

    public void testCreateTable() {
        createTable();
    }

    public void testCreateUser() {
        createUser();
    }
}