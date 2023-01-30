package hello.itemservice.web;

import hello.itemservice.domain.Item;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor

public class HomeController {

    ItemService itemService;


//    @GetMapping("/")
//    public List<Item> home() {
//        try {
//            return itemService.findAllItems();
//        } catch (NullPointerException e) {
//            log.info("error", e);
//            return null;
//        }
//
//    }
//
//    @PostMapping("/addItem")
//    public Item addItem(@RequestBody Item item) {
//
//        return itemService.save(item);
//    }
}
