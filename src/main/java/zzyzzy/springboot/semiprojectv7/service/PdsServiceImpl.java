package zzyzzy.springboot.semiprojectv7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.dao.PdsDAO;
import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.utils.PdsUtils;

@Service("pdssrv")
public class PdsServiceImpl implements PdsService{

    @Autowired PdsDAO pdsdao;
    @Autowired
    PdsUtils pdsUtils;

    @Override
    public int newPds(Pds pds) {
        pds.setUuid(pdsUtils.makeUUID());
        return pdsdao.insertPds(pds);
    }

    @Override
    public boolean newPdsAttach(MultipartFile attach, int pno) {
        return true;// 일단 잘넘어가도록 true해줌
    }
}
