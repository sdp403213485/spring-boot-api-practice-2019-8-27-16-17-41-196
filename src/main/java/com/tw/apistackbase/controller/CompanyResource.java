package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")

public class CompanyResource {
	
	List<Company> companies = new ArrayList<Company>();

	//1
    @GetMapping(path = "/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAll() {
        return companies;
    }

    //2
    @GetMapping(path = "/companies/{id}")
    public ResponseEntity<Company> getOneCompany (@PathVariable String id) {
       for(Company c : companies) {
    	   if(c.getCompanyID().equals(id)) {
    		   return ResponseEntity.ok(c);
    	   }
       }
    	
       return ResponseEntity.notFound().build();
    }
    
    
    
    //3
    
    @GetMapping(path = "/companies/{id}/employees")
    public ResponseEntity<List<Employee>> getOneCompanyEmployees(@PathVariable String id){
    	for(Company c : companies) {
    		if(c.getCompanyID().equals(id)) {
    			return ResponseEntity.ok(c.getEmployees());
    		}
    	}
    	
    	
    	return ResponseEntity.notFound().build();
    }
    
    
//	@GetMapping(path = "/{id}")
//	public ResponseEntity<Employee> getOneEmployee(@PathVariable String id){
//		for(Employee employee:employees) {
//			if(employee.getID().equals(id)) {
//				return ResponseEntity.ok(employee);
//			}
//		}
//		return ResponseEntity.notFound().build();
//	}

}