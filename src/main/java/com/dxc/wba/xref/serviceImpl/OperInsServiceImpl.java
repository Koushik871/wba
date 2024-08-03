package com.dxc.wba.xref.serviceImpl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.OperationalInstructions;
import com.dxc.wba.xref.repository.OperInsRepository;
import com.dxc.wba.xref.service.OperInsService;

@Service
public class OperInsServiceImpl implements OperInsService {

	@Autowired
	OperInsRepository OpRepo;

	@Override
	public List<OperationalInstructions> findByapplId(String applId) {
		return OpRepo.findByapplId(applId);
	}

	@Transactional
	@Override
	public OperationalInstructions addInstruction(OperationalInstructions operationalInstructions) {
		OpRepo.save(operationalInstructions);

		return operationalInstructions;

	}

	public List<OperationalInstructions> findByapplIdAndOpNo(String applId, String opNo) {
		return OpRepo.findByapplIdAndOpNo(applId, opNo);

	}

	@Transactional
	@Override
	public OperationalInstructions updateInstruction(OperationalInstructions operationalInstructions) {
		OperationalInstructions instructions = OpRepo.findByapplIdAndOpNos(operationalInstructions.getApplId(),
				operationalInstructions.getOpNo());
		if (Objects.nonNull(instructions)) {
			instructions.setApplId(operationalInstructions.getApplId());
			instructions.setMemberName(operationalInstructions.getMemberName());
			instructions.setApplFunc(operationalInstructions.getApplFunc());
			instructions.setValidFrom(operationalInstructions.getValidFrom());
			instructions.setValidTo(operationalInstructions.getValidTo());
			instructions.setInsOper(operationalInstructions.getInsOper());
			instructions.setOpNo(operationalInstructions.getOpNo());
			instructions.setBussinessFunc(operationalInstructions.getBussinessFunc());
			instructions.setDateLastUpdated(operationalInstructions.getDateLastUpdated());
			instructions.setMainframeDomain(operationalInstructions.getMainframeDomain());
			instructions.setOpxUser(operationalInstructions.getOpxUser());
			instructions.setTimeLastUpdated(operationalInstructions.getTimeLastUpdated());
			instructions.setRecoveryAction(operationalInstructions.getRecoveryAction());
			instructions.setSplIns(operationalInstructions.getSplIns());
			instructions.setInsOsg(operationalInstructions.getInsOsg());
			OpRepo.saveAndFlush(instructions);
		}

		return operationalInstructions;
	}

}
