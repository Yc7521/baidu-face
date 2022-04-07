package cn.edu.njuit.web.server.service;

import cn.edu.njuit.web.server.domain.User;
import org.json.JSONObject;

import java.util.Base64;

public class UserTokenService {
    //根据登录名生成令牌
    public String getToken(User user) {
        String json = new JSONObject(user).toString();
        final String token = Base64.getEncoder().encodeToString(json.getBytes());
        return String.format("token.%s.end", token);
    }

    public User getUser(String token) {
        try {
            String[] tokens = token.split("\\.");
            if (tokens[0].equals("token") && tokens[2].equals("end")) {
                String json = new String(Base64.getDecoder().decode(tokens[1]));
                final JSONObject obj = new JSONObject(json);
                User user = new User();
                // id, login, username, password
                if (obj.has("id")) {
                    user.setId(obj.getInt("id"));
                }
                if (obj.has("username")) {
                    user.setUsername(obj.getString("username"));
                }
                if (obj.has("password")) {
                    user.setPassword(obj.getString("password"));
                }
                return user;
            }
            throw new Exception("token格式错误");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}