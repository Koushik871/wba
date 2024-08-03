package com.dxc.wba.xref.repository;

import com.dxc.wba.xref.dbmodel.XrefProcJobProgm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XrefProcJobProgmRepository extends JpaRepository<XrefProcJobProgm,Long> {
}
