package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class EmployeeResource {

	
	 List<Employee> employees = new ArrayList<Employee>(); 
	{
		  employees.add(new Employee(1, "ali1", 20, "20000"));
		  employees.add(new Employee(2, "ali2", 35, "30000"));
		  employees.add(new Employee(3, "ali3", 40, "40000"));
		  employees.add(new Employee(4, "TX1", 40, "40000"));
		  employees.add(new Employee(5, "TX2", 23, "10000"));
		  employees.add(new Employee(6, "TX3", 33, "20000"));
		  employees.add(new Employee(7, "HW1", 40, "40000"));
		  employees.add(new Employee(8, "HW2", 23, "30000"));
		  employees.add(new Employee(9, "HW3", 33, "30000"));
	}
	
	//获取雇员列表
    @GetMapping(path = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAll() {
        return employees;
    }
    
    
    //获取某个雇员
    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getOneEmployee (@PathVariable int id) {
       for(Employee e : employees) {
    	   if(e.getId()==id) {
    		   return ResponseEntity.ok(e);
    	   }
       }
       return ResponseEntity.notFound().build();
    }
    
    //添加一个雇员
  	@PostMapping(path ="/employees")
  	@ResponseStatus(HttpStatus.CREATED)
  	public Employee creatEmployee(@RequestBody Employee employee) {
  		employees.add(employee);
  			return employee;
  	}
  	
	//删除一个公司
	@DeleteMapping(path = "/employees/{id}")
	public ResponseEntity<Integer> deletEmployee(@PathVariable int id) {
		Iterator<Employee> iterator = employees.iterator();
		  while (iterator.hasNext()) {
			   if(iterator.next().getId() == id) {
			    iterator.remove();
			    return ResponseEntity.ok(id);
			   }

			}
		  return ResponseEntity.notFound().build();
	}
}
