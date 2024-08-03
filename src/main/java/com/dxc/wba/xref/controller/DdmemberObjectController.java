package com.dxc.wba.xref.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.wba.xref.dbmodel.DbRelationCombo;
import com.dxc.wba.xref.ddentity.MemberDefinitionObject;
import com.dxc.wba.xref.ddentity.MemberDefinitonTemp;
import com.dxc.wba.xref.ddentity.MemberRelationObject;
import com.dxc.wba.xref.dto.MemBrowseDTO;
import com.dxc.wba.xref.dto.MemberUpdateRequest;
import com.dxc.wba.xref.repository.DdMemberRelationRepository;
import com.dxc.wba.xref.repository.DdmemberObjectRepository;
import com.dxc.wba.xref.repository.MemberdefinitionTempRepository;
import com.dxc.wba.xref.service.DdMemberObjectService;
import com.dxc.wba.xref.type.DDWduJson;
import com.dxc.wba.xref.type.Mjson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DdmemberObjectController {

	@Autowired
	private DdMemberObjectService ddMemberObjectService;

	@Autowired
	private DdmemberObjectRepository ddmemberobjectrepository;

	@Autowired
	private DdMemberRelationRepository ddmemberrelationrepository;

	@Autowired
	MemberdefinitionTempRepository temprepo;

	@GetMapping("/getDBMemberBrowse/{member}")
	public Mjson getDDMemberbrowses(@PathVariable(required = false) String member) {

		return ddMemberObjectService.getDDdMemberBrowses(member);
	}

	@GetMapping("/getDBWDU/{related_member}")
	public DDWduJson getWdu(@PathVariable(required = false) String related_member) {
		return ddMemberObjectService.getWduMember(related_member);
	}

	@GetMapping(value = "/getMemberforUpdateDataset/{member}")
	public Mjson getMemberforUpdateDataset(@PathVariable(required = false) String member) {
		return ddMemberObjectService.getDatabaseMemberforUpdates(member);
	}

	@PutMapping(value = "updateMemberForDataset")
	public Mjson updateDefinitionAndRelation(@RequestBody DbRelationCombo combo) {
		return ddMemberObjectService.updateDefinitionAndRelation(combo);
	}

	@DeleteMapping("/deleteMemberForDataset/{memberName}")
	public Mjson deleteUser(@PathVariable String memberName) {
		Mjson m = new Mjson();
		ddMemberObjectService.deleteByMemberName(memberName);
		return m;

	}

	@PostMapping(value = "/createNewMemberForDataset")
	public Mjson createNewMember(@RequestParam(value = "newMemberId") String newMemberId,

			@RequestParam(value = "copyMemberId") String copyMemberId) {
//		List<MemberDefinitonTemp> values = null;
		try {
			ddMemberObjectService.createNewMember(newMemberId, copyMemberId);
			return ddMemberObjectService.getDatabaseMemberforUpdates(newMemberId);
		} catch (Exception e) {

		}
		return null;

	}

	@PutMapping(value = "updateCreatedMemberForDataset")
	public Mjson updateDefinitionAndRelationNewMember(@RequestBody DbRelationCombo combo) {
		return ddMemberObjectService.updateDefinitionAndRelationMember(combo);
	}

	@PutMapping("/updateDatasetObjectDD/{member}")
	public MemberUpdateRequest updateDefinitionAndRelationMember(@PathVariable(required = false) String member,
			@RequestBody MemberUpdateRequest request) {

		MemberUpdateRequest m = new MemberUpdateRequest();
		try {

			List<MemberDefinitionObject> definitions = ddmemberobjectrepository.findByMemberName(member);
			List<MemberRelationObject> relations = ddmemberrelationrepository.findByMemberName(member);
			List<MemberDefinitonTemp> deftemp = temprepo.findByMemberName(member);

			if (!definitions.isEmpty() || !relations.isEmpty()) {
				for (MemBrowseDTO updateDefinition : request.getDefinitions()) {
					for (MemberDefinitionObject definition : definitions) {
						int rowNo = definition.getRow_no();
						int seqNo = Integer.parseInt(updateDefinition.getSequeneNo());
						if (rowNo == seqNo) {
							// Update the definition
							definition.setMemberLineText(updateDefinition.getDescription());
							ddmemberobjectrepository.save(definition);
							break;
						}
					}
				}

				for (MemBrowseDTO updateRelation : request.getRelations()) {
					for (MemberRelationObject relation : relations) {
						int rowNo = relation.getRow_no();
						int seqNo = Integer.parseInt(updateRelation.getSequeneNo());
						if (rowNo == seqNo) {
							// Update the definition
							relation.setRelatedMember(updateRelation.getDescription());
							ddmemberrelationrepository.save(relation);
							break;
						}
					}
				}
			}
			if (!deftemp.isEmpty()) {
				for (MemBrowseDTO updateDefinition : request.getDefinitions()) {
					for (MemberDefinitonTemp temp : deftemp) {
						int rowNo = temp.getRow_no();
						int seqNo = Integer.parseInt(updateDefinition.getSequeneNo());
						if (rowNo == seqNo) {
							// Update the definition
							temp.setMemberLineText(updateDefinition.getDescription());
							temprepo.save(temp);
							break;
						}
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return m;
	}

//	@GetMapping("/getDBMemberBrowse/{member}")
//	public Mjson getDDMemberbrowse(@PathVariable(required = false) String member) {
//
//		return ddMemberObjectService.getDDdMemberBrowse(member);
//	}

//	@GetMapping("/getDBMember/{member}")
//	public DdMemJson getDDMemberFiles(@PathVariable(required = false) String member) {
//		return ddMemberObjectService.getDDdMemberResponse(member);
//	}

//	@GetMapping(value = "/getMemberforUpdateDataset/{member}")
//	public DbRelationCombo getDefinitionAndRelation(@PathVariable(required = false) String member) {
//		return ddMemberObjectService.getDefinitionAndRelation(member);
//	}

//	@PostMapping(value = "/range")
//	public DdMemJson getDDMemberRange(@RequestParam(value = "member") String member,
//			@RequestParam(value = "member1") String member1) {
//		return ddMemberObjectService.getDDdMemberRange(member, member1);
//	}
//
//	@PostMapping(value = "getRangeBetweenDB")
//	public MemRangeJson getDDMemberRangefromRow(@RequestParam(value = "member") String member,
//			@RequestParam(value = "member1") String member1) {
//		return ddMemberObjectService.getDDdMemberRangefromrow(member, member1);
//	}

}
