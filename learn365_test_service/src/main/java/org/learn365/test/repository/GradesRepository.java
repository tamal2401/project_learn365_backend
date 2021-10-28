package org.learn365.test.repository;

import org.learn365.modal.test.entity.GradesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends MongoRepository<GradesEntity, String> {

    @Query("{ lowerLimit : { $lt : ?0}, upperLimit : { $gte : ?0}}")
    GradesEntity getGradesFromMarks(@Param("marks") Double marks);
}
