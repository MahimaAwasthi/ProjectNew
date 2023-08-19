package com.placementsystem.PlacementManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placementsystem.PlacementManagementSystem.Entity.CompanyProfileEntity;
import com.placementsystem.PlacementManagementSystem.Exception.MyException;
import com.placementsystem.PlacementManagementSystem.Repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository repository;
	
	public void addCompanyProfile(CompanyProfileEntity entity)
	{
		/*if(repository.existsById(entity.getId()))
			throw new MyException(String.format("Company Profile for id %s already exists",entity.getId()));
		else*/
		repository.save(entity);
	}
	
	public void modifyCompanyProfile(int id, CompanyProfileEntity entity)
	{
		if(repository.existsById(id))
		{
		CompanyProfileEntity e1 = repository.findById(id).orElseThrow(()-> new MyException(String.format("Company Profile for id %s not found",id)));
		if(entity.getCompany_name()!="")
			e1.setCompany_name(entity.getCompany_name());
	
		
		if(entity.getExperience()!=0)
			e1.setExperience(entity.getExperience());
		
		
		if(entity.getTechnical_requirement()!="")
			e1.setTechnical_requirement(entity.getTechnical_requirement());

		
		if(entity.getPackage_offered()!=0)
			e1.setPackage_offered(entity.getPackage_offered());
		
		repository.save(e1);
		}
		else
			throw new MyException(String.format("Company Profile for id %s not found",id));
		
	}
	
	public void deleteCompanyProfile(int id)
	{
		if(repository.existsById(id))
		repository.deleteById(id);
		else
		throw new MyException(String.format("Company Profile for id %s not found",id));
	}
	
	public CompanyProfileEntity showCompanyProfile(int id)
	{
		return repository.findById(id).orElseThrow(()-> new MyException(String.format("Company Profile for id %s not found",id)));
		
	}

}
