package zzyzzy.springboot.semiprojectv7.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Board;
import zzyzzy.springboot.semiprojectv7.repository.BoardRepository;

import java.util.List;
import java.util.Map;

@Repository("bddao")
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Board> selectBoard(int cpage) {
        //페이징 시 정렬 순서 지정
        //Pageable pageing = PageRequest.of(cpage,25, Sort.by("bno").descending());
        Pageable pageing = PageRequest.of(cpage,25, Sort.Direction.DESC,"bno");

        return  boardRepository.findAll(pageing).getContent();
    }

    @Override   //검색 기능 데이타 블러오기~
    public List<Board> selectBoard(Map<String, Object> params) {
        String fkey = '%' +  params.get("fkey").toString() + '%';
        String ftype = params.get("ftype").toString() ;
        int cpage = (int) params.get("stbno");

        Pageable paging = PageRequest.of(cpage,25, Sort.Direction.DESC,"bno");

        List<Board> result = null; //결과를 담을 변수 생성

        switch (ftype) {
            case "title": //제목으로 검색
                result = boardRepository.findByTitleLike(paging, fkey); break;
            case "titcont"://제목 + 본문으로 검색
                result =boardRepository.findByTitleOrContentLike(paging, fkey, fkey);break;
            case "userid"://작성자로 검색
                fkey = fkey.replace("%", ""); // %를 제거
                result =boardRepository.findByUserid(paging, fkey);break;
            case "content"://본문으로 검색
                result = boardRepository.findByContentLike(paging, fkey);
        }
        return result;
    }

    @Override//게시글 총수
    public int countBoard() {
        // select ceil(count(bno)/25) from board
        int allcnt = boardRepository.countBoardBy();
        return (int)Math.ceil(allcnt/25);
    }

    @Override
    public int countBoard(Map<String, Object> params) {
        return 0;
    }

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
