package com.dxc.wba.xref.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.OperationalInstructions;

@Service
public interface OperInsService {

	public List<OperationalInstructions> findByapplId(String applId);

	public OperationalInstructions addInstruction(OperationalInstructions applId);

	public List<OperationalInstructions> findByapplIdAndOpNo(String applId, String opNo);

	public OperationalInstructions updateInstruction(OperationalInstructions operationalInstructions);

}
