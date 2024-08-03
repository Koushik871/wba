package com.dxc.wba.xref.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.TableViewEntity;
import com.dxc.wba.xref.dto.OptionalPlanJson;
import com.dxc.wba.xref.service.TableViewService;
import com.dxc.wba.xref.service.TableViewServiceImpl;
import com.dxc.wba.xref.type.Header;
import com.dxc.wba.xref.type.PlanJson;
import com.dxc.wba.xref.type.TableViewjson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TableViewController {

	@Autowired
	private TableViewService tableViewService;

	@Autowired
	private TableViewServiceImpl tableViewServiceImpl;

//	@Autowired
//	private TableViewRepository trepo;

	@GetMapping("/getAllTableView/{mainframeDomain}/{tableView}")
	public TableViewjson getAllTableView(
			@PathVariable(required = false, name = "mainframeDomain") String mainframeDomain,
			@PathVariable(required = false, name = "tableView") String tableView) {
		tableView = tableView.replace('*', ' ').trim();
		try {
			List<Map<String, Object>> response1 = new ArrayList<>();
			List<Map<String, Object>> response2 = new ArrayList<>();

			if ((mainframeDomain.equalsIgnoreCase("PROD")) || (mainframeDomain.equalsIgnoreCase("Prod"))) {
				response1 = tableViewServiceImpl.getalldata(tableView, "Prod");
				response2 = tableViewServiceImpl.getalldb2plandata(tableView, "Prod");
			} else if ((mainframeDomain.equalsIgnoreCase("DALLAS")) || (mainframeDomain.equalsIgnoreCase("Dallas"))) {
				response1 = tableViewServiceImpl.getalldata(tableView, "Dallas");
				response2 = tableViewServiceImpl.getalldb2plandata(tableView, "Dallas");
			}

			response2 = response2.stream().map(map -> {
				Map<String, Object> newMap = new HashMap<>(map);
				if (newMap.get("table_view") != null && newMap.get("table_view").toString().length() > 2) {
					String thirdElement = newMap.get("table_view").toString().substring(2, 3);
					if (thirdElement.equals("V")) {
						newMap.put("Meaning", "View as coded in SQL");
					} else if (thirdElement.equals("T")) {
						newMap.put("Meaning", "TABLE DERIVED FROM SQL");
					} else {
						newMap.put("Meaning", "TABLE DERIVED FROM SQL");
					}
				}
				return newMap;
			}).sorted(Comparator.comparing(map -> (String) map.get("dbrm"))).collect(Collectors.toList());
			response1 = response1.stream().map(map -> {
				Map<String, Object> newMap = new HashMap<>(map);
				if (newMap.get("table_view") != null && newMap.get("table_view").toString().length() > 2) {
					String thirdElement = newMap.get("table_view").toString().substring(2, 3);
					if (thirdElement.equals("V")) {
						newMap.put("Meaning", "View as coded in SQL");
					} else if (thirdElement.equals("T")) {
						newMap.put("Meaning", "TABLE DERIVED FROM SQL");
					} else {
						newMap.put("Meaning", "View as coded in SQL");
					}
				}
				return newMap;
			}).sorted(Comparator.comparing(map -> (String) map.get("dbrm"))).collect(Collectors.toList());

			List<Map<String, Object>> res1 = new ArrayList<>();
			res1.addAll(response2);
			res1.addAll(response1);
//					.sorted(Comparator.comparingInt(map -> (int) map.get("stmt"))).collect(Collectors.toList());

			TableViewjson tableViewjson = new TableViewjson();
			tableViewjson.setConfig(tableheaders());
			tableViewjson.setData(res1);
			return tableViewjson;
		} catch (Exception e) {
			System.out.println(e);
			return new TableViewjson(); // or new TableViewjson("Error message");
		}
	}

	@PostMapping("getbyPlanandView/DB2planortableView")
	public PlanJson getbyPlanview(@RequestBody OptionalPlanJson optionalPlanJson) {

		PlanJson planJson = new PlanJson();
		List<Object> response = tableViewServiceImpl.fetchDataBasedOnOptional(optionalPlanJson);

		plantableheaders();
		planJson.setConfig(plantableheaders());
		planJson.setData(response);

		return planJson;

	}

	@GetMapping("/getAllTableView/{tableView}")
	public TableViewjson getAllTableView(@PathVariable(required = false, name = "tableView") String tableView) {
		try {
			List<Map<String, Object>> response1 = tableViewServiceImpl.getalldata(tableView);
			List<Map<String, Object>> response2 = tableViewService.getalldb2plandata(tableView);

			List<Map<String, Object>> res = Stream.concat(response2.stream().map(map -> {
				Map<String, Object> newMap = new HashMap<>(map);
				if (newMap.get("table_view") != null && newMap.get("table_view").toString().length() > 2) {
					String thirdElement = newMap.get("table_view").toString().substring(2, 3);
					if (thirdElement.equals("V")) {
						newMap.put("Meaning", "View as coded in SQL");
					} else if (thirdElement.equals("T")) {
						newMap.put("Meaning", "TABLE DERIVED FROM SQL");
					} else {
						newMap.put("Meaning", "TABLE DERIVED FROM SQL");
					}
				}
				return newMap;
			}), response1.stream().map(map -> {
				Map<String, Object> newMap = new HashMap<>(map);
				if (newMap.get("table_view") != null && newMap.get("table_view").toString().length() > 2) {
					String thirdElement = newMap.get("table_view").toString().substring(2, 3);
					if (thirdElement.equals("V")) {
						newMap.put("Meaning", "View as coded in SQL");
					} else if (thirdElement.equals("T")) {
						newMap.put("Meaning", "TABLE DERIVED FROM SQL");
					} else {
						newMap.put("Meaning", "View as coded in SQL");
					}
				}
				return newMap;
			})).collect(Collectors.toList());

			TableViewjson tableViewjson = new TableViewjson();
			tableViewjson.setConfig(tableheaders());
			tableViewjson.setData(res);
			return tableViewjson;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//	@GetMapping("/getAllTableView/{tableView}")
//	public TableViewjson getAllTableView(@PathVariable(required = false, name = "tableView") String tableView) {
//		try {
//			List<Map<String, Object>> response1 = tableViewServiceImpl.getalldata(tableView);
//			List<Map<String, Object>> response2 = tableViewService.getalldb2plandata(tableView);
//
//			List<Map<String, Object>> res = Stream.concat(response2.stream().map(map -> {
//				Map<String, Object> newMap = new HashMap<>(map);
//				newMap.put("Meaning", "TABLE DERIVED FROM SQL");
//				return newMap;
//			}), response1.stream().map(map -> {
//				Map<String, Object> newMap = new HashMap<>(map);
//				newMap.put("Meaning", "View as coded in SQL");
//				return newMap;
//			})).collect(Collectors.toList());
//
//			List<Map<String, Object>> resultList = new ArrayList<>();
//			for (int i = 0; i < res.size(); i++) {
//				Map<String, Object> currentMap = res.get(i);
//				boolean isDuplicate = false;
//				for (int j = i + 1; j < res.size(); j++) {
//					Map<String, Object> otherMap = res.get(j);
//					if (currentMap.get("owner").equals(otherMap.get("owner"))
//							&& currentMap.get("db2plan").equals(otherMap.get("db2plan"))
//							&& currentMap.get("table_view").equals(otherMap.get("table_view"))
//							&& currentMap.get("dbrmversions").equals(otherMap.get("dbrmversions"))
//							&& currentMap.get("timestamps").equals(otherMap.get("timestamps"))
//							&& currentMap.get("verb").equals(otherMap.get("verb"))
//							&& currentMap.get("db2planver").equals(otherMap.get("db2planver"))
//							&& currentMap.get("ssid").equals(otherMap.get("ssid"))
//							&& currentMap.get("db2planverbs").equals(otherMap.get("db2planverbs"))
//							&& currentMap.get("dbrm").equals(otherMap.get("dbrm"))
//							&& currentMap.get("mainframe_domain").equals(otherMap.get("mainframe_domain"))
//							&& currentMap.get("stmt").equals(otherMap.get("stmt"))
//							&& currentMap.get("dbrmversion").equals(otherMap.get("dbrmversion"))
//							&& currentMap.get("Meaning").equals(otherMap.get("Meaning"))) {
//						isDuplicate = true;
//						break;
//					}
//				}
//				if (!isDuplicate) {
//					resultList.add(currentMap);
//				}
//			}
////					
////					.sorted(Comparator.comparing((Map<String, Object> map) -> (String) map.get("db2plan"))
////					.thenComparing((map1, map2) -> {
////						Object stmtObj1 = map1.get("stmt");
////						Object stmtObj2 = map2.get("stmt");
////						int stmt1 = stmtObj1 instanceof Integer ? (int) stmtObj1 : Integer.parseInt((String) stmtObj1);
////						int stmt2 = stmtObj2 instanceof Integer ? (int) stmtObj2 : Integer.parseInt((String) stmtObj2);
////						return Integer.compare(stmt1, stmt2);
////					})).collect(Collectors.toList());
//
//			TableViewjson tableViewjson = new TableViewjson();
//			tableViewjson.setConfig(tableheaders());
//			tableViewjson.setData(res);
//			return tableViewjson;
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}
//	}

	@GetMapping("/getbytableview/{DB2Plan}")
	public List<TableViewEntity> getbytableview(@PathVariable(required = false, name = "DB2Plan") String DB2Plan) {
		try {
			List<TableViewEntity> response = tableViewServiceImpl.findbyplan(DB2Plan);

			return response;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	private List<Header> tableheaders() {

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("SSID");
		headerType.setKey("ssid");

		Header headerType1 = new Header();
		headerType1.setHeader("Rgn+ac/Plan+vers");
		headerType1.setKey("db2planverbs");

		Header headerType2 = new Header();
		headerType2.setHeader("MANTIS Program/DBRM+version");
		headerType2.setKey("dbrmversions");

		Header headerType3 = new Header();
		headerType3.setHeader("stmt");
		headerType3.setKey("stmt");

		Header headerType4 = new Header();
		headerType4.setHeader("verb");
		headerType4.setKey("verb");

		Header headerType5 = new Header();
		headerType5.setHeader("Owner");
		headerType5.setKey("owner");

		Header headerType6 = new Header();
		headerType6.setHeader("Table/View");
		headerType6.setKey("table_view");

		Header headerType7 = new Header();
		headerType7.setHeader("Meaning");
		headerType7.setKey("Meaning");

		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType2);
		headerList.add(headerType3);
		headerList.add(headerType4);
		headerList.add(headerType5);
		headerList.add(headerType6);
		headerList.add(headerType7);
		return headerList;
	}

//	@GetMapping("/getAllTableView/{tableView}")
//	public TableViewjson getAllTableView(@PathVariable(required = false, name = "tableView") String tableView) {
//		TableViewjson tableViewjson = new TableViewjson();
//		try {
//			TableViewEntity tb = new TableViewEntity();
//
//			List<Map<String, Object>> res = new ArrayList<>();
//
//			List<Map<String, Object>> response = tableViewServiceImpl.getalldata(tableView);
//
//			List<Map<String, Object>> response2 = tableViewService.getalldb2plandata(tableView);
//
//			List<Map<String, Object>> list = new ArrayList<>();
//
//			for (Map<String, Object> map : response2) {
//
//				Map<String, Object> newMap = new HashMap<>();
//				for (Map.Entry<String, Object> entry : map.entrySet()) {
//
//					newMap.put(entry.getKey(), entry.getValue());
//
//				}
//				newMap.put("Meaning", "TABLE DERIVED FROM SQL");
//				list.add(newMap);
//
//			}
//			List<Map<String, Object>> list1 = new ArrayList<>();
//
//			for (Map<String, Object> map : response) {
//
//				Map<String, Object> newMap = new HashMap<>();
//				for (Map.Entry<String, Object> entry : map.entrySet()) {
//
//					newMap.put(entry.getKey(), entry.getValue());
//
//				}
//				newMap.put("Meaning", "View as coded in SQL");
//				list1.add(newMap);
//
//			}
//			tableheaders();
//			tableViewjson.setConfig(tableheaders());
//			res.addAll(list);
//			res.addAll(list1);
//
//			Collections.sort(res, new Comparator<Map<String, Object>>() {
//				public int compare(Map<String, Object> map1, Map<String, Object> map2) {
//					int stmt1 = (int) map1.get("stmt");
//					int stmt2 = (int) map2.get("stmt");
//					return stmt1 - stmt2;
//				}
//			});
//			tableViewjson.setData(res);
//			return tableViewjson;
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}
//
//	}

	private List<Header> plantableheaders() {
		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("SSID");
		headerType.setKey("ssid");

		Header headerType1 = new Header();
		headerType1.setHeader("Plan");
		headerType1.setKey("db2plan");

		Header headerType2 = new Header();
		headerType2.setHeader("DBRM");
		headerType2.setKey("dbrm");

		Header headerType3 = new Header();
		headerType3.setHeader("Stmt");
		headerType3.setKey("stmt");

		Header headerType4 = new Header();
		headerType4.setHeader("Verb");
		headerType4.setKey("verb");

		Header headerType5 = new Header();
		headerType5.setHeader("Owner");
		headerType5.setKey("owner");

		Header headerType6 = new Header();
		headerType6.setHeader("Tableview");
		headerType6.setKey("tableView");

		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType2);
		headerList.add(headerType3);
		headerList.add(headerType4);
		headerList.add(headerType5);
		headerList.add(headerType6);
		return headerList;

	}

}
