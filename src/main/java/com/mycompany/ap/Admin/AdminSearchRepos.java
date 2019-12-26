package com.mycompany.ap.Admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;;

public interface AdminSearchRepos extends JpaRepository<AdminSearchEntity, Long>
{

	//@Query(value ="Select pic, company, contact, system from where SearchEntity where system = :system", nativeQuery = true)
	@Query(value ="Select a from AdminSearchEntity a where a.system like  CONCAT('%', :system, '%')  order by a.idx")
	List<AdminSearchEntity> findBySystemLike(@Param("system") String system);
	
	@Query(value ="Select a from AdminSearchEntity a where a.pic like  CONCAT('%', :pic, '%')  order by a.idx")
	List<AdminSearchEntity> findByPicLike(@Param("pic") String pic);
	
	List<AdminSearchEntity> findByPic(@Param("pic") String pic);

	@Override
	@Transactional
	@Modifying
	AdminSearchEntity save(AdminSearchEntity Entity);
	
	@Transactional
    @Modifying
    void delete(Long id);
	
	//@Modifying
	/*
	 * @Query("UPDATE SearchEntity a " +
	 * "SET a.company=:entity.company, a.pic=:entity.pic, a.contact=:entity.contact, a.system=:entity.system"
	 * + "where a.idx=:entity.idx") void updateByEntity(@Param("entity")
	 * SearchEntity entity);
	 */
	
	/* @Query("ucode:200 message: error:SyntaxError: 구문 오류pdate Customer c set c.name = :name WHERE c.id = :customerId")
	 * @Query("UPDATE Question q set q.showCount = q.showCount + 1 where q.questionId = :questionId")
		void updateShowCount(@Param("questionId") Long questionId);
	 * @Modifying // update , delete Query
	 * 
	 * @Query(
	 * value="update User u set u.name = :#{#user.name} WHERE u.no = :#{#user.no}",
	 * nativeQuery=false) Integer update(@Param("user") User user );
	 */
}
