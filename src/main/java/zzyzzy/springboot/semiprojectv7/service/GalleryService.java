package zzyzzy.springboot.semiprojectv7.service;

import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.model.Gallery;

import java.util.List;
import java.util.Map;

public interface GalleryService {

    Map<String, Object> newGallery(Gallery gallery);

    boolean newGalAttach(List<MultipartFile> attachs, Map<String, Object> ginfo);

    Map<String, Object> readGallery(Integer cpg);
}
