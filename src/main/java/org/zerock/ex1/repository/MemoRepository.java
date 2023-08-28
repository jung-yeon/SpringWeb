package org.zerock.ex1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex1.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // select를 하는 작업이라면 List타입이나 배열을 이요할 수 있다
    // 파라미터에 Pageable타입을 넣는 경우에는 무조건 Page<E> 타입
    //ex) Memo의 객체의 mno값이 70부터 80사이의 객체들을 구하고 mno의 역순으로 정렬하고 싶다면 다음과 같은 메서드를 MemoRepository 인터페이스에 추가
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

}
