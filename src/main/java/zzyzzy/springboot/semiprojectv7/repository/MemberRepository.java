package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zzyzzy.springboot.semiprojectv7.model.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(" select count(userid) from Member where userid = ?1")
    List<Member> selectOneUid(String userid);

}
