package cn.zdxh.lcy.demo03.Filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * 功能需求：自定义的Filter组件
 */
public class MyFilter implements Filter {
    @Override//Filter初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter 初始化方法");
    }

    @Override//Filter销毁方法
    public void destroy() {
        System.out.println("MyFilter 销毁方法");
    }

    @Override//Filter处理方法
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter 执行了.....");
        filterChain.doFilter(servletRequest, servletResponse);//拦截器放行
    }
}
