package jee.ensas.accountservice.daos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "accounts")
@Data
public class Account {

	@Id
	private String id;

	private String accountNumber;
	private double balance;
	private EType Type;
	private String currency;
	private boolean isDeleted = false;
	private boolean isEnabled = false;
	private boolean isAccepted = false;
	private boolean isPrincipal = false;
	private String agencyId;

	@LastModifiedDate
	private Date creationDate;

	private Set<String> cardIds = new HashSet<>();
	private String userId;

}
