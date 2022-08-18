package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /**
     * Constructor Injection
     * Param em
     * @RequiredArgsConstructor -> final param 생성자 자동생성
     */
    private final EntityManager em;

    // member 저장
    public void save(Member member) {
        em.persist(member);
    }

    // member 조회(by id)
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // member List 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                    .getResultList();
        /**
         * SQL과 다른 JPQL임
         * Entity 객체를 대상으로한 Query
         */
    }

    // member List 조회(by name)
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
    // END LINE
}
