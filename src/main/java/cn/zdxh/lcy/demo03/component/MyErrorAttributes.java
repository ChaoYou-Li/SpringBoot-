package cn.zdxh.lcy.demo03.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;
import java.util.Map;

import static java.awt.SystemColor.info;

/**
 * 功能需求：修改默认配置的报错信息，把自定义的错误信息添加到系统报错配置中
 */
@Component//给容器中添加我们自定义的ErrorAttributes
public class MyErrorAttributes extends DefaultErrorAttributes {//DefaultErrorAttributes 这个类是系统的默认报错配置类
    //返回值的map就是web端的页面数据或者移动端的json数据信息
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);//获取默认配置报错信息集合
        map.put("exception", "我去你大爷！");//我们自定义异常处理器添加的数据
        return map;
    }
}
