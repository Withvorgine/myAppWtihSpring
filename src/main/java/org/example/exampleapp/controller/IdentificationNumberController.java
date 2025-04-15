package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.IdentityCard;
import org.example.exampleapp.repository.IdentificationNumberRepository;
import org.example.exampleapp.service.IdentificationNumberService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Identification")
@RequiredArgsConstructor
public class IdentificationNumberController {

    private final IdentificationNumberService identificationNumberService;
    private final IdentificationNumberRepository identificationNumberRepository;

    @PostMapping("/saveIdentification")
    public ResponseEntity<IdentityCard> identificationNumber(@RequestBody IdentityCard identityCard) {
        try {
            identificationNumberService.saveIdentificationNumber(identityCard);
            return ResponseEntity.ok().body(identityCard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping()
    public List<IdentityCard> getIdentificationNumberByName(
            @RequestParam(required = false) String name,
            @RequestParam (defaultValue = "createdDate")  String sortBy,
            @RequestParam (defaultValue = "desc") String orderBy
    ) {
        Sort sort = Sort.by(
                orderBy.equalsIgnoreCase("desc") ? Sort.Order.desc(sortBy) : Sort.Order.asc(orderBy)
        );
        if (name != null) {
            return identificationNumberRepository.findByName(name,sort);
        }
        return identificationNumberRepository.findAll(sort);
    }
}
