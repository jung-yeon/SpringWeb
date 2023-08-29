package org.zerock.ex1.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex1.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // select를 하는 작업이라면 List타입이나 배열을 이용할 수 있다
    // 파라미터에 Pageable타입을 넣는 경우에는 무조건 Page<E> 타입
    //ex) Memo의 객체의 mno값이 70부터 80사이의 객체들을 구하고 mno의 역순으로 정렬하고 싶다면 다음과 같은 메서드를 MemoRepository 인터페이스에 추가
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // 목록을 원하는 쿼리를 실행해야한다면 대부분의 경우 orderby키워드 등을 사용해야 하기때문에 혼동하기 쉬움
    // pageable 파라미터를 같이 결합해서 사용하여 좀 더 간단한 형태의 메서드 선언이 가능해짐
    // pageable 파라미터는 모든 쿼리 메서드에 적용할수 있음
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable0);

    // deleteBy로 시작하는 삭제처리
    void deleteMemoByMnoLessThan(Long num);

}
