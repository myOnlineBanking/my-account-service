package jee.ensas.accountservice.dtos;

import jee.ensas.accountservice.daos.EType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable {

    private String id;
    private String userId;

    private String accountNumber;
    private double balance;
    private EType Type;
    private String currency;
    private boolean isDeleted = false;
    private boolean isEnabled = false;
    private boolean isAccepted = false;
    private Date creationDate;
    private boolean isPrincipal = false;
    private String agencyId;
    private Set<String> cardIds = new HashSet<>();


}

