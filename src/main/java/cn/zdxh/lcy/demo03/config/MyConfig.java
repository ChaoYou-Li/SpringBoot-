package cn.zdxh.lcy.demo03.config;

import cn.zdxh.lcy.demo03.component.LoginHandlerInterceptor;
import cn.zdxh.lcy.demo03.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurationSupport可以来扩展SpringMVC的功能
@Controller
public class MyConfig extends WebMvcConfigurationSupport{

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //设置当有/hello请求的时候就会转发到index视图页面
        registry.addViewController("main.html").setViewName("success");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("index").setViewName("index");
    }

    //注册拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        //静态资源：*.css，*.js
        //SpringBoot已经做好了静态资源映射
        //设置拦截的请求范围（addPathPatterns）和不拦截的请求范围（excludePathPatterns）
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "index", "/user/login");


    }

    //所有的WebMvcConfigurationSupport 组件都会一起其中作用
//    public WebMvcConfigurerAdapter webMvcConfigurationSupport() {
//        WebMvcConfigurerAdapter webMvc = new WebMvcConfigurerAdapter(){
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                //专门设置用户请求转发@Controller处理后的View层
//                registry.addViewController("/").setViewName("index");
//                registry.addViewController("index").setViewName("index");
//                registry.addViewController("main.html").setViewName("success");
//            }
//        };
//        return webMvc;
//    }

    @Bean//把自己设置组件添加容器中
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Bean//设置一个请求method 格式拦截组件
    public FilterRegistrationBean<HiddenHttpMethodFilter> testFilterRegistration3() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registration = new FilterRegistrationBean<HiddenHttpMethodFilter>();
        registration.setFilter(new HiddenHttpMethodFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("HiddenHttpMethodFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }

}
