package by.tr.web.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 7627527785535323891L;

    private int id;

    private String firstName;
    private String surname;
    private String patronymic;

    private Parent father;
    private Parent mother;

    private int brothersAmount;
    private int sistersAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (brothersAmount != student.brothersAmount) return false;
        if (sistersAmount != student.sistersAmount) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(student.patronymic) : student.patronymic != null) return false;
        if (father != null ? !father.equals(student.father) : student.father != null) return false;
        return mother != null ? mother.equals(student.mother) : student.mother == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (father != null ? father.hashCode() : 0);
        result = 31 * result + (mother != null ? mother.hashCode() : 0);
        result = 31 * result + brothersAmount;
        result = 31 * result + sistersAmount;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public int getBrothersAmount() {
        return brothersAmount;
    }

    public void setBrothersAmount(int brothersAmount) {
        this.brothersAmount = brothersAmount;
    }

    public int getSistersAmount() {
        return sistersAmount;
    }

    public void setSistersAmount(int sistersAmount) {
        this.sistersAmount = sistersAmount;
    }
}
