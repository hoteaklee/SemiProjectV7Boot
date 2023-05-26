package zzyzzy.springboot.semiprojectv7.dao;

import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;
import zzyzzy.springboot.semiprojectv7.model.PdsReply;

import java.util.List;
import java.util.Map;

public interface PdsDAO {
    int insertPds(Pds pds);

    int insertPdsAttach(PdsAttach pa);

    Map<String, Object> selectPds(int cpg);

    Pds selectOnePds(int pno);

    PdsAttach selectOnePdsAttach(int pno);


    void countDownload(int pno);

    List<String> selectFtype();

    List<PdsReply> selectPdsReply(int pno);
}
