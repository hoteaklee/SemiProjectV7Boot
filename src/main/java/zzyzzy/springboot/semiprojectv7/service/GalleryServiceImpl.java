package zzyzzy.springboot.semiprojectv7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.dao.GalleryDAO;
import zzyzzy.springboot.semiprojectv7.model.GalAttach;
import zzyzzy.springboot.semiprojectv7.model.Gallery;
import zzyzzy.springboot.semiprojectv7.utils.GalleryUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("galsrv")
public class GalleryServiceImpl implements GalleryService{

    @Autowired
    GalleryDAO galdao;
    @Autowired
    GalleryUtils galutils;
    @Override
    public Map<String, Object> newGallery(Gallery gallery) {
        gallery.setUuid( galutils.makeUUID() );
        int gno = galdao.insertGal(gallery);

        Map<String, Object> ginfo = new HashMap<>();
        ginfo.put("gno", gno);
        ginfo.put("uuid", gallery.getUuid());

        return ginfo;
    }

    @Override
    public boolean newGalAttach(List<MultipartFile> attachs, Map<String, Object> ginfo) {
        //이미지 파일 저장
        GalAttach ga = galutils.processUpload(attachs, ginfo);
        // 썸네일 이미지 생성
        galutils.makeThumbail(ga,ginfo.get("uuid"));
        // 첨부정보 디비 저장
        int gano = galdao.insertGalAttach(ga);

        return (gano > 0) ? true : false;

    }
}
