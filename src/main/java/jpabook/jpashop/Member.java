package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private Long id;
    private String username;

    // END LINE
}
