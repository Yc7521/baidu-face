package cn.edu.njuit.web.server;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "lesson", urlPatterns = "/api/lesson")
public class LessonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        System.out.println("GET /api/lesson");
        //获取查询字符串
        String queryString = req.getQueryString();
        System.out.println("查询字符串：" + queryString);
        //获取指定参数
        String text = req.getParameter("keyword");
        System.out.println("获取指定keyword参数：" + text);
        //指定格式
        resp.setContentType("text/plain; charset=utf8");
        resp.getWriter().write("这是一个文本响应");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        System.out.println("POST /api/lesson");
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),
          StandardCharsets.UTF_8
        ));
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        System.out.println("解析出json：" + jsonString.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("field", "这是一个字段值");
        jsonObject.put("field2", "这是第二个字段");
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(jsonObject.toJSONString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        System.out.println("PUT /api/lesson");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        System.out.println("DELETE /api/lesson");
    }
}
