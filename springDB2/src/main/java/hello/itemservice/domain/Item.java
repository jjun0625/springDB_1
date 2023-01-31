package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "item") 이름 같으면 생략 가능

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10) //자동으로 itemName으로 바뀌기 때문에 생략 가능하다
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
