package org.springdataautomappingobjects.repositories;

import org.springdataautomappingobjects.models.Employee;
import org.springdataautomappingobjects.models.dtos.EmployeeInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT new org.springdataautomappingobjects.models.dtos.EmployeeInfoDTO(e.firstName, e.lastName, e.salary, e.birthday) " +
            "FROM Employee e WHERE e.birthday < :before")
    List<EmployeeInfoDTO> findAllByBirthdayBefore(LocalDate before);
}
