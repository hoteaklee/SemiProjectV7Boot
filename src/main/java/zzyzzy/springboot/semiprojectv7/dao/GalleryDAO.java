package zzyzzy.springboot.semiprojectv7.dao;

import zzyzzy.springboot.semiprojectv7.model.GalAttach;
import zzyzzy.springboot.semiprojectv7.model.Gallery;

import java.util.Map;

public interface GalleryDAO {
    int insertGal(Gallery gallery);

    int insertGalAttach(GalAttach ga);

    Map<String, Object> selectGallery(Integer cpg);

    Object selectOneGallery(int gno);
}
