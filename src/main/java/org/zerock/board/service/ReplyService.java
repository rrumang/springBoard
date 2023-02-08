package org.zerock.board.service;

import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.ReplyDTO;

public interface ReplyService {

    long register(ReplyDTO replyDTO);

    ReplyDTO read(long rno);

    void modify(ReplyDTO replyDTO);

    void remove(long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(long bno, PageRequestDTO pageRequestDTO);
}
