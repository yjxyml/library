package com.cs.wxjsxy.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@MyScan("com.wxjsxy")
public class Text {
    public static void main(String[] args) throws Exception {


        Class studentClass = Student.class;
        //获取方法上的注解值
        Method[] declaredMethods1 = studentClass.getDeclaredMethods();
        if(declaredMethods1!=null)
        {
            for (Method method : declaredMethods1) {
                ValueScan annotation = method.getAnnotation(ValueScan.class);
                if(annotation==null)
                    continue;
                Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    String invoke = (String) declaredMethod.invoke(annotation, null);
                    System.out.println(invoke);
                }
            }
        }

        //如果获得注解信息
        Class c6 = Text.class;

        MyScan annotation = (MyScan) c6.getAnnotation(MyScan.class);
//        System.out.println("values==========="+annotation.value());

        //几种创建对象方法
        Student student = new Student();

        Class c2 = student.getClass();

        Class c3 = Student.class ;

        Class c4 = ClassLoader.getSystemClassLoader().loadClass("com.cs.wxjsxy.model.Student");

        //Student.java -->student.class -->加载到内存（静态方法区）-->new 在堆区创建对象
        //Class对象

        try {
           Class c = Class.forName("com.cs.wxjsxy.model.Student");

           //获取属性名称
            Field[] declaredFields = c.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                System.out.println(declaredField.getName());
            }

            //Get Set 方法
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
//                System.out.println(declaredMethod.getName());
            }

            //构造方法
            Constructor[] declaredConstructors = c.getDeclaredConstructors();
            for (Constructor declaredConstructor : declaredConstructors) {
                System.out.println(declaredConstructor);
            }

            //无参构造
           Constructor constructor = c.getConstructor();
            Object o = constructor.newInstance();
            Student student1 = (Student) o;
            System.out.println("------------"+student1.getName());

            //有参构造
//            Constructor declaredConstructor = c.getDeclaredConstructor(String.class, int.class, int.class);
//            Object o1 = declaredConstructor.newInstance(new Object[]{"张三", 1, 15});
//            Student student2 = (Student) o1;
//            System.out.println(student2.getName());
//            System.out.println(student2.getAge());
//            System.out.println(student2.getId());


            //遍历getset方法找相同注入内容
            for (Method declaredMethod : declaredMethods) {
//                System.out.println(declaredMethod.getName());
                String s = declaredMethod.getName();
                if(s.equals("setName"))
                {
                    declaredMethod.invoke(o,new Object[]{"liuer"});
                }
            }
            System.out.println("------------"+student1.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}




///通过字符串 com.wxjsxy.model.Student -->class --> Field, Method , Constructor -->
///Constructor -->创建对象
//Method.invoke()实现方法的执行