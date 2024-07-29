package com.example.demo.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLoginRepository extends JpaRepository<MemberLogin, Integer> {
	@Query(value = "select * from Member_Login m where m.account=?1 and m.password=?2", nativeQuery = true)
	Optional<MemberLogin> findByAccountAndPassword(String account, String password);
	
	@Query(value="select * from Member_Login m where m.account=?1",nativeQuery=true)
	Optional<MemberLogin> findByAccount(String account);
}
