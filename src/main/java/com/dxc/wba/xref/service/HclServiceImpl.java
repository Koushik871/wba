package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.HclCalender;
import com.dxc.wba.xref.repository.HclCalenderRepository;
import com.dxc.wba.xref.type.TextResponse;

@Service
public class HclServiceImpl implements HclService {

	@Autowired
	HclCalenderRepository hclRepo;

//	public List<HclCalender> findbymemberName(String memberName) {
//
//		return hclRepo.findbyMembername(memberName);
//	}

	@Override
	public Object findbymemberName() {

		List<HclCalender> hclCalender = null;
		try {

//			hclCalender = hclRepo.findbyMembername(memberName);
			hclCalender = hclRepo.findAll();
//			hclCalender.stream().distinct().collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("hello");
		}

		TextResponse response = new TextResponse();
		Object val = null;

		List<String> list = new ArrayList<>();

		for (HclCalender value : hclCalender) {

			list.add(value.getLineText());
		}

		return list;
	}

}
