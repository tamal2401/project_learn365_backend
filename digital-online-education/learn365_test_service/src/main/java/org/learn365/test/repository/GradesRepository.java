package org.learn365.test.repository;

import org.learn365.test.entity.GradesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends JpaRepository<GradesEntity, Long> {

    @Query("select g.grade from GradesEntity g where g.lowerLimit<:marks and g.upperLimit>=:marks")
    String getGradesFromMarks(@Param("marks") Float marks);
}
