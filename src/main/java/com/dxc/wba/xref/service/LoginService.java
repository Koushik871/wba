package com.dxc.wba.xref.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.wba.xref.dbmodel.Login;
import com.dxc.wba.xref.repository.LoginRepository;

 
 
 
@Service
public class LoginService {
@Autowired
private LoginRepository repo;
  
  public Login login(String username, String password) {
  Login user = repo.findByUsernameAndPassword(username, password);
   return user;
  }
 
}