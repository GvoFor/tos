package ua.fam.tos.repository;

import ua.fam.tos.domain.boarditem.BoardItem;

import java.util.Optional;

public interface BoardItemRepository {

    long save(BoardItem item, long boardId);

    Optional<BoardItem> findById(long boardId, long itemId);

}
