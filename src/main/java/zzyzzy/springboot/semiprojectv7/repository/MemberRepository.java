package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zzyzzy.springboot.semiprojectv7.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
