package com.dxc.wba.xref.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.ApplicationGroup;
import com.dxc.wba.xref.repository.ApplicationGroupRepository;

@Service
public class ApplicationGroupService {
	
	@Autowired
	private ApplicationGroupRepository  applicationGroupRepository;

	public List<ApplicationGroup> getAllApplications() {
		return applicationGroupRepository.findAll();
	}

	public List<ApplicationGroup> getApplicationsByapplId(String applGroup) {
	
		return applicationGroupRepository.getAllApplicationsByapplId(applGroup);
	}

	public List<ApplicationGroup> getApplicationsByWildcards(String applGroup) {
		return applicationGroupRepository.getAllByWildcards(applGroup);
	}

}
