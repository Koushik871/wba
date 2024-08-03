package com.dxc.wba.xref.sso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

//	List<UserAccess> findByUsername(String username);
	List<UserAccess> findByUsername(String username);

	

}
