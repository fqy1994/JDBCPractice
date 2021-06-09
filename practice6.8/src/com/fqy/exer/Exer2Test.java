package com.fqy.exer;

import com.fqy.util.JDBCUtils;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/9 13:25
 */
public class Exer2Test {
    public static void main(String[] args) {
        System.out.println("请输入学生的考号：");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();
        String sql = "delete from examstudent where examCard = ?";
        int deleteCount = JDBCUtils.update(sql, examCard);
        if (0 < deleteCount) {
            System.out.println("删除成功");
        } else {
            System.out.println("查无此人");
        }
    }



    //问题1；想examstudent表中添加一条记录
    @Test
    public void insertTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("四级六级：");
        int type = scanner.nextInt();
        System.out.println("身份证号：");
        String IDCard = scanner.next();
        System.out.println("准考证号");
        String examCard = scanner.next();
        System.out.println("学生姓名：");
        String studentName = scanner.next();
        System.out.println("所在城市");
        String location = scanner.next();
        System.out.println("考试成绩：");
        int grade = scanner.nextInt();

        String sql = "inser into examstudent(type,IDCard,examCard,studentName,location)values(?,?,?,?,?,?)";
        int inserCount = JDBCUtils.update(sql, type, IDCard, examCard, studentName, location, grade);
        if (0 < inserCount) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }


    }

    /**
     * 查询信息，根据身份证号
     */
    @Test
    public void queryWithIDCardExamCardTest() {
        System.out.println("请选择你要输入的类型：");
        System.out.println("a.准考证号");
        System.out.println("b.身份证号");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        if ("a".equalsIgnoreCase(selection)) {
            System.out.println("请输入准考证号");
            String examCard = scanner.next();
            String sql = "select flowID,type,IDCard,examCard,StudentName name,location,grade from examstudent where examcard=?";

            List<Student> forlist = JDBCUtils.getForlist(Student.class, sql, examCard);
            if (forlist != null) {
                System.out.println(forlist);
            } else {
                System.out.println("输入的准考证号有误！");
            }


        } else if ("b".equalsIgnoreCase(selection)) {

        } else {
            System.out.println("你的输入有误请重新进入程序");
        }


    }

    /**
     * 删除指定的学生信息
     */
    @Test
    public void DeleteByExamCardTest() {
        System.out.println("请输入学生的考号：");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();
        String sql = "select flowID,type,IDCard,examCard,StudentName name,location,grade from examstudent where examcard=?";
        List<Student> forlist = JDBCUtils.getForlist(Student.class, sql, examCard);
        if (forlist == null) {
            System.out.println("查无此人请重新输入！");
        } else {
            String sql1 = "delete from examstudent where examCard = ?";
            int deleteCount = JDBCUtils.update(sql1, examCard);
            if (0 < deleteCount) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        }


    }
}
