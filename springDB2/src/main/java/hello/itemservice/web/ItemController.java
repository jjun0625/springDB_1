package hello.itemservice.web;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/")
    public List<Item> home() {
        try {
            return itemService.findAllItems();
        } catch (NullPointerException e) {
            log.info("error", e);
            return null;
        }

    }

    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item) {

        return itemService.save(item);
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(
            @PathVariable long itemId,
            @RequestBody ItemUpdateDto itemUpdateDto) {
        log.info("itemId={}, itemUpdateDto={}", itemId, itemUpdateDto);
        itemService.update(itemId, itemUpdateDto);
        return "ok";
    }


    @GetMapping("/{itemId}")
    public Item item(@PathVariable long itemId) {
        return itemService.findById(itemId).get();
    }

    @PostMapping("/search")
    public List<Item> searchItems(@RequestBody ItemSearchCond itemSearchCond) {
        return itemService.findItems(itemSearchCond);
    }

}
