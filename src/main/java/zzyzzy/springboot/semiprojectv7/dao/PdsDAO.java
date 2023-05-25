package zzyzzy.springboot.semiprojectv7.dao;

import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;

public interface PdsDAO {
    int insertPds(Pds pds);

    int insertPdsAttach(PdsAttach pa);
}
