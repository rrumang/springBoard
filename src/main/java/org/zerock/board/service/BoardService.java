package org.zerock.board.service;

import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;

public interface BoardService {

    long register(BoardDTO boardDTO);

    BoardDTO readOne(long bno);

    void modify(BoardDTO boardDTO);

    void remove(long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
