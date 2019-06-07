package cn.zdxh.lcy.demo03.config;

import cn.zdxh.lcy.demo03.Filter.MyFilter;
import cn.zdxh.lcy.demo03.Listener.MylListener;
import cn.zdxh.lcy.demo03.servlet.MyServlet;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration//标注此类为一个配置类（类似于以前使用的xml配置文件）
public class MyServerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override//设置SpringBoot内嵌Servlet容器的参数
    public void customize(ConfigurableServletWebServerFactory factory) {
        //设置Servlet容器端口号
        factory.setPort(8090);
    }


    @Bean//注册Servlet组件
    public ServletRegistrationBean myServlet(){
        //把自定义的Servlet处理请求的组件添加到容器中，并且设置处理的请求路径
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        servletRegistrationBean.setLoadOnStartup(1);//第一个添加的组件
        return servletRegistrationBean;
    }

    @Bean//注册Filter组件
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //把自定义的MyFilter组件添加到容器中(组件只有在容器中才能起作用）
        filterRegistrationBean.setFilter(new MyFilter());
        //设置要拦截的请求路径
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myFilter", "/hello"));//此处可拦截的参数是一个数组里面存放要拦截的路径
        return filterRegistrationBean;
    }

    @Bean//注册Listener组件
    public ServletListenerRegistrationBean myListener(){
        //把自定义的监听组件添加大Servlet容器中
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean(new MylListener());
        return servletListenerRegistrationBean;

    }



}
