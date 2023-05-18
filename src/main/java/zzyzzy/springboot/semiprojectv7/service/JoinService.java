package zzyzzy.springboot.semiprojectv7.service;

import zzyzzy.springboot.semiprojectv7.model.Member;

public interface JoinService {

    String findZipcode(String dong);

    boolean newMember(Member m);

    int checkUserid(String uid);

    boolean loginMember(Member m);

}
