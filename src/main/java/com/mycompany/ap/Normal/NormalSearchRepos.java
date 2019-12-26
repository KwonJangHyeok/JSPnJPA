package com.mycompany.ap.Normal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;;

public interface NormalSearchRepos extends JpaRepository<NormalSearchEntity, Long>
{
	//@Query(value ="Select pic, company, contact, system from where SearchEntity where system = :system", nativeQuery = true)
	@Query(value ="Select a from NormalSearchEntity a where a.system like  CONCAT('%', :system, '%')  ")
	List<NormalSearchEntity> findBySystemLike(@Param("system") String system);
	
	@Query(value ="Select a from NormalSearchEntity a where a.pic like  CONCAT('%', :pic, '%')  ")
	List<NormalSearchEntity> findByPicLike(@Param("pic") String pic);
	List<NormalSearchEntity> findByPic(@Param("pic") String pic);
}
