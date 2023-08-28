package org.zerock.ex1.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.entity.Memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;

    @Test
    @DisplayName("생성 테스트")
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    @DisplayName("insert 테스트")
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    @DisplayName("100L id select 테스트")
    public void testSelect() {
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("======================================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }
    //SQL처리 이후에 실행된 것을 볼 수 있습니다.
    //getOne()의 경우는 조금 다른 방식으로 동작
    //Transactional어노테이션이 추가로 필요

    @Transactional
    @Test
    @DisplayName("100L id select 테스트2")
    public void testSelect2() {
        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);
        System.out.println("======================================");
        System.out.println(memo);
    }

    @Test
    @DisplayName("update 테스트")
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test
    @DisplayName("delete 테스트")
    public void testDelete() {
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    @DisplayName("paging 테스트")
    public void testPageDefault() {
        //1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("-------------------------------------");
        System.out.println("Total Pages: " + result.getTotalPages()); // 총 몇 페이지
        System.out.println("Total Count: " + result.getTotalElements()); // 전체 개수
        System.out.println("Page Number: " + result.getNumber()); // 현재 페이지 번호 0부터 시작
        System.out.println("Page Size: " + result.getSize()); // 페이지당 데이터 개수
        System.out.println("has next page?: " + result.hasNext()); // 다음 페이지 존재 여부
        System.out.println("first page: " + result.isFirst()); // 시작 페이지(0) 여부
        System.out.println("-------------------------------------");
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    @DisplayName("paging 정렬 테스트")
    public void testSorts() {
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    @DisplayName("메서드 이름 between 사용 order By 적용")
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for(Memo memo : list){
            System.out.println(memo);
        }
    }

}
