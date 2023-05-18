package zzyzzy.springboot.semiprojectv7.service;

import zzyzzy.springboot.semiprojectv7.model.Member;

import javax.servlet.http.HttpSession;

public interface MemberService {
    boolean checkLogin(Member m, HttpSession sess);
}
