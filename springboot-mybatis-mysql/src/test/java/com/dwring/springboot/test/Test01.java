package com.dwring.springboot.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dwring.springboot.Application;
import com.dwring.springboot.mapper.UserMapper;
import com.dwring.springboot.model.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = {Application.class})  
@ActiveProfiles(value="dev") 
public class Test01 {
	
	@Autowired
    private UserMapper mapper;  
      
    @Test
    public void testInsert(){  
        User user = new User();  
        user.setUserName("����");  
        user.setAge(23);
        user.setPassword("admin");
        user.setId(3); 
 	    mapper.save(user);  
        System.out.println("�����û���Ϣ:"+user.toString());  
    } 
	@Test  
    public void testSelect(){  
        User user = mapper.selectById(3);  
        System.out.println(user);  
    }
}
