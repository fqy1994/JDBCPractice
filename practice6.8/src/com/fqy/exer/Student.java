package com.fqy.exer;

/**
 * @author fan_jennifer
 * @create 2021-06-2021/6/9 13:40
 */
public class Student {
    private int flowID;//流水号
    private int type;//考试类型
    private String IDCard;
    private String examCard;
    private String name;
    private String location;
    private int grade;

    public Student() {
    }

    public Student(int flowID, int type, String IDCard, String examCard, String name, String location, int grade) {
        this.flowID = flowID;
        this.type = type;
        this.IDCard = IDCard;
        this.examCard = examCard;
        this.name = name;
        this.location = location;
        this.grade = grade;
    }

    public int getFlowID() {
        return flowID;
    }

    public void setFlowID(int flowID) {
        this.flowID = flowID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExamCard() {
        return examCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        System.out.println("================查询结果===============");
        return info();
    }

    public String info(){
        return "流水号：" +flowID +"\n四级/六级:" + type +"\n身份证号: " +IDCard +"\n准考证号: " +examCard +
                "\n学生姓名: " +name + "\n区域: " + location + "\n成绩: " +grade;
    }
}
