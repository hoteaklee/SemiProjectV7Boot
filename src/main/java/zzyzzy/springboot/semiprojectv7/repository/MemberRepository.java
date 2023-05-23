package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zzyzzy.springboot.semiprojectv7.model.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {


    //로그인 처리 1
   // Member findByUseridAndPasswd(String userid, String passwd);

    //로그인 처리2
    int countByUseridAndPasswd(String userid, String passwd);
}
