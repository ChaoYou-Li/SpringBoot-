package cn.zdxh.lcy.demo03.component;

import cn.zdxh.lcy.demo03.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能需求：自定义一个500状态报错的错误信息处理类
 *
 */
@ControllerAdvice//这个注解是专门提供给异常处理类的
public class MyExceptionHandler {

//    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)//异常处理器
    public String Handler(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code", 500);//设置报错状态码
        map.put("code", "[user] param is not found!");
        map.put("message", e.getMessage());
        map.put("cause", e.getCause());
//        request.setAttribute("error", map);
        return "forward:/error";
    }
}
