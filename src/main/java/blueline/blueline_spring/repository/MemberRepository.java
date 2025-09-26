package blueline.blueline_spring.repository;

import blueline.blueline_spring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 멤버 저장
    // Optional: 들어온 null을 그대로 처리하는 방식이 아니라 optional로 감싸서 반환
    Optional<Member> findById(Long id); // 아이디로 회원 찾기
    Optional<Member> findByName(String name); //이름으로 회원 찾기
    List<Member> findAll(); //모든 회원 리스트 반환
}
