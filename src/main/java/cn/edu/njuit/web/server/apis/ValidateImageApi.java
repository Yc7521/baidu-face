package cn.edu.njuit.web.server.apis;

import cn.edu.njuit.web.server.service.ValidateCodeService;
import cn.edu.njuit.web.server.tools.ImageTool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/image/validate-code")
public class ValidateImageApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
        //解析请求时的code参数
        String randomCode = req.getParameter("code");
        //调用验证码服务获得算式文本
        ValidateCodeService validateCodeService = new ValidateCodeService();
        String imageText = validateCodeService.getValidateText(randomCode);
        if (imageText != null) {
            //调用字符串生成图片的方法
            ImageTool imageTool = new ImageTool();
            Integer width = imageText.length() * 16;
            Integer height = 40;
            ByteArrayOutputStream image = imageTool.string2Image(
              imageText,
              width,
              height
            );
            resp.setContentType("image/jpeg");
            image.writeTo(resp.getOutputStream());
        }
    }
}
