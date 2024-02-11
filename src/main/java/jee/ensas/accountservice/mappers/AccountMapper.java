package jee.ensas.accountservice.mappers;

import jee.ensas.accountservice.daos.Account;
import jee.ensas.accountservice.dtos.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMapper {

    public AccountDto map(Account account) {

        AccountDto accountDto = new AccountDto();

        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccepted(account.isAccepted());
        accountDto.setPrincipal(account.isPrincipal());
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setDeleted(account.isDeleted());
        accountDto.setEnabled(account.isEnabled());
        accountDto.setCreationDate(account.getCreationDate());
        accountDto.setType(account.getType());
        accountDto.setId(account.getId());
        accountDto.setCardIds(account.getCardIds());
        accountDto.setUserId(account.getUserId());

        return accountDto;
    }

    public Account map(AccountDto accountDto) {

        Account account = new Account();

        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccepted(accountDto.isAccepted());
        account.setPrincipal(accountDto.isPrincipal());
        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        account.setDeleted(accountDto.isDeleted());
        account.setEnabled(accountDto.isEnabled());
        account.setCreationDate(accountDto.getCreationDate());
        account.setType(accountDto.getType());
        account.setId(accountDto.getId());
        account.setCardIds(accountDto.getCardIds());
        account.setUserId(accountDto.getUserId());

        return account;
    }

    public List<AccountDto> map(List<Account> accounts) {

        return  accounts
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<Account> toMap(List<AccountDto> accountDtos) {

        return  accountDtos
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }



}
