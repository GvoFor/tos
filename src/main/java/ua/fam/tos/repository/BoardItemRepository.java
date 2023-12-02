package ua.fam.tos.repository;

import ua.fam.tos.domain.boarditem.BoardItem;

public interface BoardItemRepository {

    long save(BoardItem item, long boardId);

}
