package zzyzzy.springboot.semiprojectv7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Pds;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;
import zzyzzy.springboot.semiprojectv7.repository.PdsRepository;
import zzyzzy.springboot.semiprojectv7.repository.PdsaRepository;

import java.util.HashMap;
import java.util.Map;

@Repository("pdsdao")
public class PdsDAOImpl implements PdsDAO{

    @Autowired
    PdsRepository pdsRepository;
    @Autowired
    PdsaRepository pdsaRepository;

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


}
