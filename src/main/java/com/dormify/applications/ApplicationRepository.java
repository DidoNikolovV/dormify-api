package com.dormify.applications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByApplicationWindowIdAndUserEmailAndStatusNotIn(Long windowId, String email, Set<ApplicationStatus> statuses);

}
