package blueline.blueline_spring.repository;

import blueline.blueline_spring.domain.Member;
import org.apache.el.parser.AstSetData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // 다른데서 가져다 쓸 것이 아니기 때문에 public으로 선언하지 않아도 됨
    // 기존 메모리레포지토리를 새로 만들어옴
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 실행되고 끝날 때마다 실행될 때마다 store를 clear해준다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    //@Test를 통해 아래 기능을 실행해볼 수 있음
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member); // 저장하고
        //then 검증
        Member result = repository.findById(member.getId()).get(); // optional에서 값을 받아오려면 get() 사용
        // 검증 방법1
        // Assertions.assertEquals(member, result);
        // 검증 방법2(요즘 많이 씀, 조금 더 편함)
        assertThat(result).isEqualTo(member);
    }


    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
