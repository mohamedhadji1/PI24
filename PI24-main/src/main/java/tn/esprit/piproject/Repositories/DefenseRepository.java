package tn.esprit.piproject.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Defense;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface DefenseRepository extends MongoRepository<Defense, Integer> {

    //@Query("{'dateDefense' : {$lt : ?0}}")
    //List<Defense> findOldDefenses(Date date);
    List<Defense> findByDateDefenseBefore(LocalDateTime date);


}