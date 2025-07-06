package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); 인스턴스 다른 경우

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // 외부에서 매개변수 넣도록 바꾼다
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원 x
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        // cmd + option + v =  optional로 반환, null일 수도 있기 때문에 optional로 감싼다.

        result.ifPresent(m -> { // result가 존재할 경우 예외처리
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
         */
        // 로직이 있는 경우 메소드로 뽑는다. ctrl + t

        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
