package zzyzzy.springboot.semiprojectv7.dao;


import org.springframework.stereotype.Repository;
import zzyzzy.springboot.semiprojectv7.model.Board;

import java.util.List;
import java.util.Map;

@Repository("bddao")
public class BoardDAOImpl implements BoardDAO {


    @Override
    public List<Board> selectBoard(int stbno) {
        return null;
    }

    @Override
    public List<Board> selectBoard(Map<String, Object> params) {
        return null;
    }

    @Override
    public int countBoard() {
        return 0;
    }

    @Override
    public int countBoard(Map<String, Object> params) {
        return 0;
    }

    @Override
    public int insertBoard(Board bd) {
        return 0;
    }

    @Override
    public Board selectOneBoard(String bno) {
        return null;
    }
}
