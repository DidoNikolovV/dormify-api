package com.dormify.applications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    <E> boolean existsByApplicationWindowIdAndUserEmailAndStatusNotIn(Long attr0, void attr1, Set<E> attr2);
}
