package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.Abbrevations;
import com.dxc.wba.xref.repository.AbbrevationsRepository;
import com.dxc.wba.xref.type.AbbrevationJson;
import com.dxc.wba.xref.type.HeaderType;

@Service
public class AbbrevationsService {

	@Autowired
	AbbrevationsRepository abRepo;

	public AbbrevationJson findAll() {

		AbbrevationJson abbrevationJson = new AbbrevationJson();
		List<Abbrevations> response = abRepo.findAll();

		List<HeaderType> headerList = new ArrayList<>();

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("MEMBER NAME");
		headerType3.setKey("memberName");

		HeaderType headerType4 = new HeaderType();
		headerType4.setHeader("DATASET NAME");
		headerType4.setKey("datasetName");

		headerList.add(headerType4);
		headerList.add(headerType3);

		abbrevationJson.setConfig(headerList);
		abbrevationJson.setData(response);

		return abbrevationJson;
	}

}
