package tn.esprit.piproject.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.piproject.Entities.Defence;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DefenceRepository extends MongoRepository<Defence, Integer> {

    List<Defence> findByDateDefenseBefore(LocalDateTime date);

}