package com.facebook.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facebook.community.model.Supporter;
import com.facebook.community.repository.SupporterRepository;

        
@Service

  // to add auto-commit feature
@Transactional

public class SupporterService {

	@Autowired
	SupporterRepository supporterRepository;
	
	public Supporter getSupporter(String id){
            return supporterRepository.findOne(id);
         }
	
	public List<Supporter> getSupporters(){
        return supporterRepository.findAll();
     }
	
	
	public void addSupporter(Supporter supporter){
		         supporterRepository.save(supporter);
	}
}
