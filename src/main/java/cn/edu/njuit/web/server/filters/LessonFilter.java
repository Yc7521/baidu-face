package cn.edu.njuit.web.server.filters;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/file")
public class LessonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("/api/file 拦截器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String text = request.getParameter("text");
        if (text != null) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            JSONObject error = new JSONObject();
            error.put("error", "生成图片过程中发生错误");
            response.setStatus(400);
            response.setContentType("application/json;charset=utf8");
            response.getWriter().write(error.toJSONString());
        }

    }

    @Override
    public void destroy() {
    }
}
