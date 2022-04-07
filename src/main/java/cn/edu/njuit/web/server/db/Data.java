package cn.edu.njuit.web.server.db;

import cn.edu.njuit.web.server.domain.User;
import org.yc.orm.iot.DataPool;
import org.yc.orm.sql.Session;
import org.yc.orm.sql.SessionFactory;
import org.yc.orm.sql.gen.clauses.CreateTable;
import org.yc.orm.sql.gen.tr.TypeTranslate;
import org.yc.orm.util.meta.Meta;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Data {
    public static SessionFactory sessionFactory;
    public static Logger logger;

    static {
        sessionFactory = new SessionFactory();
        sessionFactory.set(
          "com.mysql.cj.jdbc.Driver",
          "jdbc:mysql://localhost:3307/ai_face?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai",
          "root",
          "123456"
        );
        logger = Logger.getGlobal();
        DataPool.getInstance().register(sessionFactory);
        TypeTranslate.register();
    }

}
