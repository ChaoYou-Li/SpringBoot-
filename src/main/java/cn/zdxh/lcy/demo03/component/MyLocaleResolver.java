package cn.zdxh.lcy.demo03.component;


import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域语言信息
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * @author chaoyou
     * @explain 当用选择哪种语言的时候把获取用户的请求参数，并读取该参数中包含的
     *          语言类型和所在地区的信息，修改默认的locale配置
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取用户选择的语言信息
        String language = request.getParameter("language");
        Locale locale = Locale.getDefault();
        //如果用户没有选择语言信息，系统会选择默认的语言信息
        if (!StringUtils.isEmpty(language)){
            String[] arr = language.split("_");
            //修改默认配置的语言和地区
            locale = new Locale(arr[0], arr[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
