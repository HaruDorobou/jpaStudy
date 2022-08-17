package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 어딘가에 내장될 수 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // Protected
        /**
         * 값 타입은 변경 불가능하게 해야 함
         */
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // END LINE
}
