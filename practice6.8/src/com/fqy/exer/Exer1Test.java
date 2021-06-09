package com.fqy.exer;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/9 13:07
 */

import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.util.Scanner;

/**
 * 从控制台插入数据
 * 并返回操作结果，成功或失败
 */
public class Exer1Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入用户名：");
        String name = scanner.next();
        System.out.println("请输入邮箱");
        String email = scanner.next();
        System.out.println("请输入生日");
        String birthday = scanner.next();//"yyyy-MM-dd"用隐式的转换

        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int insertCount = JDBCUtils.update(sql, name, email, birthday);
        if(0<insertCount){
            System.out.println("操作成功");
        }else{
            System.out.println("操作失败");
        }

    }

    /**
     * 练习插入操作
     */
    @Test
    public void insertTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入用户名：");
        String name = scanner.next();
        System.out.println("请输入邮箱");
        String email = scanner.next();
        System.out.println("请输入生日");
        String birthday = scanner.next();//"yyyy-MM-dd"用隐式的转换

        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int insertCount = JDBCUtils.update(sql, name, email, birthday);
        if(0<insertCount){
            System.out.println("操作成功");
        }else{
            System.out.println("操作失败");
        }
    }


}
