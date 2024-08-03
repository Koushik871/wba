package com.dxc.wba.xref.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.ddentity.MemberCategory;

@Repository
public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {

}
