package org.springdataautomappingobjects;

import org.modelmapper.ModelMapper;
import org.springdataautomappingobjects.models.Address;
import org.springdataautomappingobjects.models.Employee;
import org.springdataautomappingobjects.models.dtos.BasicEmployeeDTO;
import org.springdataautomappingobjects.models.dtos.EmployeeInfoDTO;
import org.springdataautomappingobjects.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class MainRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    public MainRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
//        Address address = new Address("Bg", "Plovidv", "Marica");
//        Employee employee = new Employee("First", "Last", BigDecimal.TEN, address, LocalDate.now());
//
//        BasicEmployeeDTO employeeDto = modelMapper.map(employee, BasicEmployeeDTO.class);

        List<EmployeeInfoDTO> list = employeeService.findInfoForBornBefore(LocalDate.of(1990, 01, 01));
        System.out.println("HELLO WORLD!");
    }
}
