package ru.example.hibernateRepeatable.item;

import java.util.List;

interface ItemService {
    List<ItemDto> getItems(long userId);

    ItemDto addNewItem(long userId, ItemDto itemDto);

    void deleteItem(long userId, long itemId);
}
