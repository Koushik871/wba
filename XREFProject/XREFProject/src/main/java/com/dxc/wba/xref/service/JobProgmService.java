package com.dxc.wba.xref.service;

import com.dxc.wba.xref.dbmodel.XrefDataset;
import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import com.dxc.wba.xref.repository.XrefProcJobProgmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobProgmService {

    @Autowired
    XrefProcJobProgmRepository xrefProcJobProgmRepository;

    public List<XrefProcJobProgm> getAllJobPrograms() throws Exception{
        List<XrefProcJobProgm> progmList = xrefProcJobProgmRepository.findAll();
        return progmList;
    }
}
