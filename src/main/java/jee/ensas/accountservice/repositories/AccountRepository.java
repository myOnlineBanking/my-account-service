package jee.ensas.accountservice.repositories;

import jee.ensas.accountservice.daos.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByUserId(@Param(value = "userId") String userId);
    Account findByAccountNumber(@Param(value = "accountNumber") String accountNumber);
    List<Account> findByAgencyId(@Param(value = "agencyId") String agencyId);
}
