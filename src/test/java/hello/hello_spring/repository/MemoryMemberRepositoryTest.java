package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test를 진행하면 db에 값이 계속 저장되어서, 오류가 발생한다. 따라서 test가 끝날 때마다 clear해줘야 한다.
    // afterEach()가 없으면 test가 의존적이지 못하기 때문에 오류가 발생
    @AfterEach
    public void afterEach() { // 메소드가 끝날 때마다 호출한다.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // result와 member가 같은지 확인해줄 수 있는 Assertions
        // Assertions.assertEquals(member, result);

        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
          Member member1 = new Member();
          member1.setName("spring1");
          repository.save(member1);

          Member member2 = new Member();
          member2.setName("spring2");
          repository.save(member2);

          Member result = repository.findByName("spring1").get();

          Assertions.assertThat(result).isEqualTo(member1 );
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
