package jee.ensas.accountservice;


import jee.ensas.accountservice.daos.Account;
import jee.ensas.accountservice.daos.EType;
import jee.ensas.accountservice.dtos.AccountDto;
import jee.ensas.accountservice.mappers.AccountMapper;
import jee.ensas.accountservice.repositories.AccountRepository;
import jee.ensas.accountservice.services.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper = new AccountMapper();

    @InjectMocks
    AccountServiceImpl accountService;


    @Test
    public void testUpdateAccount() {
        AccountDto accountDto = new AccountDto("", "61d49aa49a397467188850ec", "HAMZA",
                500, EType.NORMAL, "EURO", false, false, false, new Date(),
                false, "",new HashSet<String>());
        Account account = accountMapper.map(accountDto);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto createdAccount = accountService.updateAccount(accountDto, null);
        assertThat(createdAccount.getAccountNumber()).isSameAs(account.getAccountNumber());
        assertThat(createdAccount.getUserId()).isSameAs(account.getUserId());
    }

    @Test
    public void testAcceptAccount() {
        AccountDto accountDto = new AccountDto("", "61d49aa49a397467188850ec", "HAMZA",
                500, EType.NORMAL, "EURO", false, false, false, new Date(),
                false, "",new HashSet<String>());
        Account account = accountMapper.map(accountDto);
        account.setAccepted(true);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto createdAccount = accountService.updateAccount(accountDto, null);
        assertThat(createdAccount.getAccountNumber()).isSameAs(account.getAccountNumber());
        assertThat(createdAccount.isAccepted()).isTrue();
    }

    @Test
    public void testGetByAccountNumber() {
        AccountDto accountDto = new AccountDto("", "61d49aa49a397467188850ec", "HAMZA",
                500, EType.NORMAL, "EURO", false, false, false, new Date(),
                false, "",new HashSet<String>());
        Account account = accountMapper.map(accountDto);

        when(accountRepository.findByAccountNumber(any(String.class))).thenReturn(account);

        AccountDto createdAccount = accountService.getByAccountNumber("HAMZA", null);
        assertThat(createdAccount.getAccountNumber()).isSameAs(account.getAccountNumber());
        assertThat(createdAccount.getUserId()).isSameAs(account.getUserId());
    }

    @Test
    public void testReverseList() {
        List<AccountDto> accountDtoList = Arrays.asList(new AccountDto("1", "1", "1",
                1, EType.NORMAL, "1", false, false, false, new Date(),
                false, "",new HashSet<String>()), new AccountDto("2", "2", "2",
                2, EType.NORMAL, "2", false, false, false, new Date(),
                false, "",new HashSet<String>()), new AccountDto("3", "3", "3",
                3, EType.NORMAL, "3", false, false, false, new Date(),
                false, "",new HashSet<String>()));

        List<AccountDto> reversed = accountService.reverseList(new ArrayList<>(accountDtoList));
        assertThat(accountDtoList.size()).isSameAs(reversed.size());
        assertThat(accountDtoList.get(0).getId()).isEqualTo(reversed.get(2).getId());


    }


}
