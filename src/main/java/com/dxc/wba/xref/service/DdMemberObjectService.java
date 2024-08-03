package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.DbRelationCombo;
import com.dxc.wba.xref.dbmodel.DefinitionAndRelation;
import com.dxc.wba.xref.ddentity.MemberDefinitionObject;
import com.dxc.wba.xref.ddentity.MemberDefinitonTemp;
import com.dxc.wba.xref.ddentity.MemberRelationObject;
import com.dxc.wba.xref.dto.MemBrowseDTO;
import com.dxc.wba.xref.repository.DdMemberRelationRepository;
import com.dxc.wba.xref.repository.DdmemberObjectRepository;
import com.dxc.wba.xref.repository.MemberdefinitionTempRepository;
import com.dxc.wba.xref.type.DDWduJson;
import com.dxc.wba.xref.type.DdMemJson;
import com.dxc.wba.xref.type.DefRelData;
import com.dxc.wba.xref.type.DefRelDataUpdate;
import com.dxc.wba.xref.type.DefRelHeader;
import com.dxc.wba.xref.type.Header;
import com.dxc.wba.xref.type.MemRangeJson;
import com.dxc.wba.xref.type.MemRelheading;
import com.dxc.wba.xref.type.MemberHeadingType;
import com.dxc.wba.xref.type.Mjson;
import com.dxc.wba.xref.type.RelationHeadings;

@Service
public class DdMemberObjectService {

	@Autowired
	private DdmemberObjectRepository ddmemberobjectrepository;

	@Autowired
	private DdMemberRelationRepository ddmemberrelationrepository;

	@Autowired
	MemberdefinitionTempRepository temprepo;

	public DdMemJson getDDdMemberResponse(String value) {

		DdMemJson ddMemJson = new DdMemJson();
		value = value.replace('*', ' ').trim();
		List<Map<String, Object>> definitions = ddmemberobjectrepository.findByMemberName1(value);

//		List<Map<String, Object>> relation = ddmemberrelationrepository.findByRelName(value);

		List<Map<String, Object>> temp = temprepo.findByMemberName1(value);

		List<Map<String, Object>> list = new ArrayList<>();

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

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("MEMBER NAME");
		headerType.setKey("membername");

		Header headerType1 = new Header();
		headerType1.setHeader("TYPE");
		headerType1.setKey("type");

		headerList.add(headerType);
		headerList.add(headerType1);

		ddMemJson.setConfig(headerList);
		ddMemJson.setData(resultList);

		return ddMemJson;

	}



	private List<Header> getHeader() {

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

		Header headerType4 = new Header();
		headerType4.setHeader("LANGUAGE");
		headerType4.setKey("language");

		Header headerType3 = new Header();
		headerType3.setHeader("NOTE");
		headerType3.setKey("note");

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

	public DDWduJson getWduMember(String related_member) {
		DDWduJson ddWduJson = new DDWduJson();
		List<Map<String, Object>> wdu = ddmemberrelationrepository.findByRelName(related_member);
		List<Map<String, Object>> wdus = ddmemberobjectrepository.findByMemName(related_member);
		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("Item");
		headerType.setKey("membername");

		Header headerType1 = new Header();
		headerType1.setHeader("Used By");
		headerType1.setKey("type");

		headerList.add(headerType);
		headerList.add(headerType1);

		ddWduJson.setConfig(headerList);
		ddWduJson.setData(wdu);
		ddWduJson.setData(wdus);
		return ddWduJson;

	}

	public DdMemJson getDDdMemberRange(String member, String member1) {

		DdMemJson ddMemJson = new DdMemJson();
		member = member.replace('*', ' ').trim();
		member1 = member1.replace('*', ' ').trim();
		List<Map<String, Object>> definitions = ddmemberobjectrepository.findByMemberRange(member, member1);

		List<Map<String, Object>> relation = ddmemberrelationrepository.findByMemberRange(member, member1);

		List<Map<String, Object>> list = new ArrayList<>();
		list.addAll(definitions);
		list.addAll(relation);

		List<Header> headerList = new ArrayList<>();

		Header headerType = new Header();
		headerType.setHeader("MEMBER NAME");
		headerType.setKey("membername");

		Header headerType1 = new Header();
		headerType1.setHeader("TYPE");
		headerType1.setKey("type");

		headerList.add(headerType);
		headerList.add(headerType1);

		ddMemJson.setConfig(headerList);
		ddMemJson.setData(list);

		return ddMemJson;

	}

	public MemRangeJson getDDdMemberRangefromrow(String member, String member1) {

		try {
			MemRangeJson memRangeJson = new MemRangeJson();
			member = member.replace('*', ' ').trim();
			member1 = member1.replace('*', ' ').trim();
			List<Object> list = new ArrayList<>();
			Integer row_no1 = ddmemberobjectrepository.findByRowNo(member);
			Integer row_no2 = ddmemberobjectrepository.findByRowNo2(member1);
			if (row_no1 != null && row_no2 != null) {
				List<Map<String, Object>> D = ddmemberobjectrepository.findByBetweenRowNo(row_no1, row_no2);

				list.addAll(D);
			}
			List<Header> headerList = new ArrayList<>();

			Header headerType = new Header();
			headerType.setHeader("MEMBER NAME");
			headerType.setKey("membername");

			Header headerType1 = new Header();
			headerType1.setHeader("TYPE");
			headerType1.setKey("type");

			headerList.add(headerType);
			headerList.add(headerType1);

			memRangeJson.setConfig(headerList);

			memRangeJson.setData(list);

			return memRangeJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public Mjson updateDefinitionAndRelation(DbRelationCombo member) {
		Mjson mjosn = new Mjson();
		String success = "failed";
		try {

			List<MemBrowseDTO> browseDTOs = new ArrayList<>();
			browseDTOs.addAll(member.getData().getDefinition().getAdministrativeData());
			browseDTOs.addAll(member.getData().getDefinition().getAlias());
			browseDTOs.addAll(member.getData().getDefinition().getComment());
			browseDTOs.addAll(member.getData().getDefinition().getCatalogue());
			browseDTOs.addAll(member.getData().getDefinition().getNote());
			browseDTOs.addAll(member.getData().getDefinition().getDescription());

			browseDTOs.parallelStream().forEach(def -> {
				ddmemberobjectrepository.updatePriceByName(def.getDescription(), Integer.parseInt(def.getSequeneNo()));
			});

			browseDTOs.addAll(member.getData().getDefinition().getAdministrativeData());
			browseDTOs.addAll(member.getData().getDefinition().getAlias());
			browseDTOs.addAll(member.getData().getDefinition().getComment());
			browseDTOs.addAll(member.getData().getDefinition().getCatalogue());
			browseDTOs.addAll(member.getData().getDefinition().getNote());
			browseDTOs.addAll(member.getData().getDefinition().getDescription());

			browseDTOs.parallelStream().forEach(def -> {
				temprepo.updatePriceByName(def.getDescription(), Integer.parseInt(def.getSequeneNo()));
			});

			List<MemBrowseDTO> browseForRelation = new ArrayList<>();
			browseForRelation.addAll(member.getData().getRelation().getInputs());
			browseForRelation.addAll(member.getData().getRelation().getUpdates());
			browseForRelation.addAll(member.getData().getRelation().getOutputs());

			browseForRelation.parallelStream().forEach(rel -> {
				ddmemberrelationrepository.updatePriceByName(rel.getDescription(),
						Integer.parseInt(rel.getSequeneNo()));
			});

			success = "succcess";

		} catch (Exception e) {

			e.printStackTrace();
		}
		return mjosn;
	}

	public Mjson getDatabaseMemberforUpdates(String member) {

		Mjson mjson = new Mjson();
//		UpdateDefRelHeaderJson delheader = new UpdateDefRelHeaderJson();
//		UpdateDefRelDataJson delData = new UpdateDefRelDataJson();

		DefRelHeader delheader = new DefRelHeader();
		DefRelData delData = new DefRelData();

		List<MemberDefinitionObject> definitions = ddmemberobjectrepository.findByMemberName(member);
		List<MemberRelationObject> relations = ddmemberrelationrepository.findByMemberName(member);
		List<MemberDefinitonTemp> temp = temprepo.findByMemberName(member);

		try {
			Map<String, List<MemBrowseDTO>> result = new LinkedHashMap<>();
			Map<String, List<MemBrowseDTO>> result1 = new LinkedHashMap<>();
			List<Map<String, List<MemBrowseDTO>>> resultlist = new ArrayList();
			resultlist.add(result);
			resultlist.add(result1);

			List<Header> defHeaderList = new ArrayList<>();
			List<Header> RelHeaderList = new ArrayList<>();

			if (!definitions.isEmpty() || !relations.isEmpty()) {
				definitions.forEach(d -> {
					List<MemBrowseDTO> list = result.computeIfAbsent(d.getMemberDocType().toLowerCase(),
							k -> new ArrayList<>());
					MemBrowseDTO memBrowseDTO = new MemBrowseDTO();
					memBrowseDTO.setDescription(d.getMemberLineText());
					memBrowseDTO.setSequeneNo(Integer.toString(d.getRow_no()));
					memBrowseDTO.setTableType("definition");
					list.add(memBrowseDTO);
					Collections.sort(list, Comparator.comparingInt(dto -> Integer.parseInt(dto.getSequeneNo())));
				});

				relations.forEach(d -> {
					String description = d.getMemberRelation().toLowerCase();

					// Retrieve the list associated with the description or create a new one if it
					// doesn't exist
					List<MemBrowseDTO> list = result1.computeIfAbsent(description, k -> new ArrayList<>());

					// Check if the description already exists in the list
					boolean isDuplicate = list.stream()
							.anyMatch(dto -> dto.getDescription().equalsIgnoreCase(d.getRelatedMember()));

					// If it's not a duplicate, add the MemBrowseDTO to the list
					if (!isDuplicate) {
						MemBrowseDTO memBrowseDTO = new MemBrowseDTO();
						memBrowseDTO.setDescription(d.getRelatedMember());
						memBrowseDTO.setSequeneNo(Integer.toString(d.getRow_no()));
						memBrowseDTO.setTableType("relation");
						list.add(memBrowseDTO);
					}
				});

//				relations.forEach(d -> {
//					List<MemBrowseDTO> list = result1.computeIfAbsent(d.getMemberRelation().toLowerCase(),
//							k -> new ArrayList<>());
//					MemBrowseDTO memBrowseDTO = new MemBrowseDTO();
//					memBrowseDTO.setDescription(d.getRelatedMember());
//					memBrowseDTO.setSequeneNo(Integer.toString(d.getRow_no()));
//					memBrowseDTO.setTableType("relation");
//					list.add(memBrowseDTO);
//					
//					Collections.sort(list, Comparator.comparingInt(dto -> Integer.parseInt(dto.getSequeneNo())));
//				});

				Set<String> headerKeys = new HashSet<>();

				Comparator<MemberDefinitionObject> memberTypeComparator = Comparator.comparing(ddMember -> {
					String type = ddMember.getMemberDocType().toLowerCase();
					if (type.startsWith("freq")) {
						return 1;
					} else {
						switch (type) {
						case "inputs":
							return 2;
						case "outputs":
							return 3;
						case "calls":
							return 4;
						case "description":
							return 5;
						case "note":
							return 6;
						case "comment":
							return 7;
						case "catalogue":
							return 8;
						default:
							return 9;
						}
					}
				});

				defHeaderList = definitions.stream().sorted(memberTypeComparator).map(ddMember -> {
					Header header = new Header();
					header.setHeader(ddMember.getMemberDocType().toUpperCase());
					header.setKey(ddMember.getMemberDocType().toLowerCase());

					String headerKey = header.getHeader() + header.getKey();
					if (headerKeys.contains(headerKey)) {
						return null; // skip duplicate header
					} else {
						headerKeys.add(headerKey);
						return header;
					}
				}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
						.collect(Collectors.toList());

//				defHeaderList = definitions.stream()
//						.sorted(Comparator.comparing(ddMember -> ddMember.getMemberDocType().toLowerCase()))
//						.map(ddMember -> {
//							Header header = new Header();
//							header.setHeader(ddMember.getMemberDocType().toUpperCase());
////							header.setKey(ddMember.getMemberDocType().toLowerCase().replace(" ", "_"));
//							header.setKey(ddMember.getMemberDocType().toLowerCase());
//							String headerKey = header.getHeader() + header.getKey();
//							if (headerKeys.contains(headerKey)) {
//								return null; // skip duplicate header
//							} else {
//								headerKeys.add(headerKey);
//								return header;
//							}
//						}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
//						.collect(Collectors.toList());

				Set<String> headerKeys1 = new HashSet<>();

				RelHeaderList = relations.stream().map(ddMember -> {
					Header header = new Header();
					header.setHeader(ddMember.getMemberRelation().toUpperCase());
//					header.setKey(ddMember.getMemberRelation().toLowerCase().replace(" ", "_"));
					header.setKey(ddMember.getMemberRelation().toLowerCase());
					String headerKey = header.getHeader() + header.getKey();
					if (headerKeys1.contains(headerKey)) {
						return null; // skip duplicate header
					} else {
						headerKeys1.add(headerKey);
						return header;
					}
				}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
						.collect(Collectors.toList());
			} else {

				temp.forEach(d -> {
					List<MemBrowseDTO> list = result.computeIfAbsent(d.getMemberDocType().toLowerCase(),
							k -> new ArrayList<>());
					MemBrowseDTO memBrowseDTO = new MemBrowseDTO();
					memBrowseDTO.setDescription(d.getMemberLineText());
					memBrowseDTO.setSequeneNo(Integer.toString(d.getRow_no()));
					memBrowseDTO.setTableType("definition");
					list.add(memBrowseDTO);
					Collections.sort(list, Comparator.comparingInt(dto -> Integer.parseInt(dto.getSequeneNo())));
				});

				Set<String> headerKeys = new HashSet<>();

				defHeaderList = temp.stream()
						.sorted(Comparator.comparing(ddMember -> ddMember.getMemberDocType().toLowerCase()))
						.map(ddMember -> {
							Header header = new Header();
							header.setHeader(ddMember.getMemberDocType().toUpperCase());
//							header.setKey(ddMember.getMemberDocType().toLowerCase().replace(" ", "_"));
							header.setKey(ddMember.getMemberDocType().toLowerCase());

							String headerKey = header.getHeader() + header.getKey();
							if (headerKeys.contains(headerKey)) {
								return null; // skip duplicate header
							} else {
								headerKeys.add(headerKey);
								return header;
							}
						}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
						.collect(Collectors.toList());

			}

			List<Object> resultList1 = new ArrayList<>();
			resultList1.add(result);

			List<Object> resultList2 = new ArrayList<>();
			resultList2.add(result1);

			List<Header> combinedHeaderList = new ArrayList<>();
			combinedHeaderList.addAll(defHeaderList);
			combinedHeaderList.addAll(RelHeaderList);

			List<Header> sortedHeaderList = combinedHeaderList.stream().sorted(headerTypeComparator)
					.collect(Collectors.toList());

			delheader.setRelationHeader(new ArrayList<>());
			delheader.setDefintionHeader(sortedHeaderList);

//			list.removeIf(map -> map instanceof Map && ((Map) map).isEmpty());

			List<Object> reslist = new ArrayList<>();

			reslist.add(result);
			reslist.add(result1);

			reslist.removeIf(map -> map instanceof Map && ((Map<?, ?>) map).isEmpty());

			delData.setDefinition(reslist);

			delData.setRelation(new ArrayList<>());

			mjson.setConfig(delheader);
			mjson.setData(delData);

			return mjson;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return mjson;

	}

	public DbRelationCombo getDefinitionAndRelation(String member) {

		List<MemberDefinitionObject> definitions = ddmemberobjectrepository.findByMemberName(member);
		List<MemberRelationObject> relations = ddmemberrelationrepository.findByMemberName(member);
		List<MemberDefinitonTemp> temp = temprepo.findByMemberName(member);
		MemberHeadingType memberHeadingType = new MemberHeadingType();
		MemRelheading relheading = new MemRelheading();

		DbRelationCombo combo = new DbRelationCombo();
		DefinitionAndRelation deff = new DefinitionAndRelation();

		// deff.setDefinition(memberHeadingType); // combo.setData(deff);

		DefRelHeader delheader = new DefRelHeader();
		DefRelDataUpdate delData = new DefRelDataUpdate();

		List<MemBrowseDTO> browseDTOs = new ArrayList<>();
		List<MemBrowseDTO> catalogue = new ArrayList<>();
		List<MemBrowseDTO> comment = new ArrayList<>();
		List<MemBrowseDTO> des = new ArrayList<>();
		List<MemBrowseDTO> note = new ArrayList<>();
		List<MemBrowseDTO> ad = new ArrayList<>();
		List<MemBrowseDTO> al = new ArrayList<>();

		for (MemberDefinitionObject definition : definitions) {

			MemBrowseDTO browseDTO = new MemBrowseDTO();

			switch (definition.getMemberDocType().toUpperCase()) {
			case "CATALOGUE":
				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");
				catalogue.add(browseDTO);

				break;
			case "COMMENT":
				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");
				comment.add(browseDTO);
				break;
			case "DESCRIPTION":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				// browseDTOs.add(browseDTO); //
				memberHeadingType.setDescription(browseDTOs);
				des.add(browseDTO);
				break;
			case "NOTE":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				note.add(browseDTO);
				break;
			case "ADMINISTRATIVE_DATA":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				ad.add(browseDTO);
				break;
			case "ALIAS":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				al.add(browseDTO);
				break;

			default:
				break;
			}
		}

		memberHeadingType.setCatalogue(catalogue);
		memberHeadingType.setComment(comment);
		memberHeadingType.setDescription(des);
		memberHeadingType.setAdministrativeData(ad);
		memberHeadingType.setAlias(al);
		memberHeadingType.setNote(note);

		deff.setDefinition(memberHeadingType);

		for (MemberDefinitonTemp tmp : temp) {

			MemBrowseDTO browseDTO = new MemBrowseDTO();

			switch (tmp.getMemberDocType().toUpperCase()) {
			case "CATALOGUE":
				browseDTO.setDescription(tmp.getMemberLineText());
				browseDTO.setSequeneNo(tmp.getRow_no() + "");
				catalogue.add(browseDTO);

				break;
			case "COMMENT":
				browseDTO.setDescription(tmp.getMemberLineText());
				browseDTO.setSequeneNo(tmp.getRow_no() + "");
				comment.add(browseDTO);
				break;
			case "DESCRIPTION":

				browseDTO.setDescription(tmp.getMemberLineText());
				browseDTO.setSequeneNo(tmp.getRow_no() + "");

//						browseDTOs.add(browseDTO);
//						memberHeadingType.setDescription(browseDTOs);
				des.add(browseDTO);
				break;
			case "NOTE":

				browseDTO.setDescription(tmp.getMemberLineText());
				browseDTO.setSequeneNo(tmp.getRow_no() + "");

				note.add(browseDTO);
				break;
			case "ADMINISTRATIVE_DATA":

				browseDTO.setDescription(tmp.getMemberLineText());
				browseDTO.setSequeneNo(tmp.getRow_no() + "");

				ad.add(browseDTO);
				break;
			case "ALIAS":

				browseDTO.setDescription(tmp.getMemberLineText());
				browseDTO.setSequeneNo(tmp.getRow_no() + "");

				al.add(browseDTO);
				break;

			default:
				break;
			}
		}

		memberHeadingType.setCatalogue(catalogue);
		memberHeadingType.setComment(comment);
		memberHeadingType.setDescription(des);
		memberHeadingType.setAdministrativeData(ad);
		memberHeadingType.setAlias(al);
		memberHeadingType.setNote(note);

		deff.setDefinition(memberHeadingType);

		List<MemBrowseDTO> out = new ArrayList<>();
		List<MemBrowseDTO> in = new ArrayList<>();
		List<MemBrowseDTO> up = new ArrayList<>();

		RelationHeadings relH = new RelationHeadings();

		List<MemBrowseDTO> browseDTORelation = new ArrayList<>();

		for (MemberRelationObject relation : relations) {
			MemBrowseDTO browseDTO = new MemBrowseDTO();

			switch (relation.getMemberRelation().toUpperCase()) {
			case "OUTPUTS":
				browseDTO.setDescription(relation.getRelatedMember());
				browseDTO.setSequeneNo(relation.getRow_no() + "");

				out.add(browseDTO);
				break;
			case "INPUTS":
				relH.getInputs().add(relation.getRelatedMember());
				browseDTO.setDescription(relation.getRelatedMember());
				browseDTO.setSequeneNo(relation.getRow_no() + " ");

				in.add(browseDTO);
				break;
			case "UPDATES":
				relH.getUpdates().add(relation.getRelatedMember());
				browseDTO.setDescription(relation.getRelatedMember());
				browseDTO.setSequeneNo(relation.getRow_no() + "");

				up.add(browseDTO);
				break;

			default:
				break;
			}
		}
		relheading.setInputs(in);
		relheading.setOutputs(out);
		relheading.setUpdates(up);

		List<Object> list1 = new ArrayList<>();
		list1.add(relH);

		deff.setDefinition(memberHeadingType);
		deff.setRelation(relheading);
		combo.setData(deff);
		combo.setConfig(delheader);
		delheader.setDefintionHeader(getdefHeaderForUpdate());
		delheader.setRelationHeader(getrelheader()); //
		delData.setDefintion(browseDTOs);
		delData.setRelation(browseDTORelation);
		// Mjson mjson = new Mjson(); ////mjson.setConfig(delheader);
		// mjson.setData(delData);

		return combo;
	}

	private List<Header> getdefHeaderForUpdate() {

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

		Header headerType14 = new Header();
		headerType14.setHeader("ALIAS");
		headerType14.setKey("alias");

		Header headerType15 = new Header();
		headerType15.setHeader("ADMINISTRATIVE_DATA");
		headerType15.setKey("administrativeData");

		headerList.add(headerType);
		headerList.add(headerType1);
		headerList.add(headerType2);
		headerList.add(headerType3);
		headerList.add(headerType4);
		headerList.add(headerType14);
		headerList.add(headerType15);

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

	public List<MemberDefinitonTemp> createNewMember(String newMemberId, String copyMemberId) {

		List<MemberDefinitionObject> list = ddmemberobjectrepository.findByMemberName(copyMemberId);
		List<MemberRelationObject> relations = ddmemberrelationrepository.findByMemberName(copyMemberId);
		List<MemberDefinitonTemp> definitonTemps = new ArrayList();

		list.stream().forEach(map -> {
			MemberDefinitonTemp newEntity = new MemberDefinitonTemp();
			newEntity.setMemberName(newMemberId);
			newEntity.setMemberType(map.getMemberType());
			System.out.println(map.getMemberType());
			newEntity.setMemberDocType(map.getMemberDocType());
			newEntity.setMemberLineText(map.getMemberLineText());
			newEntity.setMemberDoclNo(map.getMemberDoclNo());
			newEntity.setRow_no(map.getRow_no());
			newEntity.setProdStatus(map.getProdStatus());
			newEntity.setObjType("DATABASE-OBJECT");
			MemberDefinitonTemp savedData = temprepo.save(newEntity);
			definitonTemps.add(savedData);

		});

		relations.stream().forEach(map -> {
			MemberDefinitonTemp newEntity = new MemberDefinitonTemp();
			newEntity.setMemberName(newMemberId);
			newEntity.setMemberType(map.getMemberType());
			System.out.println(map.getMemberType());
			newEntity.setMemberDocType(map.getMemberRelation());
			newEntity.setMemberLineText(map.getRelatedMember());
//			newEntity.setMemberDoclNo(map.getMemberDoclNo());
			newEntity.setRow_no(map.getRow_no());
			newEntity.setProdStatus(map.getProdStatus());
			newEntity.setObjType("DATABASE-OBJECT");
			MemberDefinitonTemp savedData = temprepo.save(newEntity);
			definitonTemps.add(savedData);

		});

		return definitonTemps;
	}

	public DbRelationCombo PatternForCreatedMember(String member) {

		List<MemberDefinitonTemp> definitions = temprepo.findByMemberName(member);
		List<MemberRelationObject> relations = ddmemberrelationrepository.findByMemberName(member);
		MemberHeadingType memberHeadingType = new MemberHeadingType();
		MemRelheading relheading = new MemRelheading();

		DbRelationCombo combo = new DbRelationCombo();
		DefinitionAndRelation deff = new DefinitionAndRelation();
		DefRelHeader delheader = new DefRelHeader();
		DefRelDataUpdate delData = new DefRelDataUpdate();

		List<MemBrowseDTO> browseDTOs = new ArrayList<>();
		List<MemBrowseDTO> catalogue = new ArrayList<>();
		List<MemBrowseDTO> comment = new ArrayList<>();
		List<MemBrowseDTO> des = new ArrayList<>();
		List<MemBrowseDTO> note = new ArrayList<>();
		List<MemBrowseDTO> ad = new ArrayList<>();
		List<MemBrowseDTO> al = new ArrayList<>();

		for (MemberDefinitonTemp definition : definitions) {

			MemBrowseDTO browseDTO = new MemBrowseDTO();

			switch (definition.getMemberDocType().toUpperCase()) {
			case "CATALOGUE":
				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");
				catalogue.add(browseDTO);

				break;
			case "COMMENT":
				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");
				comment.add(browseDTO);
				break;
			case "DESCRIPTION":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

//				browseDTOs.add(browseDTO);
//				memberHeadingType.setDescription(browseDTOs);
				des.add(browseDTO);
				break;
			case "NOTE":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				note.add(browseDTO);
				break;
			case "ADMINISTRATIVE_DATA":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				ad.add(browseDTO);
				break;
			case "ALIAS":

				browseDTO.setDescription(definition.getMemberLineText());
				browseDTO.setSequeneNo(definition.getRow_no() + "");

				al.add(browseDTO);
				break;

			default:
				break;
			}
		}

		memberHeadingType.setCatalogue(catalogue);
		memberHeadingType.setComment(comment);
		memberHeadingType.setDescription(des);
		memberHeadingType.setAdministrativeData(ad);
		memberHeadingType.setAlias(al);
		memberHeadingType.setNote(note);

		deff.setDefinition(memberHeadingType);

		List<MemBrowseDTO> out = new ArrayList<>();
		List<MemBrowseDTO> in = new ArrayList<>();
		List<MemBrowseDTO> up = new ArrayList<>();

		RelationHeadings relH = new RelationHeadings();

		List<MemBrowseDTO> browseDTORelation = new ArrayList<>();

		for (MemberRelationObject relation : relations) {
			MemBrowseDTO browseDTO = new MemBrowseDTO();

			switch (relation.getMemberRelation().toUpperCase()) {
			case "OUTPUTS":
				browseDTO.setDescription(relation.getRelatedMember());
				browseDTO.setSequeneNo(relation.getRow_no() + "");

				out.add(browseDTO);
				break;
			case "INPUTS":
				relH.getInputs().add(relation.getRelatedMember());
				browseDTO.setDescription(relation.getRelatedMember());
				browseDTO.setSequeneNo(relation.getRow_no() + "");

				in.add(browseDTO);
				break;
			case "UPDATES":
				relH.getUpdates().add(relation.getRelatedMember());
				browseDTO.setDescription(relation.getRelatedMember());
				browseDTO.setSequeneNo(relation.getRow_no() + "");

				up.add(browseDTO);
				break;

			default:
				break;
			}
		}
		relheading.setInputs(in);
		relheading.setOutputs(out);
		relheading.setUpdates(up);

		List<Object> list1 = new ArrayList<>();
		list1.add(relH);

		deff.setDefinition(memberHeadingType);
		deff.setRelation(relheading);
		combo.setData(deff);
		combo.setConfig(delheader);
		delheader.setDefintionHeader(getdefHeaderForUpdate());
		delheader.setRelationHeader(getrelheader());
//		delData.setDefintion(browseDTOs);
		delData.setRelation(browseDTORelation);
		// Mjson mjson = new Mjson();
		//// mjson.setConfig(delheader);
		// mjson.setData(delData);

		return combo;
	}

	public Mjson updateDefinitionAndRelationMember(DbRelationCombo member) {
		Mjson mjson = new Mjson();
		String success = "failed";
		try {

			List<MemBrowseDTO> browseDTOs = new ArrayList<>();
			browseDTOs.addAll(member.getData().getDefinition().getAdministrativeData());
			browseDTOs.addAll(member.getData().getDefinition().getAlias());
			browseDTOs.addAll(member.getData().getDefinition().getComment());
			browseDTOs.addAll(member.getData().getDefinition().getCatalogue());
			browseDTOs.addAll(member.getData().getDefinition().getNote());
			browseDTOs.addAll(member.getData().getDefinition().getDescription());

			browseDTOs.parallelStream().forEach(def -> {
				temprepo.updatePriceByName(def.getDescription(), Integer.parseInt(def.getSequeneNo()));
			});

			List<MemBrowseDTO> browseForRelation = new ArrayList<>();
			browseForRelation.addAll(member.getData().getRelation().getInputs());

			browseForRelation.parallelStream().forEach(rel -> {
				ddmemberobjectrepository.updatePriceByName(rel.getDescription(), Integer.parseInt(rel.getSequeneNo()));
			});

			success = "succcess";

		} catch (Exception e) {

			e.printStackTrace();
		}
		return mjson;
	}

	public void deleteByMemberName(String memberName) {

		ddmemberobjectrepository.deleteByMemberName(memberName);

		temprepo.deleteByMemberName(memberName);
	}

	public Mjson getDDdMemberBrowses(String value) {

		value = value.replace('*', ' ').trim();
		Mjson mjson = new Mjson();
		List<MemberDefinitionObject> definitions = new ArrayList<>();
		List<MemberRelationObject> relations = new ArrayList<>();
		List<MemberDefinitonTemp> temp = new ArrayList<>();

		DefRelHeader delheader = new DefRelHeader();
		DefRelData delData = new DefRelData();

		definitions = ddmemberobjectrepository.findByMemberName(value);
		relations = ddmemberrelationrepository.findByMemberName(value);
		temp = temprepo.findByMemberName(value);

		List<Object> list = new ArrayList<>();
		List<Object> list1 = new ArrayList<>();

		List<Header> defHeaderList = new ArrayList<>();
		List<Header> RelHeaderList = new ArrayList<>();

		try {

			if (!temp.isEmpty()) {

				Map<String, List<String>> result = new LinkedHashMap<>();

				// group temp by memberDocType and aggregate memberLineText values
				temp.forEach(d -> result.computeIfAbsent(d.getMemberDocType().toLowerCase(), k -> new ArrayList<>())
						.add(d.getMemberLineText()));
				list.add(result);

				Set<String> headerKeys = new HashSet<>();

				Comparator<MemberDefinitonTemp> memberTypeComparator = Comparator.comparing(ddMember -> {
					String type = ddMember.getMemberDocType().toLowerCase();
					if (type.startsWith("freq")) {
						return 1;
					} else {
						switch (type) {
						case "inputs":
							return 2;
						case "outputs":
							return 3;
						case "calls":
							return 4;
						case "description":
							return 5;
						case "note":
							return 6;
						case "comment":
							return 7;
						case "catalog":
							return 8;
						default:
							return 9;
						}
					}
				});

				defHeaderList = temp.stream().sorted(memberTypeComparator).map(ddMember -> {
					Header header = new Header();
					header.setHeader(ddMember.getMemberDocType().toUpperCase());
					header.setKey(ddMember.getMemberDocType().toLowerCase());

					String headerKey = header.getHeader() + header.getKey();
					if (headerKeys.contains(headerKey)) {
						return null; // skip duplicate header
					} else {
						headerKeys.add(headerKey);
						return header;
					}
				}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
						.collect(Collectors.toList());

//				defHeaderList = temp.stream()
//						.sorted(Comparator.comparing(ddMember -> ddMember.getMemberDocType().toLowerCase()))
//						.map(ddMember -> {
//							Header header = new Header();
//							header.setHeader(ddMember.getMemberDocType().toUpperCase());
//							header.setKey(ddMember.getMemberDocType().toLowerCase());
////							header.setKey(ddMember.getMemberDocType().toLowerCase().replace(" ", "_"));
//
//							String headerKey = header.getHeader() + header.getKey();
//							if (headerKeys.contains(headerKey)) {
//								return null; // skip duplicate header
//							} else {
//								headerKeys.add(headerKey);
//								return header;
//							}
//						}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
//						.collect(Collectors.toList());

			} else if (definitions.isEmpty()) {

				definitions = ddmemberobjectrepository.findByMemberName(value);

			} else {

				temp.stream().sorted(Comparator.comparingLong(MemberDefinitonTemp::converToInt))
						.collect(Collectors.toList());

				Map<String, List<String>> result = new LinkedHashMap<>();
//
//				// group definitions by memberDocType and aggregate memberLineText values
//				definitions
//						.forEach(d -> result.computeIfAbsent(d.getMemberDocType().toLowerCase(), k -> new ArrayList<>())
//								.add(d.getMemberLineText()));

				List<MemberDefinitionObject> sortedDefinitions = new ArrayList<>(definitions);

				Collections.sort(sortedDefinitions, Comparator.comparingInt(MemberDefinitionObject::getRow_no));

//				sortedDefinitions.forEach(d -> {
//					String docType = d.getMemberDocType().toLowerCase();
//					List<String> lines = result.computeIfAbsent(docType, k -> new ArrayList<>());
//					Set<String> uniqueLines = new LinkedHashSet<>(lines);
//					uniqueLines.add(d.getMemberLineText());
//
//					lines.clear();
//					lines.addAll(uniqueLines);
//				});

				sortedDefinitions.forEach(d -> {
					String docType = d.getMemberDocType().toLowerCase();
					List<String> lines = result.computeIfAbsent(docType, k -> new ArrayList<>());
					lines.add(d.getMemberLineText());
				});

				list.add(result);

				Set<String> headerKeys = new HashSet<>();

				Comparator<MemberDefinitionObject> memberTypeComparator = Comparator.comparing(ddMember -> {
					String type = ddMember.getMemberDocType().toLowerCase();
					if (type.startsWith("freq")) {
						return 1;
					} else {
						switch (type) {
						case "inputs":
							return 2;
						case "outputs":
							return 3;
						case "calls":
							return 4;
						case "description":
							return 5;
						case "note":
							return 6;
						case "comment":
							return 7;
						case "catalog":
							return 8;
						default:
							return 9;
						}
					}
				});

				defHeaderList = definitions.stream().sorted(memberTypeComparator).map(ddMember -> {
					Header header = new Header();
					header.setHeader(ddMember.getMemberDocType().toUpperCase());
					header.setKey(ddMember.getMemberDocType().toLowerCase());

					String headerKey = header.getHeader() + header.getKey();
					if (headerKeys.contains(headerKey)) {
						return null; // skip duplicate header
					} else {
						headerKeys.add(headerKey);
						return header;
					}
				}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
						.collect(Collectors.toList());
			}

			if (relations.isEmpty()) {

				relations = ddmemberrelationrepository.findByMemberName(value);

			} else {
				Map<String, List<String>> result1 = new LinkedHashMap<>();

				List<MemberRelationObject> sortedRelations = new ArrayList<>(relations);

				Collections.sort(sortedRelations, Comparator.comparingInt(MemberRelationObject::getRow_no));

//				relations.forEach(d -> {
//					if (d.getMemberType().equalsIgnoreCase("RECORD") || d.getMemberType().equalsIgnoreCase("GROUP")) {
//						result1.computeIfAbsent("contains", k -> new ArrayList<>()).add(d.getRelatedMember());
//					} else {
//
//						result1.computeIfAbsent(d.getMemberRelation().toLowerCase(), k -> new ArrayList<>())
//								.add(d.getRelatedMember());
//					}
//				});

//				 group relations by memberDocType and aggregate memberLineText values
				relations.forEach(
						d -> result1.computeIfAbsent(d.getMemberRelation().toLowerCase(), k -> new ArrayList<>())
								.add(d.getRelatedMember()));

				Collections.sort(sortedRelations, Comparator.comparingInt(MemberRelationObject::getRow_no));

//				sortedRelations.forEach(d -> {
//					String docType = d.getMemberRelation().toLowerCase();
//					List<String> lines = result1.computeIfAbsent(docType, k -> new ArrayList<>());
//					Set<String> uniqueLines = new LinkedHashSet<>(lines);
//					uniqueLines.add(d.getRelatedMember());
//
//					lines.clear();
//					lines.addAll(uniqueLines);
//				});

				sortedRelations.forEach(d -> {
					String docType = d.getMemberRelation().toLowerCase();
					List<String> lines = result1.computeIfAbsent(docType, k -> new ArrayList<>());
					lines.add(d.getRelatedMember());
				});

				list.add(result1);

				Set<String> headerKeys1 = new HashSet<>();

				RelHeaderList = relations.stream().map(ddMember -> {
					Header header = new Header();
					header.setHeader(ddMember.getMemberRelation().toUpperCase());
//					header.setKey(ddMember.getMemberRelation().toLowerCase().replace(" ", "_"));
					header.setKey(ddMember.getMemberRelation().toLowerCase());

					String headerKey = header.getHeader() + header.getKey();
					if (headerKeys1.contains(headerKey)) {
						return null; // skip duplicate header
					} else {
						headerKeys1.add(headerKey);
						return header;
					}
				}).filter(Objects::nonNull) // filter out null headers (i.e. duplicates)
						.collect(Collectors.toList());
			}
//			list1.removeIf(map -> map instanceof Map && ((Map) map).isEmpty());
//			list.removeIf(map -> map instanceof Map && ((Map) map).isEmpty());
//
//			delheader.setDefintionHeader(defHeaderList);
//			delheader.setRelationHeader(RelHeaderList);
//			delData.setDefinition(list);
//			delData.setRelation(list1);

			List<Header> combinedHeaderList = new ArrayList<>();
			combinedHeaderList.addAll(defHeaderList);
			combinedHeaderList.addAll(RelHeaderList);

			List<Header> sortedHeaderList = combinedHeaderList.stream().sorted(headerTypeComparator)
					.collect(Collectors.toList());

			delheader.setRelationHeader(new ArrayList<>());
			delheader.setDefintionHeader(sortedHeaderList);

			list.removeIf(map -> map instanceof Map && ((Map) map).isEmpty());
			delData.setDefinition(list);
			delData.setRelation(new ArrayList<>());

			mjson.setConfig(delheader);
			mjson.setData(delData);

			return mjson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mjson;

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

}
