package cn.edu.njuit.web.server.apis;

import cn.edu.njuit.web.server.service.BaiduAiService;
import cn.edu.njuit.web.server.tools.ImageTool;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/api/ai-face")
@MultipartConfig
public class FaceApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        Part image = req.getPart("image");
        ImageTool imageTool = new ImageTool();
        //将图片转换为Base64
        String imageBase64 = imageTool.image2Base64(image.getInputStream());
        //调用百度ai服务层方法
        BaiduAiService baiduAiService = new BaiduAiService();
        JSONObject res = baiduAiService.checkFaces(imageBase64);
        //进行响应
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(res.toString());
    }
}