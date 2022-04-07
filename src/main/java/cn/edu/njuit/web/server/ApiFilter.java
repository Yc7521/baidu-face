package cn.edu.njuit.web.server;

import cn.edu.njuit.web.server.domain.User;
import cn.edu.njuit.web.server.service.UserTokenService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/ai-face")
public class ApiFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //校验请求是否携带了token
        String token = httpRequest.getParameter("token");
        //没有携带令牌，拒绝请求
        if (token == null) {
            JSONObject error = new JSONObject();
            error.put("error", "未登录，拒绝访问");
            httpResponse.setContentType("application/json;charset=utf8");
            httpResponse.setStatus(401);
            httpResponse.getWriter().write(error.toString());
            return;
        } else {
            //校验token合法性
            UserTokenService userTokenService = new UserTokenService();
            User u = userTokenService.getUser(token);
            //正确解析到用户，token合法
            if (u != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                JSONObject error = new JSONObject();
                error.put("error", "token无效");
                httpResponse.setContentType("application/json;charset=utf8");
                httpResponse.setStatus(401);
                httpResponse.getWriter().write(error.toString());
                return;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
