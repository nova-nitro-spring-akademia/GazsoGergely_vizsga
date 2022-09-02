package com.gergely.vizsga_0902.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, Long> {

    //        kártya becsült értéke = megjelenési évbeni érték * eltelt évek száma * 1,1;
//    (card.getIssuePrice() * (Calendar.getInstance().get(Calendar.YEAR) - card.getIssueYear())
    @Query("SELECT c FROM CardEntity c WHERE (c.issuePrice * (2022 - c.issueYear)) > :lowerBound")
    Set<CardEntity> findGreaterValuedCards(int lowerBound);

}
