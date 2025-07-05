package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    // option + enter

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null인 경우에도 반환할 수 있다. ofNullable
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // map에서 계속 돌면서 찾으면 반환하고, 못 찾으면 null 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store.values() = member 들이 반환된다.
    }

    public void clearStore() {
        store.clear();
    }
}
