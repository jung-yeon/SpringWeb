package org.zerock.ex1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex1.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
