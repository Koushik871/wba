package com.dxc.wba.xref.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.TableViewEntity;
import com.dxc.wba.xref.dto.OptionalPlanJson;

@Service
public interface TableViewService {

	public List<Object> fetchDataBasedOnOptional(OptionalPlanJson optionalPlanJson);

	public List<TableViewEntity> findbyplan(String DB2Plan);

	public List<Map<String, Object>> getalldb2plandata(String tableView);

	

	

}
