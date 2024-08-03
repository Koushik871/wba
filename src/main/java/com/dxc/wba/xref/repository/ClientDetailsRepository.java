package com.dxc.wba.xref.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.wba.xref.login.ClientCredentials;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientCredentials, Long> {

	ClientCredentials findByServerName(String servername);

}
