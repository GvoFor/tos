package ua.fam.tos.service;

import ua.fam.tos.domain.Board;
import ua.fam.tos.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

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
}
