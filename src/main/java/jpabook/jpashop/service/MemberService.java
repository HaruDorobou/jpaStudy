package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
/**
 * 특별한 명시 없다면 @Transactional(readOnly = true)
 */
@RequiredArgsConstructor
public class MemberService {

    /**
     * Constructor Injection
     * param memberRepository
     * @RequiredArgsConstructor -> final param 생성자 자동생성
     */
    private final MemberRepository memberRepository;

    // 회원 가입, Default readOnly : false
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증 절차
    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } // if close
    }

    // 회원 전체 조회 public readOnly : True
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    // 회원 한 건 조회 public readOnly : True
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // END LINE
}
