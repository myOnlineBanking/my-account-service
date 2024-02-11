package jee.ensas.accountservice.controllers;

import jee.ensas.accountservice.dtos.AccountDto;
import jee.ensas.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/getAll")
    public List<AccountDto> getAccounts (final HttpServletRequest request){
        return accountService.getAll(request);
    }

    @GetMapping("/get")
    public AccountDto getAccount (@RequestParam String id, final HttpServletRequest request){
        return accountService.getAccount(id,request);
    }

    @GetMapping("/getByAccountNumber")
    public AccountDto getByAccountNumber (@RequestParam String accountNumber, final HttpServletRequest request){
        return accountService.getByAccountNumber(accountNumber,request);
    }

    @GetMapping("/getUserAccounts")
    public List<AccountDto> getUserAccounts (@RequestParam String userId , final HttpServletRequest request){
        return accountService.getAccountByUser(userId , request);
    }

    @PostMapping("/create")
    public AccountDto createAccounts (@RequestBody AccountDto accountDto, final HttpServletRequest request){
        return accountService.createAccount(accountDto, request);
    }

    @PostMapping("/enable")
    public AccountDto enableAccount (@RequestBody AccountDto accountDto, final HttpServletRequest request){
        return accountService.enableAccount(accountDto, request);
    }

    @PostMapping("/disable")
    public AccountDto disableAccount (@RequestBody AccountDto accountDto, final HttpServletRequest request){
        return accountService.disableAccount(accountDto, request);
    }

    @PostMapping("/delete")
    public AccountDto deleteAccount (@RequestBody AccountDto accountDto, final HttpServletRequest request){
        return accountService.deleteAccount(accountDto, request);
    }

    @PostMapping("/accept")
    public AccountDto acceptAccount (@RequestBody AccountDto accountDto, final HttpServletRequest request){
        return accountService.acceptAccount(accountDto, request);
    }



    @PutMapping("/update")
    public AccountDto updateAccount (@RequestBody AccountDto accountDto, final HttpServletRequest request){
        return accountService.updateAccount(accountDto, request);
    }

    @PutMapping("/addCard")
    public AccountDto addCardToAccount (@RequestBody Map<String,String> cardAndAccount , final HttpServletRequest request){
        return accountService.addCardToAccount(cardAndAccount, request);
    }

    @GetMapping("/getByAgencyId")
    public List<AccountDto> getByAgencyId (String agencyId, final HttpServletRequest request){
        return accountService.getByAgencyId(agencyId, request);
    }
}
