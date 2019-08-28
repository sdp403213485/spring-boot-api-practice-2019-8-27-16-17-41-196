package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")

public class CompanyResource {
	
	
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
	
	List<Employee> aliEmployees = new ArrayList<Employee>(); 
	{
		aliEmployees.add(new Employee(1, "ali1", 20, "20000"));
		aliEmployees.add(new Employee(2, "ali2", 35, "30000"));
		aliEmployees.add(new Employee(3, "ali3", 40, "40000"));
	}
	
	List<Employee> TxEmployees = new ArrayList<Employee>(); 
	{
		TxEmployees.add(new Employee(4, "TX1", 40, "40000"));
		TxEmployees.add(new Employee(5, "TX2", 23, "10000"));
		TxEmployees.add(new Employee(6, "TX3", 33, "20000"));
	}
	
	List<Employee> HwEmployees = new ArrayList<Employee>(); 
	{
		HwEmployees.add(new Employee(7, "HW1", 40, "40000"));
		HwEmployees.add(new Employee(8, "HW2", 23, "30000"));
		HwEmployees.add(new Employee(9, "HW3", 33, "30000"));
	}
	
	 List<Company> companies = new ArrayList<Company>();
	{
		companies.add(new Company("1", "ali", 200, aliEmployees));
		companies.add(new Company("2", "TX", 200, TxEmployees));
		companies.add(new Company("3", "HW", 200, HwEmployees));
	}
	


	//获取公司列表
    @GetMapping(path = "/companies/")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAll() {
        return companies;
    }
    

    
    //获取某个特定的公司
    @GetMapping(path = "/companies/{id}")
    public ResponseEntity<Company> getOneCompany (@PathVariable String id) {
       for(Company c : companies) {
    	   if(c.getCompanyID().equals(id)) {
    		   return ResponseEntity.ok(c);
    	   }
       }
    	
       return ResponseEntity.notFound().build();
    }
    
    
    //获取某个公司下的所有员工
    @GetMapping(path = "/companies/{id}/employees")
    public ResponseEntity<List<Employee>> getOneCompanyEmployees(@PathVariable String id){
    	for(Company c : companies) {
    		if(c.getCompanyID().equals(id)) {
    			return ResponseEntity.ok(c.getEmployees());
    		}
    	}
    	return ResponseEntity.notFound().build();
    }
    
    //添加一个公司
	@PostMapping(path ="/companies/")
	@ResponseStatus(HttpStatus.CREATED)
	public Company creatEmployee(@RequestBody Company company) {
			companies.add(company);
			return company;
	}
    
	//更新一个公司
	@PutMapping(path = "/companies/{id}/")
	public ResponseEntity<Company> updateCompany(@PathVariable String id,@RequestBody Company changecompany){
		for(Company company:companies) {
			if(company.getCompanyID().equals(id)) {
				company.setCompanyID(changecompany.getCompanyID());
				company.setCompanyName(changecompany.getCompanyName());
				company.setEmployees(changecompany.getEmployees());
				company.setEmployeesNumber(changecompany.getEmployeesNumber());
				return ResponseEntity.ok(company);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	//删除一个公司
	@DeleteMapping(path = "/companies/{id}/")
	public ResponseEntity<String> deletCompany(@PathVariable String id) {
		Iterator<Company> iterator = companies.iterator();
		  while (iterator.hasNext()) {
			   if(iterator.next().getCompanyID() == id) {
			    iterator.remove();
			    return ResponseEntity.ok(id);
			   }

			}
		  return ResponseEntity.notFound().build();
	}


}