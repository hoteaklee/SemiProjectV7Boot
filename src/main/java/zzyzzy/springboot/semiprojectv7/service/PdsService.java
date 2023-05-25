package zzyzzy.springboot.semiprojectv7.service;

import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.model.Pds;

import java.util.Map;

public interface PdsService {

    Map<String, Object> newPds(Pds pds);
    boolean newPdsAttach(MultipartFile attach, Map<String, Object> pinfo);
}
