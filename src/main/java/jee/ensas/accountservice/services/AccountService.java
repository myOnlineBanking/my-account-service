package jee.ensas.accountservice.services;

import jee.ensas.accountservice.dtos.AccountDto;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface AccountService extends Serializable {

    List<AccountDto> getAll(final HttpServletRequest request);

    List<AccountDto> getAccountByUser(String userId, final HttpServletRequest request);

    AccountDto createAccount(AccountDto accountDto, final HttpServletRequest request);

    AccountDto updateAccount(AccountDto accountDto, final HttpServletRequest request);

    AccountDto getAccount(String id, HttpServletRequest request);

    AccountDto getByAccountNumber(String accountNumber, HttpServletRequest request);

    AccountDto addCardToAccount(Map<String, String> cardAndAccount, HttpServletRequest request);

    AccountDto enableAccount(AccountDto accountDto, HttpServletRequest request);

    AccountDto disableAccount(AccountDto accountDto, HttpServletRequest request);

    AccountDto deleteAccount(AccountDto accountDto, HttpServletRequest request);

    AccountDto acceptAccount(AccountDto accountDto, HttpServletRequest request);

    List<AccountDto> getByAgencyId(String agencyId, HttpServletRequest request);
}
