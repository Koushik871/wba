package com.dxc.wba.xref.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.ddentity.DDMemberLookup;
import com.dxc.wba.xref.ddentity.DDMemberdefinition;
import com.dxc.wba.xref.ddentity.DDNewMemberTable;
import com.dxc.wba.xref.ddentity.MemberCategory;
import com.dxc.wba.xref.ddentity.MemberRelation;
import com.dxc.wba.xref.dto.MemberDto;
import com.dxc.wba.xref.repository.DDMemberLookupRepo;
import com.dxc.wba.xref.repository.DDProgramRepository;
import com.dxc.wba.xref.repository.DfMemberRepository;
import com.dxc.wba.xref.repository.MemberCategoryRepository;
import com.dxc.wba.xref.repository.NewMemberRepository;
import com.dxc.wba.xref.type.DDCategoryJson;
import com.dxc.wba.xref.type.DDCategoryUpdateJson;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class DDProgramService {

	@Autowired
	private DDProgramRepository ddrepo;

	@Autowired
	private MemberCategoryRepository catrepo;

	@Autowired
	private DfMemberRepository dfmemberrepo;

	@Autowired
	private NewMemberRepository ddnewrepo;

	@Autowired
	private DDMemberLookupRepo lookuprepo;

	public DDCategoryJson memberDataBrowse(String memberName) {
		DDCategoryJson dd = new DDCategoryJson();
		try {
			List<DDMemberdefinition> DDmemberList = new ArrayList<>();
			List<DDNewMemberTable> definitonTemps = new ArrayList<>();
			List<MemberCategory> CategoryList = new ArrayList<>();
			DDmemberList = ddrepo.findMemberByMemberName(memberName);
			CategoryList = catrepo.findAll();
			definitonTemps = ddnewrepo.findByMemberName(memberName);

			List<String> list = new ArrayList<>();
			List<String> list1 = new ArrayList<>();
			if (!DDmemberList.isEmpty()) {
				DDmemberList.sort(Comparator.comparingInt(DDMemberdefinition::getLineNo));
				for (DDMemberdefinition value : DDmemberList) {
					list.add(value.getMemberLineText());
				}
				for (MemberCategory value : CategoryList) {
					list1.add(value.getMemCategory());
				}
			} else {
				definitonTemps.sort(Comparator.comparingInt(DDNewMemberTable::getLineNo));
				for (DDNewMemberTable value : definitonTemps) {
					list.add(value.getMemberLineText());
				}
				for (MemberCategory value : CategoryList) {
					list1.add(value.getMemCategory());
				}

			}
			dd.setData(list);
			dd.setCategory(list1);

			return dd;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Object memberDataBrowseforNew(String newmemberid, String copymemberid) {
		try {
			List<DDMemberdefinition> DDmemberList = ddrepo.findMemberByMemberName(newmemberid);
			List<DDNewMemberTable> definitonTemps = ddnewrepo.findByMemberName(newmemberid);

			if (!DDmemberList.isEmpty() || !definitonTemps.isEmpty()) {
				String jsonResponse = "{\"message\":\"Member Name already exists\"}";
				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonResponse);
			} else {
				DDCategoryJson dd = new DDCategoryJson();
				List<MemberCategory> CategoryList = catrepo.findAll();
				List<String> list1 = new ArrayList<>();
				List<String> datalist = new ArrayList<>();

				for (MemberCategory value : CategoryList) {
					list1.add(value.getMemCategory());
				}

				dd.setCategory(list1);

				List<String> data = new ArrayList<>();
				List<DDMemberdefinition> DDmemberLists = ddrepo.findMemberByMemberName(copymemberid);
				List<DDNewMemberTable> definitonTempss = ddnewrepo.findByMemberName(copymemberid);

				// Assuming you have logic to retrieve data from DDmemberLists and
				// definitonTempss
				// and add them to the datalist
				// Example:
				for (DDMemberdefinition member : DDmemberLists) {
					datalist.add(member.getMemberLineText());
				}
				for (DDNewMemberTable definition : definitonTempss) {
					datalist.add(definition.getMemberLineText());
				}

				dd.setData(datalist);

				return dd;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DDCategoryUpdateJson memberDataBrowseforupdate(String memberName) {
		DDCategoryUpdateJson dd = new DDCategoryUpdateJson();
		try {
			List<DDMemberdefinition> DDmemberList = new ArrayList<>();
			List<MemberCategory> CategoryList = new ArrayList<>();
			DDmemberList = ddrepo.findMemberByMemberName(memberName);
			CategoryList = catrepo.findAll();

			List<String> list = new ArrayList<>();

			List<MemberDto> resultList = new ArrayList<>();

			DDmemberList.forEach(d -> {
				MemberDto memBrowseDTO = new MemberDto();
				memBrowseDTO.setDescription(d.getMemberLineText());
				memBrowseDTO.setSequeneNo(Integer.toString(d.getLineNo()));

				resultList.add(memBrowseDTO);
			});

			for (MemberCategory value : CategoryList) {
				list.add(value.getMemCategory());
			}
			dd.setData(resultList);
			dd.setCategory(list);

			return dd;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void createNewMember(String newMemberId, String copyMemberId) {

		List<DDMemberdefinition> list = ddrepo.findMemberByMemberName(copyMemberId);

		List<DDNewMemberTable> definitonTemps = new ArrayList();

		list.stream().forEach(map -> {
			DDNewMemberTable newEntity = new DDNewMemberTable();
			newEntity.setMemberName(newMemberId);
			newEntity.setMemberType(map.getMemberType());
			newEntity.setLineNo(map.getLineNo());
			System.out.println(map.getMemberType());

			newEntity.setMemberLineText(map.getMemberLineText());
			newEntity.setProdStatus(map.getProdStatus());

			DDNewMemberTable savedData = ddnewrepo.save(newEntity);
			definitonTemps.add(savedData);

		});

	}

//	public void createNewMember(String newMemberId, String copyMemberId) {
//	    List<DDMemberdefinition> list = ddrepo.findMemberByMemberName(copyMemberId);
//	    List<DDNewMemberTable> definitonTemps = new ArrayList();
//
//	    boolean isFirstLine = true; // To track the first occurrence of the target line
//
//	    for (DDMemberdefinition map : list) {
//	        DDNewMemberTable newEntity = new DDNewMemberTable();
//	        newEntity.setMemberName(newMemberId);
//	        newEntity.setMemberType(map.getMemberType());
//	        newEntity.setLineNo(map.getLineNo());
//	        System.out.println(map.getMemberType());
//
//	        // Modify the line text for the first occurrence
//	        if (isFirstLine) {
//	            String modifiedLineText = map.getMemberLineText().replaceFirst("REPORT OF ITEM .+", "REPORT OF ITEM " + newMemberId);
//	            newEntity.setMemberLineText(modifiedLineText);
//	            isFirstLine = false; // Mark the first line as processed
//	        } else {
//	            newEntity.setMemberLineText(map.getMemberLineText());
//	        }
//
//	        newEntity.setProdStatus(map.getProdStatus());
//
//	        DDNewMemberTable savedData = ddnewrepo.save(newEntity);
//	        definitonTemps.add(savedData);
//	    }
//	}

	public DDCategoryJson updateMemberData(String memberName, Map<String, List<String>> dataArrayMap) {
		List<String> data = dataArrayMap.get("DATA");
		if (data != null) {
			updateMemberData1(memberName, data.toArray(new String[0]));
		}

		// Retrieving member relations for the specified memberName
		List<MemberRelation> memberRelationList = dfmemberrepo.findByMemberName(memberName);

		List<DDMemberdefinition> memberdefintionList = ddrepo.findMemberByMemberName(memberName);
		List<DDNewMemberTable> newmemberdefintionList = ddnewrepo.findMemberByMember(memberName);
		dfmemberrepo.deleteByMemberName1(memberName);
		// Iterating through the dataArrayMap entries
		for (Map.Entry<String, List<String>> entry : dataArrayMap.entrySet()) {
			String arrayName = entry.getKey();
			List<String> arrayData = entry.getValue();

			// Skipping the "DATA" entry and empty arrays
			if (!"DATA".equals(arrayName) && !arrayData.isEmpty()) {
				// Deleting existing member relations for the given memberName and arrayName
//				dfmemberrepo.deleteByMemberNameAndMemberRelation(memberName, arrayName);

				int i = 1;
				for (String item : arrayData) {
					MemberRelation rel = new MemberRelation();
					rel.setMemberName(memberName);
					rel.setRelatedMember(item);
					rel.setMemberRelation(arrayName);
					rel.setRow_no(i + 1);
					if (!memberdefintionList.isEmpty()) {
						DDMemberdefinition memberdefintion = memberdefintionList.get(0);
						rel.setMemberType(memberdefintion.getMemberType());
						rel.setProdStatus(memberdefintion.getProdStatus());
						rel.setRelatedMemType(memberdefintion.getMemberType());
						rel.setRelatedMemProdStatus(memberdefintion.getProdStatus());
					} else if ((!newmemberdefintionList.isEmpty())) {
						DDNewMemberTable newmemberdefintion = newmemberdefintionList.get(0);
						rel.setMemberType(newmemberdefintion.getMemberType());
						rel.setProdStatus(newmemberdefintion.getProdStatus());
						rel.setRelatedMemType(newmemberdefintion.getMemberType());
						rel.setRelatedMemProdStatus(newmemberdefintion.getProdStatus());

					}

					if (!memberRelationList.isEmpty()) {
						MemberRelation memberRelation = memberRelationList.get(0);
						rel.setMemberType(memberRelation.getMemberType());
						rel.setProdStatus(memberRelation.getProdStatus());
						rel.setRelatedMemType(memberRelation.getRelatedMemType());
						rel.setRelatedMemProdStatus(memberRelation.getRelatedMemProdStatus());
					}

					dfmemberrepo.save(rel);
					i++;
				}
			}
		}

		return memberDataBrowse(memberName);
	}

	private void updateMemberRelationTable(String memberName, Map<String, List<String>> dataArrayMap) {
		// Retrieving member relations for the specified memberName
		List<MemberRelation> memberRelationList = dfmemberrepo.findByMemberName(memberName);

		List<DDMemberdefinition> memberdefintionList = ddrepo.findMemberByMemberName(memberName);

		// Iterating through the dataArrayMap entries
		for (Map.Entry<String, List<String>> entry : dataArrayMap.entrySet()) {
			String arrayName = entry.getKey();
			List<String> arrayData = entry.getValue();

			// Skipping the "DATA" entry and empty arrays
			if (!"DATA".equals(arrayName) && !arrayData.isEmpty()) {
				// Deleting existing member relations for the given memberName and arrayName
				dfmemberrepo.deleteByMemberNameAndMemberRelation(memberName, arrayName);

				int i = 1;
				for (String item : arrayData) {
					MemberRelation rel = new MemberRelation();
					rel.setMemberName(memberName);
					rel.setRelatedMember(item);
					rel.setMemberRelation(arrayName);
					rel.setRow_no(i + 1);

					DDMemberdefinition memberdefintion = memberdefintionList.get(0);
					rel.setMemberType(memberdefintion.getMemberType());
					rel.setProdStatus(memberdefintion.getProdStatus());
					rel.setRelatedMemType(memberdefintion.getMemberType());
					rel.setRelatedMemProdStatus(memberdefintion.getProdStatus());

					if (!memberRelationList.isEmpty()) {
						MemberRelation memberRelation = memberRelationList.get(0);
						rel.setMemberType(memberRelation.getMemberType());
						rel.setProdStatus(memberRelation.getProdStatus());
						rel.setRelatedMemType(memberRelation.getRelatedMemType());
						rel.setRelatedMemProdStatus(memberRelation.getRelatedMemProdStatus());
					}

					dfmemberrepo.save(rel);
					i++;
				}
			}
		}

	}

//	private void updateMemberRelationTable(String memberName, Map<String, List<String>> dataArrayMap) {
//		List<MemberRelation> memberRelationList = dfmemberrepo.findByMemberName(memberName);
//
//		for (MemberRelation memberRelation : memberRelationList) {
//			String arrayName = memberRelation.getMemberRelation();
//			List<String> arrayData = dataArrayMap.get(arrayName);
//
//			if (arrayData != null) {
//				dfmemberrepo.deleteByMemberNameAndMemberRelation(memberName, arrayName);
//
//				int i = 1;
//				for (String item : arrayData) {
//					MemberRelation rel = new MemberRelation();
//					rel.setMemberName(memberName);
//					rel.setRelatedMember(item);
//					rel.setMemberRelation(arrayName);
//					rel.setMemberType(memberRelation.getMemberType());
//					rel.setProdStatus(memberRelation.getProdStatus());
//					rel.setRelatedMemType(memberRelation.getRelatedMemType());
//					rel.setRelatedMemProdStatus(memberRelation.getRelatedMemProdStatus());
//					rel.setRow_no(i);
//					dfmemberrepo.save(rel);
//					i++;
//				}
//			}
//		}
//	}

	@Transactional
	public void updateMemberData1(String memberName, String[] data) {

		List<DDMemberdefinition> DDmemberList = ddrepo.findMemberByMemberName(memberName);
		List<DDNewMemberTable> ddNewMemberTable = ddnewrepo.findByMemberName(memberName);
		if (!DDmemberList.isEmpty()) {
			ddrepo.deleteByMemberName(memberName);
			DDMemberdefinition firstMember = DDmemberList.get(0);
			for (int i = 0; i < data.length; i++) {
				DDMemberdefinition dataLine = new DDMemberdefinition();
				dataLine.setLineNo(i + 1);
				dataLine.setMemberLineText(data[i]);
				dataLine.setMemberName(memberName);
				dataLine.setMemberType(firstMember.getMemberType());
				dataLine.setProdStatus(firstMember.getProdStatus());
				ddrepo.saveAndFlush(dataLine);
			}
		}
		if (!ddNewMemberTable.isEmpty()) {
			DDNewMemberTable firstMember = ddNewMemberTable.get(0);
			ddnewrepo.deleteByMemberName(memberName);
			for (int i = 0; i < data.length; i++) {
				DDNewMemberTable dataLine = new DDNewMemberTable();
				dataLine.setLineNo(i + 1);
				dataLine.setMemberLineText(data[i]);
				dataLine.setMemberName(memberName);
				dataLine.setMemberType(firstMember.getMemberType());
				dataLine.setProdStatus(firstMember.getProdStatus());
				ddnewrepo.save(dataLine);
			}
		}

	}

	@Transactional
	public void createMemberFromNewData(String memberName, String newMemberId, String[] data) {
		List<DDMemberdefinition> DDmemberList = ddrepo.findMemberByMemberName(memberName);
		List<DDNewMemberTable> ddNewMemberTable = ddnewrepo.findByMemberName(memberName);
		List<DDMemberLookup> ddNewMemberlookup = lookuprepo.findByMembername(memberName);

		if (!DDmemberList.isEmpty()) {
			DDMemberdefinition firstMember = DDmemberList.get(0);
			IntStream.range(0, data.length).mapToObj(i -> {
				DDMemberdefinition dataLine = new DDMemberdefinition();
				dataLine.setLineNo(i + 1);
				dataLine.setMemberLineText(data[i]);
				dataLine.setMemberName(newMemberId);
				dataLine.setMemberType(firstMember.getMemberType());
				dataLine.setProdStatus(firstMember.getProdStatus());
				return dataLine;
			}).forEach(ddrepo::save);

			String firstLetter = newMemberId.substring(0, 1);
			List<String> namesStartingWithFirstLetter = lookuprepo.findByMemberss(firstLetter);
			namesStartingWithFirstLetter.add(newMemberId);
	
			Collections.sort(namesStartingWithFirstLetter);

			int indexOfMember = namesStartingWithFirstLetter.indexOf(newMemberId);
			if (indexOfMember > 0) {
				String previousName = namesStartingWithFirstLetter.get(indexOfMember - 1);
				double row = lookuprepo.findByRowNumber(previousName);

				row = row += 0.1;
				lookuprepo.insertMemberDefinition1(row, newMemberId, firstMember.getMemberType(),
						firstMember.getProdStatus());
			}
		}

//		DDMemberdefinition firstMember = DDmemberList.get(0);
//			DDMemberLookup firstMember = ddNewMemberlookup.get(0);

//		DDMemberLookup dataLine1 = new DDMemberLookup();
//		dataLine1.setMemberName(newMemberId);
//		dataLine1.setRow_no(row++);
//		dataLine1.setMemberType(firstMember.getMemberType());
//		dataLine1.setProdStatus(firstMember.getProdStatus());

	}

	public void createMemberFromNewDatas(String copyMemberId, String newMemberId,
			Map<String, List<String>> dataArrayMap) {

		List<DDMemberdefinition> DDmemberList = ddrepo.findMemberByMemberName(copyMemberId);
		List<String> data = dataArrayMap.get("DATA");
		if (data != null) {
			createMemberFromNewData(copyMemberId, newMemberId, data.toArray(new String[0]));

		}
		if (!DDmemberList.isEmpty()) {
			createMemberRelation(copyMemberId, newMemberId, dataArrayMap);
		}
//		dataArrayMap.remove("DATA");

	}

	private void createMemberRelation(String copyMemberId, String newMemberId, Map<String, List<String>> dataArrayMap) {
		String[] arrayNamesToProcess = { "SEE", "INPUTS", "UPDATES", "DELETES", "CONTAINS", "OUTPUTS", "SORT-KEY",
				"CALLS", "PARAMETERS", "ON", "FROM" };
		int i = 1;

		List<MemberRelation> memberRelationList = dfmemberrepo.findByMemberName(copyMemberId);
		
		List<DDMemberdefinition> DDmemberList = ddrepo.findMemberByMemberName(copyMemberId);
		if (memberRelationList.isEmpty()) {
			
			
			for (String arrayName : arrayNamesToProcess) {
				List<String> arrayData = dataArrayMap.get(arrayName);

				if (arrayData != null) {
					DDMemberdefinition memberRelation = DDmemberList.get(0);
					System.out.println("Processing array: " + arrayName);
					for (String item : arrayData) {
						System.out.println("Processing item: " + item);
						MemberRelation rel = new MemberRelation();
						rel.setMemberName(newMemberId);
						rel.setRelatedMember(item);
						rel.setMemberRelation(arrayName);
						rel.setMemberType(memberRelation.getMemberType());
						rel.setProdStatus(memberRelation.getProdStatus());
						rel.setRelatedMemType(memberRelation.getMemberType());
						rel.setRelatedMemProdStatus(memberRelation.getProdStatus());
						rel.setRow_no(i);
						dfmemberrepo.save(rel);
						i++;
					}
				} else {
					System.out.println("No data for array: " + arrayName);
				}
			}
			
		}

		for (String arrayName : arrayNamesToProcess) {
			List<String> arrayData = dataArrayMap.get(arrayName);

			if (arrayData != null) {
				MemberRelation memberRelation = memberRelationList.get(0);
				System.out.println("Processing array: " + arrayName);
				for (String item : arrayData) {
					System.out.println("Processing item: " + item);
					MemberRelation rel = new MemberRelation();
					rel.setMemberName(newMemberId);
					rel.setRelatedMember(item);
					rel.setMemberRelation(arrayName);
					rel.setMemberType(memberRelation.getMemberType());
					rel.setProdStatus(memberRelation.getProdStatus());
					rel.setRelatedMemType(memberRelation.getRelatedMemType());
					rel.setRelatedMemProdStatus(memberRelation.getRelatedMemProdStatus());
					rel.setRow_no(i);
					dfmemberrepo.save(rel);
					i++;
				}
			} else {
				System.out.println("No data for array: " + arrayName);
			}
		}
	}

//	private void createMemberRelation(String copyMemberId, String newMemberId, Map<String, List<String>> dataArrayMap) {
//		List<MemberRelation> memberRelationList = dfmemberrepo.findByMemberName(copyMemberId);
//
//		for (MemberRelation memberRelation : memberRelationList) {
//			String arrayName = memberRelation.getMemberRelation();
//			List<String> arrayData = dataArrayMap.get(arrayName);
//
//			int i = 1;
//			for (String item : arrayData) {
//				MemberRelation rel = new MemberRelation();
//				rel.setMemberName(newMemberId);
//				rel.setRelatedMember(item);
//				rel.setMemberRelation(arrayName);
//				rel.setMemberType(memberRelation.getMemberType());
//				rel.setProdStatus(memberRelation.getProdStatus());
//				rel.setRelatedMemType(memberRelation.getRelatedMemType());
//				rel.setRelatedMemProdStatus(memberRelation.getRelatedMemProdStatus());
//				rel.setRow_no(i+1);
//				dfmemberrepo.save(rel);
//				;
//			}
//
//		}
//	}

	public void saveDataLine(int lineNum, String lineText, String member) {
		DDMemberdefinition dataLine = new DDMemberdefinition();
		DDMemberdefinition dataLine1 = new DDMemberdefinition();

		List<DDNewMemberTable> definitonTemps = new ArrayList();
		definitonTemps = ddnewrepo.findByMemberName(member);

		List<DDMemberdefinition> DDmemberList = new ArrayList<>();
		DDmemberList = ddrepo.findMemberByMemberName(member);

		if (!DDmemberList.isEmpty()) {
			dataLine.setLineNo(lineNum);
			dataLine.setMemberLineText(lineText);
			dataLine.setMemberName(member);

			dataLine.setMemberType(DDmemberList.get(0).getMemberType());
			dataLine.setProdStatus(DDmemberList.get(0).getProdStatus());

		}

		DDmemberList = ddrepo.findMemberByMemberName(member);
		ddrepo.saveAndFlush(dataLine);
	}

//	public DDMemberdefinition saveDataLine1(int lineNum, String lineText, String member) {
//	    DDMemberdefinition dataLine = new DDMemberdefinition();
//	    
//	    List<DDMemberdefinition> DDmemberList = ddrepo.findMemberByMemberName(member);
//	    
//	    Optional<String> memberTypeOptional = DDmemberList.stream()
//	        .map(DDMemberdefinition::getMemberType)
//	        .findFirst();
//	    
//	    String memberType = memberTypeOptional.orElse(null);
//
//	    dataLine.setLineNo(lineNum);
//	    dataLine.setMemberLineText(lineText);
//	    dataLine.setMemberName(member);
//	    dataLine.setMemberType(memberType);
//
//	    return ddrepo.save(dataLine);
//	}

}
