package blueline.blueline_spring.repository;

import blueline.blueline_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //시퀀스 : Key 값을 생성해주는 것

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional.ofNullable()로 감싸서 보내는 경우가 많다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //위에는 map이므로 Array로 변환해서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //루프 돌림
                .filter(member -> member.getName().equals(name)) // 넘어온 이름과 기존 정보에 있는 이름이 동일한지 필터링
                .findAny(); // 찾으면 반환
    }

    // 테스트를 할 때마다 데이터를 clear해주기 위해 해당 메소드 필요
    public void clearStore() {
        store.clear();
    }
}

