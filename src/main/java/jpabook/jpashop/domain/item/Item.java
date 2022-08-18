package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    /**
     * 부모 클래스에 상속 전략을 설정
     * (strategy = InheritanceType.SINGLE_TABLE)
     */
@DiscriminatorColumn(name="dtype")
@Getter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //<==Business Logic==>//
    /**
     * Stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity = quantity;
    }

    /**
     * Stock 감소
     */
    public void decreaseStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) { // Check minus stock quantity
            throw new NotEnoughStockException("Need more stock (minus stock)"); // Custom exception
        }
        this.stockQuantity = restStock;
    }

    // END LINE
}
