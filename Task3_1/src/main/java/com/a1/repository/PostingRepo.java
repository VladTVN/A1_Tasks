package com.a1.repository;


import com.a1.model.Login;
import com.a1.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingRepo extends JpaRepository<Posting, Long> {
    @Query(value = "select * from posting where MONTH(doc_date) = ?",nativeQuery = true)
    List <Posting> findByMonth(String month);

    @Query(value = "select * from posting where DAY(doc_date) = ?",nativeQuery = true)
    List <Posting> findByDay(String day);

    @Query(value = "select * from posting where YEAR (doc_date) = ?",nativeQuery = true)
    List <Posting> findByYear(String year);

    @Query(value = "select * from posting where MONTH (doc_date) >= ?1 and MONTH (doc_date) <= ?2",nativeQuery = true)
    List <Posting> findByQuarter(String startMonth, String endMoth);

}
