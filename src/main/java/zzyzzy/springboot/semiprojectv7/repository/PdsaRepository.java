package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;

import java.util.Map;

public interface PdsaRepository extends JpaRepository<PdsAttach, Long> {
    PdsAttach findByPno(int pno);


    @Modifying
    @Transactional
    @Query("update PdsAttach set fdown = fdown +1 where pno = :pno")
    void countDownByPno(@Param("pno") int pno);
}
