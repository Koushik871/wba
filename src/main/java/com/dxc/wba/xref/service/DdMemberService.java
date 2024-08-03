package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.ddentity.DDLGenerationDB2D;
import com.dxc.wba.xref.ddentity.DDMemberdefinition;
import com.dxc.wba.xref.ddentity.MemberDefinition;
import com.dxc.wba.xref.ddentity.MemberRelation;
import com.dxc.wba.xref.repository.DDLgenerationDB2DRepository;
import com.dxc.wba.xref.repository.DDMemberLookupRepo;
import com.dxc.wba.xref.repository.DDProgramRepository;
import com.dxc.wba.xref.repository.DdMemberRepository;
import com.dxc.wba.xref.repository.DfMemberRepository;
import com.dxc.wba.xref.repository.MemberdefinitionTempRepository;
import com.dxc.wba.xref.repository.NewMemberRepository;
import com.dxc.wba.xref.type.DDLdb2dJson;
import com.dxc.wba.xref.type.DDWduJson;
import com.dxc.wba.xref.type.DataDTO;
import com.dxc.wba.xref.type.DdMemJson;
import com.dxc.wba.xref.type.Header;
import com.dxc.wba.xref.type.MemRangeJson;
import com.dxc.wba.xref.type.MemberSequenceDTO;
import com.dxc.wba.xref.type.Mjson;

@Service
public class DdMemberService {

	@Autowired
	private DdMemberRepository ddMemberRepo;

	@Autowired
	private NewMemberRepository ddnewrepo;

	@Autowired
	private DDMemberLookupRepo ddMemberlookupRepo;

	@Autowired
	private DfMemberRepository dfMemberRepo;

	@Autowired
	MemberdefinitionTempRepository temprepo;

	@Autowired
	DDLgenerationDB2DRepository ddldb2dRepo;

	@Autowired
	private DDProgramRepository ddrepo;

	public DDLdb2dJson getDDLDB2DByTableNameandSchemaName(String ddl_gen_table_name, String ddl_gen_schema_name) {

		DDLdb2dJson ddl = new DDLdb2dJson();
		try {

			List<DDLGenerationDB2D> response = new ArrayList<>();
			response = ddldb2dRepo.getDDLDB2DByTableNameandSchemaName(ddl_gen_table_name, ddl_gen_schema_name);

			headersforddlgen();
			ddl.setConfig(headersforddlgen());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	/* FOR DB2D Table */

	public DDLdb2dJson getDDLDB2DByTableName(String ddl_gen_table_name) {

		DDLdb2dJson ddl = new DDLdb2dJson();
		try {
			List<DDLGenerationDB2D> response = new ArrayList<>();
			response = ddldb2dRepo.getDDLDB2DByTableName(ddl_gen_table_name);

			headersforddl();
			ddl.setConfig(headersforddl());
			ddl.setData(response);
			return ddl;

		} catch (Exception e) {
			return null;
		}

	}

	public void deleteByMemberName(String memberName) {
		List<Map<String, Object>> definitions = ddMemberlookupRepo.findByMember(memberName);
		List<Map<String, Object>> temp = temprepo.findByMember(memberName);
		List<DDMemberdefinition> list = ddrepo.findMemberByMemberName(memberName);

		if (!definitions.isEmpty()) {
			ddMemberRepo.deleteByMemberName(memberName);
			ddrepo.deleteByMemberName(memberName);
			ddMemberlookupRepo.deleteByMemberName(memberName);
			dfMemberRepo.deleteByMemberName(memberName);

		} else {
			dfMemberRepo.deleteByMemberName(memberName);
			ddnewrepo.deleteByMemberName(memberName);
			temprepo.deleteByMemberName(memberName);
		}
	}

	public DDWduJson getWduMember(String related_member) {
		DDWduJson ddWduJson = new DDWduJson();
		related_member = related_member.replace('*', ' ').trim();
		List<Map<String, Object>> wdu = dfMemberRepo.findByRelName(related_member);

//		List<Map<String, Object>> wdus = ddMemberRepo.findByMemName(related_member);
//		List<Map<String, Object>> wd = ddMemberlookupRepo.findByMemName(related_member);
		List<Map<String, Object>> list = new ArrayList<>();
//		List<MemberRelation> wdulist = dfMemberRepo.findAll();
		List<MemberRelation> wdulist1 = dfMemberRepo.findByRelName1(related_member);

		for (MemberRelation member : wdulist1) {
			String memberName = member.getRelatedMember();

			String[] words = memberName.split("\\s+");

			for (String word : words) {

				if (word.equals(related_member)) {
					Map<String, Object> memberMap = new HashMap<>();
					memberMap.put("membername", member.getMemberName());
					memberMap.put("type", member.getMemberType());

					list.add(memberMap);

				}
			}
		}
		list.sort(Comparator.comparing(m -> (String) m.get("membername")));
		getWduHeaders();

		ddWduJson.setConfig(getWduHeaders());
		ddWduJson.setData(list);
		return ddWduJson;
	}

	public DdMemJson getDDMemberPandD(String value) {
		value = value.replace('*', ' ').trim();

		DdMemJson ddMemJson = new DdMemJson();

		try {
			List<Map<String, Object>> definitions = ddMemberlookupRepo.findByMemberPgandDB(value);

			List<Map<String, Object>> list = new ArrayList<>();
			list.addAll(definitions);
			list.sort(Comparator.comparing(m -> (Double) m.get("row_no")));
			ddMemJson.setConfig(lsHeaders());
			ddMemJson.setData(list);

			return ddMemJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddMemJson;
	}

	public DdMemJson getRangePandD(String member, String member1) {
		DdMemJson range = new DdMemJson();
		try {

			List<Map<String, Object>> list = new ArrayList<>();
			member = member.replace("*", "").trim();
			member1 = member1.replace("*", "").trim();
//
			Integer dfrow_no1 = ddMemberlookupRepo.findBydfRowNo(member);
			Integer dfrow_no2 = ddMemberlookupRepo.findBydfRowNo2(member1);

			if (dfrow_no1 == null && member.length() > 0) {
				while (dfrow_no1 == null && member.length() > 0) {
					char lastChar = member.charAt(member.length() - 1);
					char nextChar = (char) (lastChar + 1);

					if (nextChar == ':') {
						break;
					}

					String similarMember = member.substring(0, member.length() - 1) + nextChar;
					dfrow_no1 = ddMemberlookupRepo.findBydfRowNo(similarMember);

//			        // Removes the last character from member1 for the next iteration
					member = member.substring(0, member.length() - 1) + nextChar;
					System.out.println("ms1:: " + member);
					if (dfrow_no1 != null) {
						// Exits the loop if a non-null result is found
						break;
					}

				}
			}

			if (dfrow_no2 == null && member1.length() > 0) {
				// Continue searching for similar names by decrementing the last character
				while (dfrow_no2 == null && member1.length() > 0) {
					char lastChar = member1.charAt(member1.length() - 1);
					char prevChar = (char) (lastChar - 1);
					String similarMember = member1.substring(0, member1.length() - 1) + prevChar;
					dfrow_no2 = ddMemberlookupRepo.findBydfRowNo2(similarMember);

//		            // Remove the last character from member1 for the next iteration
					member1 = member1.substring(0, member1.length() - 1) + prevChar;
					System.out.println("ms2:: " + member1);
					if (dfrow_no2 != null) {
						// Exits the loop if a non-null result is found
						break;
					}
				}
			}

			if (member.equals(member1)) {

			}
			if (dfrow_no1 != null && dfrow_no2 != null) {
				List<Map<String, Object>> D = ddMemberlookupRepo.findRangePandD(dfrow_no1, dfrow_no2);
				list.addAll(D);
			}

			list.sort((map1, map2) -> {
				Double row1 = (Double) map1.get("row_no");
				Double row2 = (Double) map2.get("row_no");
				return row1.compareTo(row2);
			});
			range.setConfig(getHeader());
			range.setData(list);
			return range;

		} catch (Exception e) {
			return null;
		}
	}

//	public void deleteByMemberName(String memberName) {
//		List<Map<String, Object>> definitions = ddMemberlookupRepo.findByMemberPgandDB(memberName);
//		List<Map<String, Object>> temp = temprepo.findByMemberName1(memberName);
//		ddMemberRepo.deleteByMemberName(memberName);
//		ddMemberlookupRepo.deleteByMemberName(memberName);
//		temprepo.deleteByMemberName(memberName);
//
//	}

	public DdMemJson getDDdMemberResponse(String value) {
		DdMemJson ddMemJson = new DdMemJson();
		try {
			value = value.replace('*', ' ').trim();

			List<Map<String, Object>> definitions = ddMemberRepo.findByMemberName1(value);

//		List<Map<String, Object>> relation = dfMemberRepo.findByRelName(value);

			List<Map<String, Object>> temp = temprepo.findByMemberName1(value);

			List<Map<String, Object>> list = new ArrayList<>();

//		for (Map<String, Object> map : definitions) {
//
//			Map<String, Object> newMap = new HashMap<>();
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//
//				newMap.put(entry.getKey(), entry.getValue());
//
//			}
//			newMap.put("CONDITION", "SRC");
//			list.add(newMap);
//
//		}
//		
//		for (Map<String, Object> map : relation) {
//
//			Map<String, Object> rel = new HashMap<>();
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//
//				rel.put(entry.getKey(), entry.getValue());
//
//			}
//			rel.put("CONDITION", "");
//			list.add(rel);
//
//		}
			list.addAll(definitions);
//		list.addAll(relation);
			list.addAll(temp);

			List<Map<String, Object>> resultList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> currentMap = list.get(i);
				boolean isDuplicate = false;
				for (int j = i + 1; j < list.size(); j++) {
					Map<String, Object> otherMap = list.get(j);
					if (currentMap.get("membername").equals(otherMap.get("membername"))
							&& currentMap.get("type").equals(otherMap.get("type"))) {
						isDuplicate = true;
						break;
					}
				}
				if (!isDuplicate) {
					resultList.add(currentMap);
				}
			}

			Collections.sort(resultList, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> map1, Map<String, Object> map2) {
					String stmt1 = (String) map1.get("membername");
					String stmt2 = (String) map2.get("membername");
					return stmt1.compareTo(stmt2);
				}
			});

			lsHeaders();

			ddMemJson.setConfig(lsHeaders());
			ddMemJson.setData(resultList);

			return ddMemJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ddMemJson;

	}

//	public DdMemJson getRangePandD(String member, String member1) {
//
//		DdMemJson range = new DdMemJson();
//		try {
//			List<Map<String, Object>> list = new ArrayList<>();
//			member = member.replace('*', ' ').trim();
//			member1 = member1.replace('*', ' ').trim();
//
//			Integer row_no1 = ddMemberlookupRepo.findByRowNo(member);
//			Integer row_no2 = ddMemberlookupRepo.findByRowNo2(member1);
////			Long maxRowNo = ddMemberlookupRepo.findMaxRowNoForMember2();
//
//
//			if (row_no1 != null && row_no2 != null) {
//				List<Map<String, Object>> D = ddMemberlookupRepo.findRangePandD(row_no1, row_no2);
//
//				list.addAll(D);
//
//			}
////			if (D.isEmpty()) {
//////				list.addAll(D1);
////			} else {
////				list.addAll(D);
////			}
//
////			List<Map<String, Object>> D1 = ddMemberlookupRepo.findByMemberLookupRange(member, member1);
//
////			list.addAll(D);
//
//			Map<String, Map<String, Object>> map = new TreeMap<>();
//			for (Map<String, Object> m : list) {
//				map.put((String) m.get("membername"), m);
//			}
//			List<Map<String, Object>> sortedList = new ArrayList<>(map.values());
//
//			range.setConfig(getHeader());
//			range.setData(sortedList);
//			return range;
//
//		} catch (Exception e) {
//			
//			return null;
//		}
//
//	}

	public MemRangeJson getDDdMemberRangefromrow(String member, String member1) {

		MemRangeJson memRangeJson = new MemRangeJson();
//		member = member.replace('*', ' ').trim();
//		member1 = member1.replace('*', ' ').trim();
		try {

			List<Object> list = new ArrayList<>();

			Integer row_no1 = ddMemberRepo.findByRowNo(member);
			Integer row_no2 = ddMemberRepo.findByRowNo2(member1);

			/*
			 * Integer dfrow_no1 = dfMemberRepo.findByRowNo(member); Integer dfrow_no2 =
			 * dfMemberRepo.findByRowNo2(member1);
			 */
			String sys = new String();
			String sys1 = new String();
			sys = ddMemberRepo.findbymembertype(member);
			sys1 = ddMemberRepo.findbymembertype(member1);

			if (row_no1 != null && row_no2 != null) {

				List<Map<String, Object>> D = ddMemberRepo.findByBetweenRowNo(row_no1, row_no2);
//				List<Map<String, Object>> C = dfMemberRepo.findByBetweenRowNo(dfrow_no1, dfrow_no2);
				list.addAll(D);
//				list.addAll(C);
//		if (row_no1 > row_no2) {
//			List<Map<String, Object>> D = ddMemberRepo.findByBetweenRow(row_no2, row_no1);
//			list.addAll(D);
//		} else {
//			List<Map<String, Object>> D = ddMemberRepo.findByBetweenRowNo(row_no1, row_no2);
//			list.addAll(D);
//		}

			}
//	if (dfrow_no1 != null && dfrow_no2 != null) {
//
//		List<Map<String, Object>> DF = dfMemberRepo.findByBetweenRowNo(dfrow_no1, dfrow_no2);
////		list.addAll(DF);
//	}

			memRangeJson.setConfig(getHeader());

			memRangeJson.setData(list);

			return memRangeJson;

		} catch (Exception e) {
			return null;
		}

	}

	private List<Header> getHeader() {
		List<Header> headerList = new ArrayList<>();
		Header headerType = new Header();
		headerType.setHeader("MEMBER NAME");
		headerType.setKey("membername");

		Header headerType1 = new Header();
		headerType1.setHeader("TYPE");
		headerType1.setKey("type");

		headerList.add(headerType);
		headerList.add(headerType1);
		return headerList;
	}

	private List<Header> getdefHeader() {

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("CATALAGUED AS");
		headerType.setKey("catalogue");

		Header headerType1 = new Header();
		headerType1.setHeader("COMMENT");
		headerType1.setKey("comment");

		Header headerType2 = new Header();
		headerType2.setHeader("DESCRIPTION");
		headerType2.setKey("description");

		Header headerType3 = new Header();
		headerType3.setHeader("NOTE");
		headerType3.setKey("note");

		Header headerType4 = new Header();
		headerType4.setHeader("LAUNGUAGE");
		headerType4.setKey("language");

		Header headerType5 = new Header();
		headerType5.setHeader("FOC");
		headerType5.setKey("foc");

		Header headerType6 = new Header();
		headerType6.setHeader("FOC_DATE");
		headerType6.setKey("foc_DATE");

		Header headerType7 = new Header();
		headerType7.setHeader("FOCUS");
		headerType7.setKey("focus");

		Header headerType8 = new Header();
		headerType8.setHeader("ALIAS_MANTIS");
		headerType8.setKey("alias_MANTIS");

		Header headerType9 = new Header();
		headerType9.setHeader("ALIAS_BFH");
		headerType9.setKey("alias_BFH");

		Header headerType10 = new Header();
		headerType10.setHeader("ALIAS_INTERPERS");
		headerType10.setKey("alias_INTERPERS");

		Header headerType11 = new Header();
		headerType11.setHeader("ALIAS_IMS");
		headerType11.setKey("alias_IMS");

		Header headerType12 = new Header();
		headerType12.setHeader("ALIAS_DB2");
		headerType12.setKey("alias_DB2");

		Header headerType13 = new Header();
		headerType13.setHeader("ALIAS_COBOL");
		headerType13.setKey("alias_COBOL");

		Header headerType14 = new Header();
		headerType14.setHeader("ALIAS");
		headerType14.setKey("alias");

		Header headerType15 = new Header();
		headerType15.setHeader("ADMINISTRATIVE_DATA");
		headerType15.setKey("administrative_DATA");

		Header headerType16 = new Header();
		headerType16.setHeader("ALIAS_EXCELERATOR");
		headerType16.setKey("alias_EXCELERATOR");

		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType2);
		headerList.add(headerType3);
		headerList.add(headerType4);
		headerList.add(headerType5);
		headerList.add(headerType6);
		headerList.add(headerType7);
		headerList.add(headerType8);
		headerList.add(headerType9);
		headerList.add(headerType10);
		headerList.add(headerType11);
		headerList.add(headerType12);
		headerList.add(headerType13);
		headerList.add(headerType14);
		headerList.add(headerType15);
		headerList.add(headerType16);

		return headerList;

	}

	private List<Header> lsHeaders() {
		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("MEMBER NAME");
		headerType.setKey("membername");

		Header headerType1 = new Header();
		headerType1.setHeader("TYPE");
		headerType1.setKey("type");

		headerList.add(headerType);
		headerList.add(headerType1);

		return headerList;

	}

	private List<Header> getWduHeaders() {
		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("Member Name");
		headerType.setKey("membername");

		Header headerType1 = new Header();
		headerType1.setHeader("Member Type");
		headerType1.setKey("type");

		headerList.add(headerType1);
		headerList.add(headerType);

		return headerList;

	}

	private List<Header> getrelheader() {

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("INPUTS");
		headerType.setKey("inputs");

		Header headerType1 = new Header();
		headerType1.setHeader("OUTPUTS");
		headerType1.setKey("outputs");

		Header headerType2 = new Header();
		headerType2.setHeader("UPDATES");
		headerType2.setKey("updates");

		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType2);

		return headerList;

	}

	Comparator<Header> headerTypeComparator = Comparator.comparing(header -> {
		String type = header.getKey().toLowerCase();
		if (type.startsWith("freq")) {
			return 1;
		} else {
			switch (type) {
			case "as":
			case "frequency":
			case "inputs":
			case "outputs":
			case "updates":
			case "calls":
			case "processes":
			case "creator-owner":
				return 2;
			case "data-domain":
			case "columns":
			case "held-as":
			case "contains":
				return 3;
			case "select":
			case "from":
			case "where":
			case "unique":
			case "on":
			case "with-check-option":
				return 4;
			case "contents":
			case "usage":
			case "attributes":
			case "stogroup":
			case "cluster":
			case "subpages":
			case "bufferpool":
			case "locksize":
			case "close":
			case "priqty":
			case "secqty":
			case "erase":
			case "freepage":
			case "pctfree":
			case "partition":
			case "cardinality":
				return 5;
			case "sequential":
			case "standard-labels":
			case "fixed":
			case "variable":
			case "access":
			case "index":
			case "dataset index":
			case "dataset prime":
			case "sequence-key":
			case "insert-position":
			case "pointers":
				return 6;
			case "description":
			case "note":
			case "foc-description":
			case "foc-segtype":
			case "foc-suffix":
			case "foc-title":
			case "foc-usage":
				return 7;
			case "administrative-data":
			case "query":
			case "comment":
			case "alias":
			case "catalogue":
			case "catalog":
				return 8;
			default:
				return 9;
			}
		}
	});

	public String updateDefinitionAndRelations(DataDTO data) {
		Mjson mjosn = new Mjson();
		String success = "failed";
		try {

			List<MemberSequenceDTO> sequenceList = data.getDefinition().getSequeneNo();

			sequenceList.parallelStream().forEach(def -> {
				ddMemberRepo.updatePriceByName(def.getDescription(), Integer.parseInt(def.getSequeneNo()));
			});

			success = "succcess";

		} catch (Exception e) {

			e.printStackTrace();
		}
		return success;
	}

	private List<Header> headersforddlgen() {

		List<Header> headerList = new ArrayList<>();

		Header headerType6 = new Header();
		headerType6.setHeader("Table Name");
		headerType6.setKey("ddlGenTableName");

		Header headerType7 = new Header();
		headerType7.setHeader("Schema Name");
		headerType7.setKey("ddlGenSchemaName");

		Header headerType = new Header();
		headerType.setHeader("Column Name");
		headerType.setKey("ddlColumnName");

		Header headerType1 = new Header();
		headerType1.setHeader("Column Type");
		headerType1.setKey("ddlColType");

		Header headerType8 = new Header();
		headerType8.setHeader("Column Length ");
		headerType8.setKey("ddlColLength");

		Header headerType2 = new Header();
		headerType2.setHeader("Primary Key");
		headerType2.setKey("ddlPrimarykeyseq");

		;
		Header headerType3 = new Header();
		headerType3.setHeader("Foreign Key");
		headerType3.setKey("ddlForeignkey");

		Header headerType4 = new Header();
		headerType4.setHeader("Table Space");
		headerType4.setKey("ddlTablespace");

		headerList.add(headerType6);
		headerList.add(headerType7);
		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType8);
		headerList.add(headerType2);
		headerList.add(headerType3);
		headerList.add(headerType4);

		return headerList;

	}

	private List<Header> headersforddl() {

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("Schema Name");
		headerType.setKey("ddlGenSchemaName");

		Header headerType1 = new Header();
		headerType1.setHeader("Table Name");
		headerType1.setKey("ddlGenTableName");

		headerList.add(headerType);
		headerList.add(headerType1);

		return headerList;
	}

	public List<MemberDefinition> findByMemberName(String memberName) {
		return ddMemberRepo.findByMemberName(memberName);
	}

}