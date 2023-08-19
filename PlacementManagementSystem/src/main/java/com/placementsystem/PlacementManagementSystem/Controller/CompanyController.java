package com.placementsystem.PlacementManagementSystem.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.placementsystem.PlacementManagementSystem.Entity.CompanyProfileEntity;
import com.placementsystem.PlacementManagementSystem.Exception.MyException;
import com.placementsystem.PlacementManagementSystem.Service.CompanyService;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {
	
	@Autowired
	CompanyService service;
		
	@PostMapping("/addCompanyProfile")
	public void addCompanyProfile(@RequestBody CompanyProfileEntity entity)
	{
		service.addCompanyProfile(entity);
	}
	
	@PutMapping("/modifyCompanyProfile/{id}")
	public void modifyCompanyProfile(@PathVariable int id, @RequestBody CompanyProfileEntity entity)
	{
		service.modifyCompanyProfile(id, entity);
	}
	
	@DeleteMapping("/deleteCompanyProfile/{id}")
	public void deleteCompanyProfile(@PathVariable int id)
	{
		service.deleteCompanyProfile(id);
	}
	
	@GetMapping("/showCompanyProfile/{id}")
	public CompanyProfileEntity showCompanyProfile(@PathVariable int id)
	{
		return service.showCompanyProfile(id);
	}
    
	@ExceptionHandler(MyException.class)
	public MyException handleException(MyException ex)
	{
		return new MyException(ex.getMessage());
	}
}
