package org.springdataautomappingobjects.Lab;

import org.modelmapper.ModelMapper;
import org.springdataautomappingobjects.Lab.models.Employee;
import org.springdataautomappingobjects.Lab.models.EmployeeDTO;
import org.springdataautomappingobjects.Lab.models.ManagerDTO;
import org.springdataautomappingobjects.Lab.models.WorkStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LabMain {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        Employee manager = new Employee("Manager", "LManager", BigDecimal.TEN, LocalDate.now(), "Plovid",
                WorkStatus.PAID_TIME_OFF, null, List.of());
        Employee employee = new Employee
                ("First", "Last", BigDecimal.ONE, LocalDate.now(), "Sofia",
                        WorkStatus.PRESENT, null, List.of());
        manager.setEmployees(List.of(employee));

        EmployeeDTO result = modelMapper.map(employee, EmployeeDTO.class);
        ManagerDTO managerDTO = modelMapper.map(manager, ManagerDTO.class);

        System.out.println(result);
        System.out.println(managerDTO);

    }
}
