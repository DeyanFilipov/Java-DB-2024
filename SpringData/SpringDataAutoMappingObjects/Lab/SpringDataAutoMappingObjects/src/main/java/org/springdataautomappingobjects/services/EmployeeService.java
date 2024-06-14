package org.springdataautomappingobjects.services;

import org.springdataautomappingobjects.models.dtos.EmployeeInfoDTO;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {

    List<EmployeeInfoDTO> findInfoForBornBefore(LocalDate before);
}
