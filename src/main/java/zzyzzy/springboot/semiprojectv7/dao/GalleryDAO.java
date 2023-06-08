package zzyzzy.springboot.semiprojectv7.dao;

import zzyzzy.springboot.semiprojectv7.model.GalAttach;
import zzyzzy.springboot.semiprojectv7.model.Gallery;

public interface GalleryDAO {
    int insertGal(Gallery gallery);

    int insertGalAttach(GalAttach ga);
}
