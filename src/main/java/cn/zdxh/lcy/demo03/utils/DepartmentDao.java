package cn.zdxh.lcy.demo03.utils;

import cn.zdxh.lcy.demo03.model.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能需求：这是一个模拟数据库的职位信息封装类
 */
public class DepartmentDao {

    //设置职位的类型
    public List<Department> addDepartment(){
        List<Department> list = new ArrayList<>();
        list.add(new Department(list.size()+1, "学生"));
        list.add(new Department(list.size()+1, "助教"));
        list.add(new Department(list.size()+1, "教授"));
        list.add(new Department(list.size()+1, "辅导员"));
        list.add(new Department(list.size()+1, "主任"));
        return list;
    }

    //通过id 查询对应的职位名称
    public Department findDepartment(Integer id){
        Department dep = null;
        List<Department> list = addDepartment();
        for (Department department:list){
            if (department.getId() == id){
                dep = department;
            }
        }
        return dep;
    }
}
