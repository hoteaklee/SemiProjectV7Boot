package zzyzzy.springboot.semiprojectv7.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Board;
import zzyzzy.springboot.semiprojectv7.repository.BoardRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bddao")
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Map<String, Object> selectBoard(int cpage) {
        //페이징 시 정렬 순서 지정
        //Pageable pageing = PageRequest.of(cpage,25, Sort.by("bno").descending());
        Pageable pageing = PageRequest.of(cpage,25, Sort.Direction.DESC,"bno");

        Map<String, Object> bds = new HashMap<>();
        bds.put("bdlist",boardRepository.findAll(pageing).getContent());
        bds.put("cntpg",boardRepository.findAll(pageing).getTotalPages());

        return  bds;
    }

    @Override   //검색 기능 데이타 블러오기~
    public Map<String, Object> selectBoard(Map<String, Object> params) {
        // like 검색에 대한 query method
        // findByTitleLike : %검색어% (% 문자제공 필요)
        // findByTitleContains : %검색어% (% 문자제공 필요x)
        // findByTitleStartsWith : 검색어% (% 문자제공 필요x)
        // findByTitleEndsWith : %검색어 (% 문자제공 필요x)


        String fkey = params.get("fkey").toString() ;
        String ftype = params.get("ftype").toString() ;
        int cpage = (int) params.get("stbno");

        Pageable paging = PageRequest.of(cpage,25, Sort.Direction.DESC,"bno");

        Page<Board> result = null; //결과를 담을 변수 생성

        switch (ftype) {
            case "title": //제목으로 검색
                result = boardRepository.findByTitleContains(paging, fkey); break;
            case "titcont"://제목 + 본문으로 검색
                result =boardRepository.findByTitleContainsOrContentContains(paging, fkey, fkey);break;
            case "userid"://작성자로 검색
                fkey = fkey.replace("%", ""); // %를 제거
                result =boardRepository.findByUserid(paging, fkey);break;
            case "content"://본문으로 검색
                result = boardRepository.findByContentContains(paging, fkey);
        }

        Map<String, Object> bds = new HashMap<>();
        bds.put("bdlist", result.getContent());
        bds.put("cntpg", result.getTotalPages());

        return bds;
    }

/*    @Override//게시글 총수
    public int countBoard() {
        // select ceil(count(bno)/25) from board
        int allcnt = boardRepository.countBoardBy();
        return (int)Math.ceil(allcnt/25+1);
    }*/

//    @Override
//    public int countBoard(Map<String, Object> params) {
//        String fkey = params.get("fkey").toString() ;
//        String ftype = params.get("ftype").toString() ;
//        int cnt = 0;
//        switch (ftype) {
//            case "title":  cnt = boardRepository.countByTitleContains(fkey); break;
//            case "titcont": cnt =boardRepository.countByTitleContainsOrContentContains(fkey, fkey);break;
//            case "userid": cnt =boardRepository.countByUserid(fkey);break;
//            case "content": cnt = boardRepository.countByContentContains(fkey);
//        }
//
//        return (int)Math.ceil(cnt/25+1);
//    }

    @Override
    public int insertBoard(Board bd) {
        return Math.toIntExact(boardRepository.save(bd).getBno());
    }

    @Override
    public Board selectOneBoard(int bno) {
        boardRepository.countViewBoard( bno);
        return boardRepository.findById((long) bno).get();
    }
}
