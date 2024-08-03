package com.dxc.wba.xref.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.Abbrevations;

@Repository
public interface AbbrevationsRepository extends JpaRepository<Abbrevations, Long>{


	
	

}
