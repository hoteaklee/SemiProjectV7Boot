package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import zzyzzy.springboot.semiprojectv7.model.Gallery;
import zzyzzy.springboot.semiprojectv7.model.Pds;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
//    @Modifying
//    @Transactional
//    @Query("update Gallery set views = views +1 where gno = :gno")
//    void countViewById(@Param("pno") long gno);  //@Param("gno") => :gno

}
