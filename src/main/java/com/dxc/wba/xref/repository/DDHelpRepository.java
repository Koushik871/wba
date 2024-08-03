package com.dxc.wba.xref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.DDHelp;

@Repository
public interface DDHelpRepository extends JpaRepository<DDHelp, Long> {

	List<DDHelp> findByScreenName(String screenName);

}
