package cn.edu.njuit.web.server;

import cn.edu.njuit.web.server.tools.ImageTool;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(name = "file", urlPatterns = "/api/file")
@MultipartConfig
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

        String text = req.getParameter("text");
        if (text != null) {
            ImageTool imageTool = new ImageTool();
            Integer width = text.length() * 25;
            try {
                ByteArrayOutputStream imageStream = imageTool.string2Image(text,
                  width,
                  40
                );
                resp.setContentType("image/jpeg");
                imageStream.writeTo(resp.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
                //生成图片过程中发生错误
                JSONObject error = new JSONObject();
                error.put("error", "生成图片过程中发生错误" + e.getMessage());
                resp.setStatus(500);
                resp.setContentType("application/json;charset=utf8");
                resp.getWriter().write(error.toJSONString());
            }

        } else {
            JSONObject error = new JSONObject();
            error.put("error", "参数text未被指定");
            resp.setStatus(400);
            resp.setContentType("application/json; charset=utf8");
            resp.getWriter().write(error.toJSONString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        Part file = req.getPart("image");
        String fileName = file.getSubmittedFileName();
        long fileSize = file.getSize();
        System.out.println("获取到文件：" + fileName + ",文件大小为" + fileSize);
    }
}
