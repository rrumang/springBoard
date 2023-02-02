package org.zerock.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.domain.Board;
import org.zerock.board.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}
