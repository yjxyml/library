package com.yjxy.wxjsxy.view;

import com.yjxy.wxjsxy.model.Student;
import com.yjxy.wxjsxy.server.StudentServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@ComponentScan("com.yjxy.wxjsxy")
public class Main {
    static AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(Main.class);
    static StudentServer studentserver = (StudentServer) acac.getBean("studentserver");
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        Student student = new Student(22222,"sad1f",'男');
        Student student = new Student();
        while (true)
        {
            System.out.println("请输入一个数 1-添加 2-删除 3-修改 4查看 其他退出");
            int next = scanner.nextInt();
            switch (next)
            {
                case 1 :
                    insert(student);
                    break;
                case 2 :
                    delete(student);
                    break;
                case 3 :
                    update(student);
                    break;
                case 4 :
                    select();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
//        studentserver.insert(student);
//        studentserver.delete(student);
//        studentserver.update(student);
//        studentserver.select();
    }
    public static void insert(Student student)
    {
        System.out.println("请输入你要添加的id");
        int id = scanner.nextInt();
        System.out.println("请输入你要添加的名字");
        String name = scanner.next();
        System.out.println("请输入你要添加的性别");
        String sex = scanner.next();
        student = new Student(id,name,sex);
        int insert = studentserver.insert(student);
        if (insert == 1)
        {
            System.out.println("添加成功");
        }
        else
        {
            System.out.println("添加失败");
        }
    }

    public static void delete(Student student)
    {
        System.out.println("请输入你要删除的名字");
        String name = scanner.next();
        student = new Student(name);
        int delete = studentserver.delete(student);
        if (delete == 1)
        {
            System.out.println("删除成功");
        }
        else
        {
            System.out.println("删除失败");
        }
    }

    public static void update(Student student)
    {
        System.out.println("请输入你要修改的id");
        int id = scanner.nextInt();
        System.out.println("请输入你要修改的名字");
        String name = scanner.next();
        System.out.println("请输入你要修改的性别");
        String sex = scanner.next();
        student = new Student(id,name,sex);
        int update = studentserver.update(student);
        if (update == 1)
        {
            System.out.println("修改成功");
        }
        else
        {
            System.out.println("修改失败");
        }
    }

    public static void select()
    {
        List<Map<String, Object>> select = studentserver.select();
        for (Map<String, Object> stringObjectMap : select) {
            String tname = (String) stringObjectMap.get("tname");
            System.out.println(tname);
//            System.out.println(stringObjectMap);
        }
    }
}
