package com.crudEmployee.crudEmployee.controller;

import com.crudEmployee.crudEmployee.exception.ResourceNotFound;
import com.crudEmployee.crudEmployee.model.Employee;
import com.crudEmployee.crudEmployee.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){

        return employeeRepo.findAll();
    }

    // create employee rest API
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepo.save(employee);

    }
    // Get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee not found with id : " + id));

        return ResponseEntity.ok(employee);
    }

    // Update employee by id
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id , @RequestBody Employee employeeDetails){

        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee not found with id : " + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepo.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee Api
    @DeleteMapping(value = "/employees/{id}")
     ResponseEntity <Map<String ,Boolean>> deleteEmployee(@PathVariable Long id){
    Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee not found with id : " + id));

    employeeRepo.delete(employee);

    Map<String, Boolean> response = new HashMap<>();

    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);

    }

    //Ready for deployment!

}
