package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.jpa.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemJpaRepository itemJpaRepository;

    @Override
    @Transactional
    public Item save(Item item) {
//        return itemRepository.save(item);
        return itemJpaRepository.save(item);
    }

    @Transactional
    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
//        itemRepository.update(itemId, updateParam);
        itemJpaRepository.updateItem(itemId, updateParam.getItemName(), updateParam.getPrice(), updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
//        return itemRepository.findById(id);
        return itemJpaRepository.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
//        return itemJpaRepository.findItems(cond.getItemName(), cond.getMaxPrice());
        return itemJpaRepository.findAll();
    }

    public List<Item> findItemsWithCond(ItemSearchCond itemSearch) {
        return itemJpaRepository.findItems(itemSearch.getItemName(), itemSearch.getMaxPrice());
    }
}
