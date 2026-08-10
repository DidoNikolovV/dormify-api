package com.dormify.floors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    boolean existsByNumberAndDormitoryId(int number, Long dormitoryId);

    Page<Floor> findByDormitoryId(Long dormitoryId, Pageable pageable);
}
