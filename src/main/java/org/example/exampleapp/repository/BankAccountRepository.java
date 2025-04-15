package org.example.exampleapp.repository;

import org.example.exampleapp.model.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccountModel, UUID> {
        BankAccountModel findById(String id);

        List<BankAccountModel> findByIban(String iban);

        @Modifying
        @Query(value = "UPDATE BankAccountModel b SET b.amount =:amount where b.iban=:iban")
        void transfer(@Param("amount") BigDecimal amount, @Param("iban") String iban);
}
