package org.zerock.board.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.board.domain.Board;

public interface BoardSearch {

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
