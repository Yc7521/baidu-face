package cn.edu.njuit.web.server.apis;

import cn.edu.njuit.web.server.domain.ValidateCode;
import cn.edu.njuit.web.server.service.ValidateCodeService;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/validate-code")
public class ValidateCodeApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
        //调用验证码服务，产生一个验证码
        ValidateCodeService validateCodeService = new ValidateCodeService();
        ValidateCode validateCode = validateCodeService.createValidate();
        //将验证码返回
        resp.setContentType("application/json;charset=utf8");
        JSONObject result = new JSONObject(validateCode);
        resp.getWriter().write(result.toString());
    }
}
