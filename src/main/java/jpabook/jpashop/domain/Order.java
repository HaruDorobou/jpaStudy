package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 관행
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    /**
     * <연관관계의 주인 정하기>
     * FK의 주인을 정하는 작업
     * -> FK가 가까운곳을 주인 Table로 정할 것
     * Member <-> Orders : FK(member_id)가 Orders에 있다
     * つまり 이 두 Table 間, 主人은 Orders임
     */

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // LocalDateTime 쓰면 Hibernate가 알아서 Mapping 해준다

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum [ORDER, CANCEL]

    // END LINE
}
