package zzyzzy.springboot.semiprojectv7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.GalAttach;
import zzyzzy.springboot.semiprojectv7.model.Gallery;
import zzyzzy.springboot.semiprojectv7.repository.GalleryRepository;
import zzyzzy.springboot.semiprojectv7.repository.GalleryaRepository;

import java.util.HashMap;
import java.util.Map;


@Repository("galdao")
public class GalleryDAOImpl implements GalleryDAO{

    // 생정자를 이용한 스프링 빈 주입
    private final GalleryRepository galleryRepository;
    private final GalleryaRepository galleryaRepository;

    @Autowired  // 생성자를 이용해서 오토와이어를 한번만 입력하면됨
    public GalleryDAOImpl(GalleryRepository galleryRepository, GalleryaRepository galleryaRepository) {
        this.galleryRepository = galleryRepository;
        this.galleryaRepository = galleryaRepository;
    }


    @Override
    public int insertGal(Gallery gallery) {
        return Math.toIntExact(galleryRepository.save(gallery).getGno());
    }

    @Override
    public int insertGalAttach(GalAttach ga) {
        return galleryaRepository.save(ga).getGno();
    }

    @Override
    public Map<String, Object> selectGallery(Integer cpg) {
        //페이징 시 정렬 순서 지정
        Pageable paging = PageRequest.of(cpg,25, Sort.Direction.DESC,"gno");

        Map<String, Object> gals = new HashMap<>();
        // Gallery와 GalAttach를 조인해서 리스트로 가져옴
        gals.put("gallist",galleryRepository.findAllBy(paging).getContent());
        gals.put("cntpg",galleryRepository.findAllBy(paging).getTotalPages());

        return gals;
    }
}
