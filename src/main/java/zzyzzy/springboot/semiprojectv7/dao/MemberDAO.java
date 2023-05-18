package zzyzzy.springboot.semiprojectv7.dao;

import zzyzzy.springboot.semiprojectv7.model.Member;

public interface MemberDAO {
    int selectLogin(Member m);
}
