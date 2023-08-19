package com.placementsystem.PlacementManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placementsystem.PlacementManagementSystem.Entity.CompanyProfileEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyProfileEntity, Integer>{
	

}
