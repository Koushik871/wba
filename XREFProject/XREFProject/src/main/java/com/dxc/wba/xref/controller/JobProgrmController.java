package com.dxc.wba.xref.controller;

import com.dxc.wba.xref.dbmodel.XrefDataset;
import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import com.dxc.wba.xref.service.JobProgmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class JobProgrmController {

    @Autowired
    JobProgmService jobProgmService;

    @GetMapping("/getAllJobPrograms")
    public ResponseEntity<Object> getAllJobPrograms() {
        //log.info("Started getAllJobPrograms() in JobProgrmController");
        try {
            List<XrefProcJobProgm> jobList = jobProgmService.getAllJobPrograms();
            return new ResponseEntity<>(jobList, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
