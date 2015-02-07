package com.ekiras.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ekiras.domain.Role;
import com.ekiras.domain.User;

@Controller
@RequestMapping(value={"","/**"})
public class TestController {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@RequestMapping(value="/test")
	public String test(){

	    Role role1 = new Role("ROLE_ADMIN");
	    Role role2 = new Role("ROLE_USER");
	    
	    Collection<Role> roles = new ArrayList<Role>();
	    roles.add(role1);
	    roles.add(role2);
	    
	    User user = new User();
	    user.setEmail("ekansh@ekiras.com");
	    user.setPassword("password");
	    user.setEnabled(true);
	    user.setRoles(roles);
		
	    
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    boolean result = (Long)session.save(user)!=0;
	    
	    session.getTransaction().commit();
	    session.close();
	    
	    System.out.println("User object saved = " + result);
	    
	    
		return "/home";
	}
	
}
