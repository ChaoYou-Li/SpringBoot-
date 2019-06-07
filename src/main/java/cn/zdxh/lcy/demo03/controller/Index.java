package cn.zdxh.lcy.demo03.controller;

import cn.zdxh.lcy.demo03.exception.UserNotFoundException;
import cn.zdxh.lcy.demo03.model.Department;
import cn.zdxh.lcy.demo03.model.People;
import cn.zdxh.lcy.demo03.model.Person;
import cn.zdxh.lcy.demo03.utils.DepartmentDao;
import cn.zdxh.lcy.demo03.utils.PersonList;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class Index {
    private List<Person> list2 = new PersonList().personList();
    private DepartmentDao departmentDao = new DepartmentDao();

    public Index() throws ParseException {
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String success(@RequestParam("user") String user) {
        if (user.equals("chaoyou")){
            throw new UserNotFoundException();
        }
        return "Welcome you！";
    }

    @PostMapping("/user/login")
    public String userLogin(@RequestParam("username")String username, @RequestParam("password") String password, Model model, HttpSession session){
        String str = null;
//        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(username) && password.equals("123456")){
//            model.addAttribute("username", username);
//            model.addAttribute("password", password);
            //设置session域作为判别用户的操作权限
            session.setAttribute("loginUser", username);
            session.setAttribute("password", password);
            str = "redirect:/info/list";//用户登录成功
        } else {
            str = "index";
//            map.put("error", "用户名或密码输入错误！");
            model.addAttribute("error", "用户名或密码输入错误！");
        }
        return str;
    }

    @GetMapping("info/list")
    public String findAll(Model model, HttpSession session) throws ParseException {
        model.addAttribute("active", "find");
        List<Person> list;
        if (session.getAttribute("list") == null){
            list = list2;
        } else {
            list = (List<Person>) session.getAttribute("list");
        }
        model.addAttribute("list", list);
        return "operation/list";
    }

    @PostMapping("info/add")
    public String add(People p, Model model, HttpSession session) throws ParseException {
        List<Person> list = new PersonList().addPerson(p, session);
        session.setAttribute("list", list);
        return "redirect:/info/list";
    }

    @PutMapping("info/update/{id}")
    public String update(@PathVariable("id") Integer id, People people,Model model, HttpSession session) throws ParseException {
        List<Person> list = new PersonList().updatePerson(id, people, session);
        model.addAttribute("active", "update");
        session.setAttribute("list", list);
        return "redirect:/info/list";
    }

    @GetMapping("info/delete{id}")
    public String delete(@PathVariable("id") Integer id,Model model, HttpSession session) throws ParseException {
        List<Person> list = new PersonList().deletePerson(id, session);
        session.setAttribute("list", list);
        return "redirect:/info/list";
    }


    @GetMapping("/add")
    public String showAdd(DepartmentDao departmentDao, Model model){
        List<Department> list = departmentDao.addDepartment();
        model.addAttribute("department", list);
        model.addAttribute("active", "add");
        model.addAttribute("type", "add");
        return "operation/add";
    }

    @GetMapping("/update/{id}")
    public String forUpdate(@PathVariable("id") Integer id, Model model, HttpSession session) throws ParseException {
        System.out.println(id);
        Person person = new PersonList().findPerson(id, session);
        List<Department> list = departmentDao.addDepartment();
        model.addAttribute("department", list);
        model.addAttribute("person", person);
        return "operation/update";
    }

    @DeleteMapping("/delete/{id}")
    public String forDelete(@PathVariable("id") Integer id, Model model, HttpSession session) throws ParseException {
        System.out.println(id);
        List<Person> list = new PersonList().deletePerson(id, session);
        session.setAttribute("list", list);
        return "redirect:/info/list";
    }

}
