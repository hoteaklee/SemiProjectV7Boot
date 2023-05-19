package zzyzzy.springboot.semiprojectv7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zzyzzy.springboot.semiprojectv7.model.Board;
import zzyzzy.springboot.semiprojectv7.model.Zipcode;
import zzyzzy.springboot.semiprojectv7.repository.BoardRepository;
import zzyzzy.springboot.semiprojectv7.repository.ZipcodeRepository;

import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("board save")
    public void saveBoard() {
        Board b = new Board(null, "하나더~~","asd123",
                null,null,"테스츠",null);

        boardRepository.save(b);
    }


}
