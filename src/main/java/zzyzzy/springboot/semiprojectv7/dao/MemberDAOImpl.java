package zzyzzy.springboot.semiprojectv7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Member;
import zzyzzy.springboot.semiprojectv7.repository.MemberRepository;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public int selectLogin(Member m) {

        return memberRepository.countByUseridAndPasswd(m.getUserid(),m.getPasswd()) ;
    }
}
