package org.springdataautomappingobjects.models.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeInfoDTO {
    private String firsName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;

    public EmployeeInfoDTO() {}

    public EmployeeInfoDTO(String firsName, String lastName, BigDecimal salary, LocalDate birthday) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfoDTO that = (EmployeeInfoDTO) o;
        return Objects.equals(firsName, that.firsName) && Objects.equals(lastName, that.lastName) && Objects.equals(salary, that.salary) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firsName, lastName, salary, birthday);
    }
}
