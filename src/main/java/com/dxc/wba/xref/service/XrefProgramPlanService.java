package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.DB2PlanEntity;
import com.dxc.wba.xref.dbmodel.TableViewEntity;
import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import com.dxc.wba.xref.dbmodel.XrefProgramPlan;
import com.dxc.wba.xref.dto.CombineData;
import com.dxc.wba.xref.dto.DataCombineConfig;
import com.dxc.wba.xref.dto.DbCombineData;
import com.dxc.wba.xref.repository.DB2PlanRepository;
import com.dxc.wba.xref.repository.TableViewRepository;
import com.dxc.wba.xref.repository.XrefProcJobProgmRepository;
import com.dxc.wba.xref.repository.XrefProgramPlanRepository;
import com.dxc.wba.xref.type.HeaderType;

@Service
public class XrefProgramPlanService {

	@Autowired
	private XrefProgramPlanRepository xrefProgramPlanRepository;

	@Autowired
	private DB2PlanRepository dbRepo;

	@Autowired
	private TableViewRepository tableViewRepository;

	@Autowired
	private XrefProcJobProgmRepository xrefProcJobProgmRepository;

	public List<XrefProgramPlan> getAllJobPrograms() throws Exception {
		List<XrefProgramPlan> progmList = xrefProgramPlanRepository.findAll();
		return progmList;
	}

	public List<XrefProgramPlan> getProgramPlanByprogName(String progName) {
		return xrefProgramPlanRepository.getProgramPlanByprogName(progName);
	}

	public List<XrefProgramPlan> fetchAllprogNameDatawithwildcards(String progName) {
		// TODO Auto-generated method stub
		return xrefProgramPlanRepository.getSuggetionsByprogName(progName);
	}

	public List<XrefProgramPlan> getDataOfProgram(String progName) {
		// TODO Auto-generated method stub
		return xrefProgramPlanRepository.getDataOfProgram(progName);
	}

	public List<XrefProcJobProgm> getProgramPlanByprogNameAndMainframeDomains(String progName, String mainframeDomain) {
		return xrefProcJobProgmRepository.getProgramPlanByprogNameAndMainframeDomain(progName, mainframeDomain);
	}

	public DbCombineData findAll(String progName) {

		List<DB2PlanEntity> a = dbRepo.findAll();

		List<XrefProgramPlan> b = xrefProgramPlanRepository.findAll();

		List<TableViewEntity> table = tableViewRepository.findAll();

		List<XrefProgramPlan> programList = new ArrayList<>();
		List<DB2PlanEntity> tableView = new ArrayList<>();

		CombineData combineData = new CombineData();
		DbCombineData dbCombineData = new DbCombineData();

		DataCombineConfig config = getAllHeaderDetails();

		for (XrefProgramPlan xrefProgramPlan : b) {

			if (xrefProgramPlan.getProgName().startsWith(progName)) {

				programList.add(xrefProgramPlan);
			}
		}

		for (TableViewEntity view : table) {

			if (view.getDb2plan().equalsIgnoreCase(progName)) {
				DB2PlanEntity db2PlanEntity = new DB2PlanEntity();

				db2PlanEntity.setDb2plan(view.getDb2plan());
				db2PlanEntity.setMainframeDomain(view.getMainframeDomain());
				db2PlanEntity.setSSID(view.getSSID());
				db2PlanEntity.setDB2Planver(view.getDB2Planver());
				db2PlanEntity.setDBRM(view.getDBRM());
				db2PlanEntity.setDBRMVersion(view.getDBRMVersion());
				db2PlanEntity.setVerb(view.getVerb());
				db2PlanEntity.setTableView(view.getTableView());
				db2PlanEntity.setOwner(view.getOwner());
				db2PlanEntity.setTimestamps(view.getTimestamps());
				db2PlanEntity.setStmt(view.getStmt());
				db2PlanEntity.setMeaning("View as CODED IN SQL");

				tableView.add(db2PlanEntity);
			}
		}

		for (DB2PlanEntity db : a) {
			db.setDBRM(db.getDBRM() + " " + db.getDBRMVersion());

			if (db.getDb2plan().startsWith(progName)) {
				db.setDBRM(db.getDBRM() + " " + db.getDBRMVersion());
				db.setMeaning("TABLE DERIVED FROM SQL");

				db.setDb2plan(db.getDb2plan() + " " + db.getDB2Planver());

				tableView.add(db);

			}
		}

//		combineData.setProgram(programList);
		combineData.setTableView(tableView);
		dbCombineData.setConfig(getAllHeaderDetails());
		dbCombineData.setData(combineData);

		return dbCombineData;

	}

	public DbCombineData getProgramPlanByprogNameAndMainframeDomain(String progName, String mainframeDomain) {
		List<DB2PlanEntity> a = new ArrayList<>();
		List<XrefProcJobProgm> b = new ArrayList<>();
		List<TableViewEntity> table = new ArrayList<>();

		try {
			if ("PROD".equalsIgnoreCase(mainframeDomain)) {
				a = dbRepo.findByDb2planStartingWithAndMainframeDomain(progName, "Prod");
				b = xrefProcJobProgmRepository.findByProgNameStartingWithAndMainframeDomain(progName, "Prod");
				table = tableViewRepository.findByDb2PlanStartingWithIgnoreCaseAndMainframeDomain(progName, "Prod");
			} else if ("DALLAS".equalsIgnoreCase(mainframeDomain)) {
				a = dbRepo.findByDb2planStartingWithAndMainframeDomain(progName, "Dallas");
				b = xrefProcJobProgmRepository.findByProgNameStartingWithAndMainframeDomain(progName, "Dallas");
				table = tableViewRepository.findByDb2PlanStartingWithIgnoreCaseAndMainframeDomain(progName, "Dallas");
			}

			List<XrefProcJobProgm> programList = b.stream().filter(x -> x.getProgName().startsWith(progName))
					.collect(Collectors.toList());

//			programList = programList.stream().filter(x -> !x.getAppId().equalsIgnoreCase("N/A")).collect(Collectors.toList());

			List<DB2PlanEntity> derivedFromSQL = a.stream().filter(db -> db.getDBRM().startsWith(progName)).map(db -> {
//						db.setMeaning("TABLE DERIVED FROM SQL");
				db.setDb2plan(db.getDb2plan() + " " + db.getDB2Planver());
				db.setDBRM(db.getDBRM() + " " + db.getDBRMVersion());

				String thirdElement = db.getTableView().toString().substring(2, 3);
				if (thirdElement.equals("V")) {
					db.setMeaning("View as coded in SQL");
				} else if (thirdElement.equals("T")) {
					db.setMeaning("TABLE DERIVED FROM SQL");
				} else {
					db.setMeaning("TABLE DERIVED FROM SQL");
				}
				return db;
			}).sorted(Comparator.comparing(db -> (String) db.getDBRM())).collect(Collectors.toList());

			List<DB2PlanEntity> tableView = table.stream().map(view -> {
				DB2PlanEntity db2PlanEntity = new DB2PlanEntity();
				db2PlanEntity.setDb2plan(view.getDb2plan());
				db2PlanEntity.setMainframeDomain(view.getMainframeDomain());
				db2PlanEntity.setSSID(view.getSSID());
				db2PlanEntity.setDB2Planver(view.getDB2Planver());
				db2PlanEntity.setDBRM(view.getDBRM() + " " + view.getDBRMVersion());
				db2PlanEntity.setDBRMVersion(view.getDBRMVersion());
				db2PlanEntity.setVerb(view.getVerb());
				db2PlanEntity.setTableView(view.getTableView());
				db2PlanEntity.setOwner(view.getOwner());
				db2PlanEntity.setTimestamps(view.getTimestamps());
				db2PlanEntity.setStmt(view.getStmt());
//				db2PlanEntity.setMeaning("View as CODED IN SQL");
				String thirdElement = view.getTableView().toString().substring(2, 3);
				if (thirdElement.equals("V")) {
					db2PlanEntity.setMeaning("View as coded in SQL");
				} else if (thirdElement.equals("T")) {
					db2PlanEntity.setMeaning("TABLE DERIVED FROM SQL");
				} else {
					db2PlanEntity.setMeaning("View as coded in SQL");
				}

				return db2PlanEntity;
			}).sorted(Comparator.comparing(db -> (String) db.getDBRM())).collect(Collectors.toList());

			List<DB2PlanEntity> combinedList = new ArrayList<>();
			combinedList.addAll(derivedFromSQL);
			combinedList.addAll(tableView);

//			Collections.sort(combinedList, Comparator.comparing(DB2PlanEntity::getStmt));

			CombineData combineData = new CombineData();
			programList = programList.stream().filter(x -> !x.getAppId().equalsIgnoreCase("N/A"))
					.collect(Collectors.toList());
			combineData.setProgram(programList);
			combineData.setTableView(combinedList);

			DbCombineData dbCombineData = new DbCombineData();
			dbCombineData.setConfig(getAllHeaderDetails());
			dbCombineData.setData(combineData);

			return dbCombineData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public DbCombineData getProgramPlanByprogNameAndMainframeDomain(String progName, String mainframeDomain) {
//
//		List<DB2PlanEntity> a = new ArrayList<>();
//		List<XrefProcJobProgm> b = new ArrayList<>();
//		List<TableViewEntity> table = new ArrayList<>();
//		try {
//			if ("PROD".equals(mainframeDomain) || "Prod".equals(mainframeDomain)) {
//				a = dbRepo.findByDb2planStartingWithAndMainframeDomain(progName, "Prod");
//				b = xrefProcJobProgmRepository.findByProgNameStartingWithAndMainframeDomain(progName, "Prod");
//				table = tableViewRepository.findByDb2PlanStartingWithIgnoreCaseAndMainframeDomain(progName, "Prod");
//			} else if ("DALLAS".equals(mainframeDomain) || "Dallas".equals(mainframeDomain)) {
//				a = dbRepo.findByDb2planStartingWithAndMainframeDomain(progName, "Dallas");
//				b = xrefProcJobProgmRepository.findByProgNameStartingWithAndMainframeDomain(progName, "Dallas");
//				table = tableViewRepository.findByDb2PlanStartingWithIgnoreCaseAndMainframeDomain(progName, "Dallas");
//			}
//
//			List<XrefProcJobProgm> programList = b.stream().filter(x -> x.getProgName().startsWith(progName))
//					.collect(Collectors.toList());
////		List<DB2PlanEntity> tableView = new ArrayList<>();
//
//			List<DB2PlanEntity> tableView = table.stream().map(view -> {
//				DB2PlanEntity db2PlanEntity = new DB2PlanEntity();
//				db2PlanEntity.setDb2plan(view.getDB2Plan());
//				db2PlanEntity.setMainframeDomain(view.getMainframeDomain());
//				db2PlanEntity.setSSID(view.getSSID());
//				db2PlanEntity.setDB2Planver(view.getDB2Planver());
//				db2PlanEntity.setDBRM(view.getDBRM());
//				db2PlanEntity.setDBRMVersion(view.getDBRMVersion());
//				db2PlanEntity.setVerb(view.getVerb());
//				db2PlanEntity.setTableView(view.getTableView());
//				db2PlanEntity.setOwner(view.getOwner());
//				db2PlanEntity.setTimestamps(view.getTimestamps());
//				db2PlanEntity.setStmt(view.getStmt());
//				db2PlanEntity.setMeaning("View as CODED IN SQL");
//				return db2PlanEntity;
//			}).collect(Collectors.toList());
//
//			a.stream().filter(db -> db.getDb2plan().startsWith(progName)).forEach(db -> {
//				db.setMeaning("TABLE DERIVED FROM SQL");
//				db.setDb2plan(db.getDb2plan() + " " + db.getDB2Planver());
////			db.setDBRM(db.getDBRM() + " " + db.getDBRMVersion());
//				tableView.add(db);
//			});
//
//			Collections.sort(tableView, Comparator.comparing(DB2PlanEntity::getStmt));
////		List<DB2PlanEntity> uniqueTableView = tableView.stream().distinct().collect(Collectors.toList());
//
//			CombineData combineData = new CombineData();
//			combineData.setProgram(programList);
//			combineData.setTableView(tableView);
//
//			DbCombineData dbCombineData = new DbCombineData();
//			dbCombineData.setConfig(getAllHeaderDetails());
//			dbCombineData.setData(combineData);
//
//			return dbCombineData;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	public DbCombineData getProgNameAndMainframeDomain(String progName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> getProgramByprogName(String progName) {

		return xrefProgramPlanRepository.getProgramByprogName(progName);
	}

	private DataCombineConfig getAllHeaderDetails() {
		DataCombineConfig combineConfig = new DataCombineConfig();
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("Application Name");
		headerType.setKey("appId");

		HeaderType headerType1 = new HeaderType();
		headerType1.setHeader("Operation Id");
		headerType1.setKey("cpuId");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("Job Name");
		headerType2.setKey("jobName");

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("Procedure Name");
		headerType3.setKey("procName");

		HeaderType headerType4 = new HeaderType();
		headerType4.setHeader("No of Refs");
		headerType4.setKey("procRefCt");

		HeaderType headerType5 = new HeaderType();
		headerType5.setHeader("Program Name");
		headerType5.setKey("progName");

		HeaderType headerType6 = new HeaderType();
		headerType6.setHeader("No of Refs");
		headerType6.setKey("progRefCt");

		HeaderType headerType7 = new HeaderType();
		headerType7.setHeader("Source");
		headerType7.setKey("source");

		headerTypeList.add(headerType);
		headerTypeList.add(headerType1);
		headerTypeList.add(headerType2);
		headerTypeList.add(headerType3);
		headerTypeList.add(headerType4);
		headerTypeList.add(headerType5);
		headerTypeList.add(headerType6);
		headerTypeList.add(headerType7);

		List<HeaderType> headerTypeListForTableview = new ArrayList<>();
		HeaderType headerType8 = new HeaderType();
		headerType8.setHeader("SSID");
		headerType8.setKey("ssid");

		HeaderType headerType9 = new HeaderType();
		headerType9.setHeader("Rgn+ac/plan+verbs");
		headerType9.setKey("db2plan");

		HeaderType headerType10 = new HeaderType();
		headerType10.setHeader("MANTIS Program/DBRM version");
		headerType10.setKey("dbrm");

		HeaderType headerType11 = new HeaderType();
		headerType11.setHeader("stmt");
		headerType11.setKey("stmt");

		HeaderType headerType12 = new HeaderType();
		headerType12.setHeader("verb");
		headerType12.setKey("verb");

		HeaderType headerType13 = new HeaderType();
		headerType13.setHeader("Owner");
		headerType13.setKey("owner");

		HeaderType headerType14 = new HeaderType();
		headerType14.setHeader("Table/View");
		headerType14.setKey("tableView");

		HeaderType headerType15 = new HeaderType();
		headerType15.setHeader("Meaning");
		headerType15.setKey("meaning");

		headerTypeListForTableview.add(headerType8);
		headerTypeListForTableview.add(headerType9);
		headerTypeListForTableview.add(headerType10);
		headerTypeListForTableview.add(headerType11);
		headerTypeListForTableview.add(headerType12);
		headerTypeListForTableview.add(headerType13);
		headerTypeListForTableview.add(headerType14);
		headerTypeListForTableview.add(headerType15);

		combineConfig.setProgramHeader(headerTypeList);
		combineConfig.setTableviewHeader(headerTypeListForTableview);

		return combineConfig;
	}

}
