package ru.example.hibernateRepeatable.item;

import java.util.List;

public interface ItemRepository {
    List<Item> findByUserId(long userId);

    Item save(Item item);

    void deleteByUserIdAndItemId(long userId, long itemId);
}
