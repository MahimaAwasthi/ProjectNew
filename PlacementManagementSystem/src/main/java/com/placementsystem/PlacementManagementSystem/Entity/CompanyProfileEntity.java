package com.placementsystem.PlacementManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CompanyProfileEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String company_name;
	String technical_requirement;
	int experience;
	int package_offered;
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTechnical_requirement() {
		return technical_requirement;
	}
	public void setTechnical_requirement(String technical_requirement) {
		this.technical_requirement = technical_requirement;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getPackage_offered() {
		return package_offered;
	}
	public void setPackage_offered(int package_offered) {
		this.package_offered = package_offered;
	}
	

}
