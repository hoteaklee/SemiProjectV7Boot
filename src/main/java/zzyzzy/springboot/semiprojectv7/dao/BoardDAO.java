package zzyzzy.springboot.semiprojectv7.dao;

import zzyzzy.springboot.semiprojectv7.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    List<Board> selectBoard(int cpage);
    List<Board> selectBoard(Map<String, Object> params);

    int countBoard();
    int countBoard(Map<String, Object> params);

    int insertBoard(Board bd);

    Board selectOneBoard(int bno);
}
