package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.IdentificationNumberModel;
import org.example.exampleapp.service.IdentificationNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Identification")
@RequiredArgsConstructor
public class IdentificationNumberController {

    private final IdentificationNumberService identificationNumberService;

    @PostMapping("/saveIdentification")
    public ResponseEntity<IdentificationNumberModel> identificationNumber(@RequestBody IdentificationNumberModel identificationNumberModel) {
        try {
            identificationNumberService.saveIdentificationNumber(identificationNumberModel);
            return ResponseEntity.ok().body(identificationNumberModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }
}
