package zzyzzy.springboot.semiprojectv7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;
import zzyzzy.springboot.semiprojectv7.model.PdsReply;
import zzyzzy.springboot.semiprojectv7.repository.PdsReplyRepository;
import zzyzzy.springboot.semiprojectv7.repository.PdsRepository;
import zzyzzy.springboot.semiprojectv7.repository.PdsaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("pdsdao")
public class PdsDAOImpl implements PdsDAO{

    @Autowired
    PdsRepository pdsRepository;
    @Autowired
    PdsaRepository pdsaRepository;

    @Autowired
    PdsReplyRepository pdsReplyRepository;


    @Override
    public int insertPds(Pds pds) {
        // 제목, 작성자, 본문을 pds테이블에 저장한 뒤
        // 저장시 생성된 Pno를 리턴함 = pdsattach 저장시 사용
        return Math.toIntExact(pdsRepository.save(pds).getPno());
    }

    @Override
    public int insertPdsAttach(PdsAttach pa) {
        return Math.toIntExact(pdsaRepository.save(pa).getPano());
    }

    @Override
    public Map<String, Object> selectPds(int cpg) {
        //페이징 시 정렬 순서 지정
        Pageable paging = PageRequest.of(cpg,25, Sort.Direction.DESC,"pno");

        Map<String, Object> pds = new HashMap<>();
        pds.put("pdslist",pdsRepository.findAll(paging).getContent());
        pds.put("cntpg",pdsRepository.findAll(paging).getTotalPages());


        return pds;
    }

    @Override
    public Pds selectOnePds(int pno) {
        pdsRepository.countViewById(pno); // 조회수 증가

        return pdsRepository.findById((long) pno).get();
    }

    @Override
    public PdsAttach selectOnePdsAttach(int pno) {
        return pdsaRepository.findByPno( pno);
    }

    @Override
    public void countDownload(int pno) {
        pdsaRepository.countDownByPno(pno);
    }

    @Override
    public List<String> selectFtype() {
        return pdsaRepository.findByFtypes();
    }

    @Override
    public List<PdsReply> selectPdsReply(int pno) {
        return pdsReplyRepository.findByPnoOrderByRefnoAscRegdateAsc(pno);
        //pno로 조회하고 refno, regdate 정렬
    }

    @Override
    public int insertPdsReply(PdsReply reply) {
        //댓글 저장시 생성된 댓글번호로
        // refno에도 저정하기 위해서 update문을 한번 더 호출

        PdsReply p = pdsReplyRepository.save(reply) ;
        int rpno = Math.toIntExact(p.getRpno());

        pdsReplyRepository.updateRefno(rpno);

        return rpno;
    }

    @Override
    public int insertPdsRreply(PdsReply reply) {
        // 대댓글 저장시 링크로 이미 전달받은 댓글번호
        // refno에 대입됨 - 댓글 저장과는 달리 바로 처리
        long rpno = pdsReplyRepository.save(reply).getRpno();

        return (int) rpno;
    }


}
