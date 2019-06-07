package cn.zdxh.lcy.demo03.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查，没有登录的用户不用进入系统主页
 * 只有登录成功的用户才获得本系统的操作权限
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {//实现了一个拦截处理器接口
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户的登录信息
        Object username = request.getSession().getAttribute("loginUser");
        if(username == null){//用户名不为空则获得操作系统权限
            request.setAttribute("error", "您没有获得操作权限，请先登录！");
            request.getRequestDispatcher("/").forward(request, response);//拦截成功则把路径转发到登录页面
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
