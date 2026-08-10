package com.dormify.floors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    boolean existsByNumberAndDormitoryId(int number, Long dormitoryId);
}
