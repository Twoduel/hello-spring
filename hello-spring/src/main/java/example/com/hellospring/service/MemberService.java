package service;

import example.com.hellospring.domain.Member;
import example.com.hellospring.repository.MemberRepository;
import example.com.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        /*Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(m ->
        {throw new IllegalStateException("이미 존재");});
        */

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재");
                });
    }

    //전체 회원 조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    //한명만 id로 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
