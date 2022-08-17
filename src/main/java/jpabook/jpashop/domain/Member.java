package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    /**
     * Member Class Info
     * JPA Entity
     * private id, username
     * H2 Table Member
     */

    @Id @GeneratedValue
    @Column(name = "member_id") // PK Setting
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    /**
     *     order 테이블에 있는 Member 필드에 의해 mapping한다
     *     '나는 Mapping된 거울인 뿐이다.' 라는 의미를 가진다
     *     그래서 지금 이 친구를 바꾼다고 order 테이블의 FK의 값이 바뀌지 않는다
     */
    private List<Order> orders = new ArrayList();


    // END LINE
}
