package org.zerock.board.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.board.domain.Board;
import org.zerock.board.domain.BoardImage;
import org.zerock.board.dto.BoardListAllDTO;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;
    private ReplyRepository replyRepository;

    @Test
    public void testInsertWithImages() {
        Board board = Board.builder()
                .title("image test")
                .content("첨부파일테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
        }

        boardRepository.save(board);
    }

    @Test
    public void testReadWithImages() {

        // 반드시 존재해야할 bno확인
        Optional<Board> result = boardRepository.findByIdWithImages(1L);

        Board board = result.orElseThrow();

        log.info(board);

        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }

    }

    @Test
    public void testModifyImage() {
        Optional<Board> result = boardRepository.findByIdWithImages(1L);

        Board board = result.orElseThrow();

        // 기존의 첨부파일들은 삭제
        board.clearImages();

        // 새로운 첨부차일들은 추가
        for (int i = 0; i < 2; i++) {
            board.addImage(UUID.randomUUID().toString(), "updatefile" + i + ".jpg");
        }

        boardRepository.save(board);
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll() {

        Long bno = 1L;

        replyRepository.deleteByBoard_Bno(bno);

        boardRepository.deleteById(bno);

    }

    @Test
    public void testInsertAll() {
        for (int i = 1; i <= 100; i++) {
            Board board = Board.builder()
                    .title("Title.." + i)
                    .content("Content.." + i)
                    .writer("writer.." + i)
                    .build();

            for (int j = 0; j < 3; j++) {

                if (i % 5 == 0) {
                    continue;
                }
                board.addImage(UUID.randomUUID().toString(), i + "file" + j + ".jpg");
            }
            boardRepository.save(board);
        }

    }

    @Test
    @Transactional
    public void testSearchImageReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);

        log.info(result.getTotalElements());

        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
    }

}
