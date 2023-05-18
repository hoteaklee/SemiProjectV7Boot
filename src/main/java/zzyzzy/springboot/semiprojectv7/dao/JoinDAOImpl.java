package zzyzzy.springboot.semiprojectv7.dao;


import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Member;
import zzyzzy.springboot.semiprojectv7.model.Zipcode;

import java.util.List;

@Repository("jndao")
public class JoinDAOImpl implements JoinDAO {


    @Override
    public List<Zipcode> selectZipcode(String dong) {
        return null;
    }

    @Override
    public int insertMember(Member m) {
        return 0;
    }

    @Override
    public int selectOneUserid(String uid) {
        return 0;
    }

    @Override
    public int selectOneMember(Member m) {
        return 0;
    }
}
