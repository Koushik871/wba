package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.dbmodel.XrefHelp;

@Repository
public interface XrefHelpRepository extends JpaRepository<XrefHelp, Long> {

	List<XrefHelp> findByScreenName(String screenName);

}
