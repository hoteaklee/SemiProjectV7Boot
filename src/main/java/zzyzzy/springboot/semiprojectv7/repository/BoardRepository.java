package zzyzzy.springboot.semiprojectv7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zzyzzy.springboot.semiprojectv7.model.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {




}
