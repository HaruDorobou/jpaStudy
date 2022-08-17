package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    /**
     * Collection 관계를 풀어내야 하기 위함
     * 중간 Table이 존재
     *
     * 실전에선 쓰지마세요
     * Field 추가 불가 -> Table을 건드려야하니까
     * 구조가 매우 경직됨
     */
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child;
    /**
     * 같은 Entity에 관해서 Self로 양방향 join을 건 상태
     * parent <-> child
     */

    /** <==연관관계 편입 메소드==>
     * 양방향 연관관계가 있을 경우 다 해줘야 함
     * 양 사이드를 원자적으로 한 코드로 해주는 메소드
     * 연관의 주인은 설정은 핵심적으로 Control하는 Entity에다가
     */
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    // END LINE
}
