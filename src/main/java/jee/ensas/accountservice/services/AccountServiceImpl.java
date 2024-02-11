package jee.ensas.accountservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jee.ensas.accountservice.daos.Account;
import jee.ensas.accountservice.dtos.AccountDto;
import jee.ensas.accountservice.exceptions.AccountException;
import jee.ensas.accountservice.mappers.AccountMapper;
import jee.ensas.accountservice.repositories.AccountRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper = new AccountMapper();

    Logger logger = Logger.getLogger("myLogger");


    @Value("${urls.saveUserAccount}")
    private String saveUserAccountUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public List<AccountDto> getAll(HttpServletRequest request) {
        try {
            System.out.println(accountRepository.findAll());
            return reverseList(accountMapper.map(accountRepository.findAll()));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public List<AccountDto> getAccountByUser(String userId, HttpServletRequest request) {
        try {
            return accountMapper.map(accountRepository.findByUserId(userId));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod_2",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto getAccount(String id, HttpServletRequest request) {
        try {
            return accountMapper.map(accountRepository.findById(id).orElse(new Account()));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod_2",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto getByAccountNumber(String accountNumber, final HttpServletRequest request) {
        try {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            return accountMapper.map(account);
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto addCardToAccount(Map<String, String> cardAndAccount, HttpServletRequest request) {
        try {
            Account account = accountRepository.findById(cardAndAccount.get("accountId")).orElse(null);
            assert account != null;
            account.getCardIds().add(cardAndAccount.get("cardId"));
            return accountMapper.map(accountRepository.save(account));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto enableAccount(AccountDto accountDto, HttpServletRequest request) {
        try {
            Account account = accountRepository.findById(accountDto.getId()).orElse(null);
            assert account != null;
            account.setEnabled(true);
            return accountMapper.map(accountRepository.save(account));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto disableAccount(AccountDto accountDto, HttpServletRequest request) {
        try {
            Account account = accountRepository.findById(accountDto.getId()).orElse(null);
            assert account != null;
            account.setEnabled(false);
            return accountMapper.map(accountRepository.save(account));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto deleteAccount(AccountDto accountDto, HttpServletRequest request) {
        try {
            Account account = accountRepository.findById(accountDto.getId()).orElse(null);
            assert account != null;
            account.setDeleted(true);
            return accountMapper.map(accountRepository.save(account));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto acceptAccount(AccountDto accountDto, HttpServletRequest request) {
        try {
            Account account = accountRepository.findById(accountDto.getId()).orElse(null);
            assert account != null;
            account.setAccepted(true);
            return accountMapper.map(accountRepository.save(account));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public List<AccountDto> getByAgencyId(String agencyId, HttpServletRequest request) {
        try {
            return accountMapper.map(accountRepository.findByAgencyId(agencyId));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto createAccount(AccountDto accountDto, final HttpServletRequest request) {
        try {
            Account account = accountMapper.map(accountDto);
            account = accountRepository.save(account);
            accountDto.setId(account.getId());

            logger.log(Level.INFO, account.toString());

            addAccountToUser(accountDto.getId(), accountDto.getUserId());

            return accountMapper.map(account);
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    private void addAccountToUser(String id, String userId) {
        try {
            Map<String, String> requestBody = new HashMap<>();

            requestBody.put("accountId", id);
            requestBody.put("userId", userId);

            webClientBuilder.build().post()
                    .uri(saveUserAccountUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(requestBody), Map.class)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "600000")
            }
    )
    @Override
    public AccountDto updateAccount(AccountDto accountDto, HttpServletRequest request) {
        try {
            Account account = accountMapper.map(accountDto);
            return accountMapper.map(accountRepository.save(account));
        } catch (Exception e) {
            throw new AccountException(HttpStatus.SC_FORBIDDEN, e.getMessage());
        }
    }


    public List<AccountDto> reverseList(List<AccountDto> alist) {
        for (int i = 0; i < alist.size() / 2; i++) {
            AccountDto temp = alist.get(i);
            alist.set(i, alist.get(alist.size() - i - 1));
            alist.set(alist.size() - i - 1, temp);
        }
        return alist;
    }

    public List<AccountDto> fallbackMethod(HttpServletRequest request, Throwable error) {
        throw new AccountException(HttpStatus.SC_FORBIDDEN, error.getMessage() != null ? error.getMessage() : "Service Is Not Responding or taking so long");
    }
    public List<AccountDto> fallbackMethod(String userId, HttpServletRequest request, Throwable error) {
        throw new AccountException(HttpStatus.SC_FORBIDDEN, error.getMessage() != null ? error.getMessage() : "Service Is Not Responding or taking so long");
    }
    public AccountDto fallbackMethod_2(String id, HttpServletRequest request, Throwable error) {
        throw new AccountException(HttpStatus.SC_FORBIDDEN, error.getMessage() != null ? error.getMessage() : "Service Is Not Responding or taking so long");
    }
    public AccountDto fallbackMethod(Map<String, String> cardAndAccount, HttpServletRequest request, Throwable error) {
        throw new AccountException(HttpStatus.SC_FORBIDDEN, error.getMessage() != null ? error.getMessage() : "Service Is Not Responding or taking so long");
    }
    public AccountDto fallbackMethod(AccountDto accountDto, HttpServletRequest request, Throwable error) {
        throw new AccountException(HttpStatus.SC_FORBIDDEN, error.getMessage() != null ? error.getMessage() : "Service Is Not Responding or taking so long");
    }
}
