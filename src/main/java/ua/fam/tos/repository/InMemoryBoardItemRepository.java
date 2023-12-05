package ua.fam.tos.repository;

import org.springframework.stereotype.Repository;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.boarditem.BoardItem;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBoardItemRepository implements BoardItemRepository {

    private long counter = 1;
    private final BoardRepository boardRepository;

    public InMemoryBoardItemRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public long save(BoardItem item, long boardId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) {
            return -1;
        }
        Board board = boardOptional.get();

        Optional<BoardItem> existedOptional = board.getItemById(item.getId());
        if (existedOptional.isPresent()) {
            List<BoardItem> items = board.getAllItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId() == item.getId()) {
                    items.set(i, item);
                    return item.getId();
                }
            }
        }

        item.setId(counter++);
        board.addItem(item);
        return item.getId();
    }

    @Override
    public Optional<BoardItem> findById(long boardId, long itemId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) {
            return Optional.empty();
        }
        Board board = boardOptional.get();
        return board.getItemById(itemId);
    }
}
