package by.tr.web.domain;

import java.io.Serializable;

public class Parent implements Serializable {
    private static final long serialVersionUID = -820690460107193006L;

    private String firstName;
    private String surname;
    private String patronymic;
    private double salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        if (Double.compare(parent.salary, salary) != 0) return false;
        if (firstName != null ? !firstName.equals(parent.firstName) : parent.firstName != null) return false;
        if (surname != null ? !surname.equals(parent.surname) : parent.surname != null) return false;
        return patronymic != null ? patronymic.equals(parent.patronymic) : parent.patronymic == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
