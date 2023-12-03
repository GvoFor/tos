package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.Board;
import ua.fam.tos.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository repository;

    public BoardService(BoardRepository repository) {
        this.repository = repository;
    }

    public List<Board> getAllBoards() {
        return repository.findAll();
    }

    public List<Board> getAllBoardsByUsername(String username) {
        return repository.findAllByUsername(username);
    }

    public Optional<Board> getBoardById(long id) {
        return repository.findById(id);
    }

    public void saveBoard(Board board) {
        repository.save(board);
    }

    public void deleteItemById(long boardId, long itemId) {
        repository.findById(boardId).ifPresent(board -> {
            board.deleteItemById(itemId);
            repository.save(board);
        });
    }
}
