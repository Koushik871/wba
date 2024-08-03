package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dxc.wba.xref.dbmodel.DB2PlanEntity;
import com.dxc.wba.xref.dbmodel.TableViewEntity;
import com.dxc.wba.xref.dto.OptionalPlanJson;
import com.dxc.wba.xref.repository.DB2PlanRepository;
import com.dxc.wba.xref.repository.TableViewRepository;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class TableViewServiceImpl implements TableViewService {

	@Autowired
	private TableViewRepository tableViewRepository;

	@Autowired
	private DB2PlanRepository db2planRepository;

	public List<TableViewEntity> getBytableView(String tableView) {
		return tableViewRepository.findBytableView(tableView);
	}

	public List<TableViewEntity> fetchAllTableDatawithWildcard(String tableView) {
		return tableViewRepository.fetchAllTableDatawithWildcard(tableView);
	}

	public List<DB2PlanEntity> getDB2PlandataBydb2plan(String DB2Plan) {
		return db2planRepository.getDB2PlandataBydb2plan(DB2Plan);
	}

	public List<Map<String, Object>> getalldata(String tableView, String string) {
		return tableViewRepository.getAlldata(tableView, string);
	}

	public List<Map<String, Object>> getalldata(String tableView) {

		return tableViewRepository.getAlldata(tableView);
	}

	public List<Map<String, Object>> getalldb2plandata(String tableView, String string) {
		return tableViewRepository.getAlldb2plandata(tableView, string);
	}

	@Override
	public List<Map<String, Object>> getalldb2plandata(String tableView) {
		return tableViewRepository.getAlldb2plandata(tableView);
	}

	@Override
	public List<TableViewEntity> findbyplan(String DB2Plan) {

		return tableViewRepository.db2plandata(DB2Plan);
	}

	@Override
	public List<Object> fetchDataBasedOnOptional(OptionalPlanJson optionalPlanJson) {

		List<TableViewEntity> tableViewEntities = new ArrayList<>();
		List<DB2PlanEntity> dbplan = new ArrayList<>();
		List<Object> combineList = new ArrayList<>();

		try {
			if (!CollectionUtils.isEmpty(optionalPlanJson.getSelectedProcessingType())) {
				checKForWrite(optionalPlanJson);
				switch (optionalPlanJson.getFieldName()) {
				case "db2plan":
					tableViewEntities = getDataBasedOnProgramName(optionalPlanJson);
					dbplan = getDataBasedOnViewNameDbPlan(optionalPlanJson);
					for (TableViewEntity db : tableViewEntities) {
						db.setDBRM(db.getDBRM() + db.getDBRMVersion());

					}

					for (DB2PlanEntity da : dbplan) {
						da.setDBRM(da.getDBRM() + da.getDBRMVersion());

					}
					combineList.addAll(tableViewEntities);
					combineList.addAll(dbplan);
					break;

				case "table_view":
					tableViewEntities = getDataBasedOnViewName(optionalPlanJson);
					dbplan = getDataBasedOnViewNameDb(optionalPlanJson);
					for (TableViewEntity db : tableViewEntities) {
						db.setDBRM(db.getDBRM() + db.getDBRMVersion());

					}

					for (DB2PlanEntity da : dbplan) {
						da.setDBRM(da.getDBRM() + da.getDBRMVersion());

					}
					combineList.addAll(tableViewEntities);
					combineList.addAll(dbplan);
					break;

				default:

					break;
				}
			} else {
				switch (optionalPlanJson.getFieldName()) {
				case "db2plan":
					tableViewEntities = tableViewRepository.db2plandata(optionalPlanJson.getFieldValue());
					dbplan = db2planRepository.getDB2PlandataBydb2plan(optionalPlanJson.getFieldValue());
					for (TableViewEntity db : tableViewEntities) {
						db.setDBRM(db.getDBRM() + db.getDBRMVersion());

					}

					for (DB2PlanEntity da : dbplan) {
						da.setDBRM(da.getDBRM() + da.getDBRMVersion());

					}
					combineList.addAll(tableViewEntities);
					combineList.addAll(dbplan);
					break;

				case "table_view":
					tableViewEntities = tableViewRepository.findBytableView(optionalPlanJson.getFieldValue());
					dbplan = db2planRepository.findBytableViews(optionalPlanJson.getFieldValue());
					for (TableViewEntity db : tableViewEntities) {
						db.setDBRM(db.getDBRM() + db.getDBRMVersion());

					}

					for (DB2PlanEntity da : dbplan) {
						da.setDBRM(da.getDBRM() + da.getDBRMVersion());

					}
					combineList.addAll(tableViewEntities);
					combineList.addAll(dbplan);
					break;

				default:

					break;
				}
			}
			Collections.sort(combineList, (obj1, obj2) -> {
				String dbrm1 = "";
				String dbrm2 = "";

				// Cast each object to a DB2PlanEntity or TableViewEntity and get the DBRM value
				if (obj1 instanceof DB2PlanEntity) {
					dbrm1 = ((DB2PlanEntity) obj1).getDBRM();
				} else if (obj1 instanceof TableViewEntity) {
					dbrm1 = ((TableViewEntity) obj1).getDBRM();
				}

				if (obj2 instanceof DB2PlanEntity) {
					dbrm2 = ((DB2PlanEntity) obj2).getDBRM();
				} else if (obj2 instanceof TableViewEntity) {
					dbrm2 = ((TableViewEntity) obj2).getDBRM();
				}

				return dbrm1.compareTo(dbrm2);
			});

			return combineList;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return combineList;
	}

	private void checKForWrite(OptionalPlanJson optionalPlanJson) {

		if (optionalPlanJson.getSelectedProcessingType().contains("Write")
				|| optionalPlanJson.getSelectedProcessingType().contains("WRITE")
				|| optionalPlanJson.getSelectedProcessingType().contains("write")) {
			List<String> list = new ArrayList<>();

			if (optionalPlanJson.getSelectedProcessingType().contains("SELECT")) {
				list.add("SELECT");
			}

			if (optionalPlanJson.getSelectedProcessingType().contains("PREPARE")) {
				list.add("PREPARE");
			}

			list.add("INSERT");
			list.add("UPDATE");
			list.add("DELETE");
//			list.add("SELECT");
//			list.add("PREPARE");

			optionalPlanJson.setSelectedProcessingType(list);

		}

	}

	private List<DB2PlanEntity> getDataBasedOnViewNameDb(OptionalPlanJson optionalPlanJson) {
		List<DB2PlanEntity> entities = new ArrayList<>();

		for (String table_view : optionalPlanJson.getSelectedProcessingType()) {

			List<DB2PlanEntity> entitie = new ArrayList<>();
			optionalPlanJson.getSelectedProcessingType();

			entitie = db2planRepository.findByDB2PlanOrTableView(optionalPlanJson.getFieldValue(), table_view);

			entities.addAll(entitie);
		}
		return entities;

	}

	private List<DB2PlanEntity> getDataBasedOnViewNameDbPlan(OptionalPlanJson optionalPlanJson) {
		List<DB2PlanEntity> entities = new ArrayList<>();

		for (String DB2Plan : optionalPlanJson.getSelectedProcessingType()) {

			List<DB2PlanEntity> entitie = new ArrayList<>();
			optionalPlanJson.getSelectedProcessingType();

			entitie = db2planRepository.findByDB2PlanOrTableViews(optionalPlanJson.getFieldValue(), DB2Plan);

			entities.addAll(entitie);
		}
		return entities;

	}

	private List<TableViewEntity> getDataBasedOnViewName(OptionalPlanJson optionalPlanJson) {
		List<TableViewEntity> entities = new ArrayList<>();

		for (String table_view : optionalPlanJson.getSelectedProcessingType()) {

			List<TableViewEntity> entitie = new ArrayList<>();
			optionalPlanJson.getSelectedProcessingType();

			entitie = tableViewRepository.findByDB2PlanOrTableView(optionalPlanJson.getFieldValue(), table_view);

			entities.addAll(entitie);
		}
		return entities;

	}

	private List<TableViewEntity> getDataBasedOnProgramName(OptionalPlanJson optionalPlanJson) {
		List<TableViewEntity> entities = new ArrayList<>();

		for (String DB2Plan : optionalPlanJson.getSelectedProcessingType()) {
			List<TableViewEntity> entitie = new ArrayList<>();
			entitie = tableViewRepository.findByDB2PlanOrTableViews(optionalPlanJson.getFieldValue(), DB2Plan);
			entities.addAll(entitie);
		}

		return entities;

	}

}
