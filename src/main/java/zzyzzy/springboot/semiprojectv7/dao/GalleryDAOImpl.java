package zzyzzy.springboot.semiprojectv7.dao;

import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.GalAttach;
import zzyzzy.springboot.semiprojectv7.model.Gallery;

@Repository("galdao")
public class GalleryDAOImpl implements GalleryDAO{


    @Override
    public int insertGal(Gallery gallery) {
        return 0;
    }

    @Override
    public int insertGalAttach(GalAttach ga) {
        return 0;
    }
}
