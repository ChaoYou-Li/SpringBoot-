package cn.zdxh.lcy.demo03.utils;

import cn.zdxh.lcy.demo03.model.People;
import cn.zdxh.lcy.demo03.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.awt.peer.ListPeer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能需求：模拟数据库封装了person的JavaBean
 */
public class PersonList {
    private static Integer PERSON_ID = 1007;//模拟数据库主键

    public List<Person> personList() throws ParseException {
        DepartmentDao departmentDao = new DepartmentDao();
        List<Person> list = new ArrayList<>();
        list.add(new Person(1001,"张三",20,1,departmentDao.findDepartment(2),new Date()));
        list.add(new Person(1002,"李四",25,0,departmentDao.findDepartment(1),new Date()));
        list.add(new Person(1003,"王五",28,1,departmentDao.findDepartment(3),new Date()));
        list.add(new Person(1004,"赵六",30,1,departmentDao.findDepartment(4),new Date()));
        list.add(new Person(1005,"钱七",32,0,departmentDao.findDepartment(3),new Date()));
        list.add(new Person(1006,"孙八",35,1,departmentDao.findDepartment(5),new Date()));
        return list;
    }

    //提供一个数据添加方法
    public List<Person> addPerson(People p, HttpSession session) throws ParseException {
        List<Person> list;
        if (session.getAttribute("list") == null){
            list = personList();
        } else {
            list = (List<Person>) session.getAttribute("list");
        }
        list.add(new Person(PERSON_ID,p.getName(),p.getAge(),p.getGender(),new DepartmentDao().findDepartment(p.getDepartment()),new SimpleDateFormat("yyyy-MM-dd").parse(p.getBirthday())));
        PERSON_ID = PERSON_ID + 1;
        return list;
    }

    //提供一个数据查找方法
    public Person findPerson(Integer id, HttpSession session) throws ParseException {
        List<Person> list;
        Person person = null;
        if (session.getAttribute("list") == null){
            list = personList();
        } else {
            list = (List<Person>) session.getAttribute("list");
        }
        for (Person person1:list){
            if (person1.getId().equals(id)){
                person = person1;
                break;
            }
        }
        return person;
    }

    //提供一个数据删除方法
    public List<Person> deletePerson(Integer id, HttpSession session) throws ParseException {
        List<Person> list;
        List<Person> list1 = new ArrayList<>();
        if (session.getAttribute("list") == null){
            list = personList();
        } else {
            list = (List<Person>) session.getAttribute("list");
        }
        for (Person person1:list){
            if (!person1.getId().equals(id)){
                list1.add(person1);
            }
        }
        return list1;
    }

    //提供一个数据更新方法
    public List<Person> updatePerson(Integer id, People people, HttpSession session) throws ParseException {
        List<Person> list;
        if (session.getAttribute("list") == null){
            list = personList();
        } else {
            list = (List<Person>) session.getAttribute("list");
        }
        for (Person person1:list){
            if (person1.getId().equals(id)){
                person1.setName(people.getName());
                person1.setAge(people.getAge());
                person1.setGender(people.getGender());
                person1.setDepartment(new DepartmentDao().findDepartment(people.getDepartment()));
                person1.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(people.getBirthday()));
            }
        }
        return list;
    }
}
