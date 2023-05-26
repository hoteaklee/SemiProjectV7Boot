package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import zzyzzy.springboot.semiprojectv7.model.PdsReply;

import java.util.List;

public interface PdsReplyRepository extends JpaRepository<PdsReply, Long> {
    //@Query("from PdsReply where pno = :pno order by refno asc")
    List<PdsReply> findByPnoOrderByRefnoAscRegdateAsc(int pno);

    @Modifying
    @Transactional
    @Query("update PdsReply set refno = :rpno where rpno = :rpno")
    void updateRefno(@Param("rpno") int rpno);
}
