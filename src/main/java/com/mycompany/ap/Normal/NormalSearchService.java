package com.mycompany.ap.Normal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NormalSearchService {
	
	@Autowired
	NormalSearchRepos repos;
	
	NormalSearchEntity getOne(long id)
	{
		return repos.findOne(id);
	}
	
	List<NormalSearchEntity> findAll()
	{
		
		return repos.findAll();
	}
	
	List<NormalSearchEntity> findbyName(String _username)
	{
		//return null;
		return repos.findByPicLike(_username);
	}
	List<NormalSearchEntity> findBySystem(String _system)
	{
		return repos.findBySystemLike(_system);
		
	}
	
}

