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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // LocalDateTime 쓰면 Hibernate가 알아서 Mapping 해준다

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum [ORDER, CANCEL]

    /** <==연관관계 편입 메소드==>
     * 양방향 연관관계가 있을 경우 다 해줘야 함
     * 양 사이드를 원자적으로 한 코드로 해주는 메소드
     * 연관의 주인은 설정은 핵심적으로 Control하는 Entity에다가
     */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // END LINE
}
