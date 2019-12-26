package com.mycompany.ap.Admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminSearchService {
	
	@Autowired
	AdminSearchRepos repos;
	
	AdminSearchEntity getOne(long id)
	{
		return repos.findOne(id);
	}
	
	List<AdminSearchEntity> findAll()
	{
		
		return repos.findAll();
	}
	
	
	void remove(long id)
	{
		
		repos.delete(id);
	}
	
	void flush()
	{
		repos.flush();
	}
	List<AdminSearchEntity> findbyName(String _username)
	{
		//return null;
		
		return repos.findByPicLike(_username);
	}
	List<AdminSearchEntity> findBySystem(String _system)
	{
		return repos.findBySystemLike(_system);
		
	}
	
	@Transactional
	AdminSearchEntity save(AdminSearchEntity Entity)
	{

		System.out.print("!");
		System.out.println(Entity.toString());
		//Entity.idx=100l;
		return repos.save(Entity);
		//repos.flush();
		//repos.updateByEntity(Entity);
		
	}
	
}

