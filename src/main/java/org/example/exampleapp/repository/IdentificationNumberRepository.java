package org.example.exampleapp.repository;


import lombok.NonNull;
import org.example.exampleapp.model.IdentityCard;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;



import java.util.List;
import java.util.Optional;

public interface IdentificationNumberRepository extends MongoRepository<IdentityCard,String> {
    IdentityCard findByIdentificationNumber(String identificationNumber);

    @NonNull
    Optional<IdentityCard> findById(String id);

    List<IdentityCard> findByName(String name, Sort sort);

}
