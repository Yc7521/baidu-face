package cn.edu.njuit.web.server.apis;

import cn.edu.njuit.web.server.domain.User;
import cn.edu.njuit.web.server.service.LoginService;
import cn.edu.njuit.web.server.service.UserTokenService;
import cn.edu.njuit.web.server.service.ValidateCodeService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/login")
public class LoginApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String uuid = req.getParameter("uuid");
        String code = req.getParameter("code");
        ValidateCodeService validateCodeService = new ValidateCodeService();
        //登录成功
        if (validateCodeService.validate(uuid, code)) {
            LoginService loginService = new LoginService();
            final User login = loginService.login(username, password);
            if (login != null) {
                //构造token
                UserTokenService userTokenService = new UserTokenService();
                String token = userTokenService.getToken(login);
                //返回token
                JSONObject result = new JSONObject();
                result.put("token", token);
                resp.setContentType("application/json;charset=utf8");
                resp.getWriter().write(result.toJSONString());
            } else {//登录失败
                JSONObject error = new JSONObject();
                error.put("error", "用户名密码错误");
                resp.setStatus(401);
                resp.setContentType("application/json;charset=utf8");
                resp.getWriter().write(error.toJSONString());

            }
        } else {//登录失败
            JSONObject error = new JSONObject();
            error.put("error", "验证码错误");
            resp.setStatus(401);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(error.toJSONString());

        }
    }
}