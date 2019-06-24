package com.bigdatajcg2.dao;


import com.bigdatajcg2.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * 用于标注数据访问组件，即DAO组件
 */
@Repository
public class StudentDao {


    private static Map<Integer, Student> students = null;
//    @Autowired
//    StudentDao studentDao;

    static{
        students = new HashMap<Integer,Student>();

        students.put(0,new Student(0,"权志龙","男",101));
        students.put(1,new Student(1,"刘亦菲","女",102));
        students.put(2,new Student(2,"刘涛","女",103));
        students.put(3,new Student(3,"李易峰","男",104));
        students.put(4,new Student(4,"周星驰","男",105));
    }

    private static Integer initStudentId = 5;
    public void save(Student student){
        //如果Id为null
        if(student.getId() == null){
            student.setId(initStudentId++);
        }
        //把当前的数据内容添加进去
        students.put(student.getId(),student);
    }
    //获取所有的sites
    public Collection<Student> getAll(){return students.values();}

    //通过id获取内容
    public Student getStudent(Integer id){
        return students.get(id);
    }

    //通过id去更新当前实例化对象内容
    public void update(Student student){
        //获取id
        int id = student.getId();
        //当前没修改前的对象 重新赋值
        getStudent(id).setName(student.getName());
        getStudent(id).setSex(student.getSex());
        getStudent(id).setSno(student.getSno());
    }

    //返回一个随机的Student
    public Student randStudent(){
        System.out.println("studnet.size = "+students.size());
        Integer ranNum = (int) (Math.random() * 5);
        return students.get(ranNum);
    }
}
