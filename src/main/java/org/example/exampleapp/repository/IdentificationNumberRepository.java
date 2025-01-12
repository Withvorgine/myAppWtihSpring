package org.example.exampleapp.repository;


import lombok.NonNull;
import org.example.exampleapp.model.IdentificationNumberModel;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;

public interface IdentificationNumberRepository extends MongoRepository<IdentificationNumberModel,String> {
    IdentificationNumberModel findByIdentificationNumber(String identificationNumber);

    @NonNull
    Optional<IdentificationNumberModel> findById(String id);

}
