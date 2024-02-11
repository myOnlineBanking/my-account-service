package jee.ensas.accountservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import jee.ensas.accountservice.controllers.AccountController;
import jee.ensas.accountservice.daos.EType;
import jee.ensas.accountservice.dtos.AccountDto;
import jee.ensas.accountservice.services.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@SpringBootTest
public class AccountControllerTest {

    @MockBean
    AccountService accountService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateAccount() throws Exception {
        AccountDto accountDto = new AccountDto(null, "61d49aa49a397467188850ec", "HAMZA",
                500, EType.NORMAL, "EURO", false, false, false, new Date(),
                false, "", new HashSet<String>());


        when(accountService.createAccount(any(AccountDto.class), any(HttpServletRequest.class)))
                .thenReturn(accountDto);

        mockMvc.perform(post("/Account/create")
                        .content(mapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(accountDto.getUserId()));
    }

    @Test
    public void testGetAccountByNumber() throws Exception {
        AccountDto accountDto = new AccountDto(null, "61d49aa49a397467188850ec", "HAMZA",
                500, EType.NORMAL, "EURO", false, false, false, new Date(),
                false, "", new HashSet<String>());


        when(accountService.getByAccountNumber(any(String.class), any(HttpServletRequest.class)))
                .thenReturn(accountDto);

        mockMvc.perform(get("/Account/getByAccountNumber")
                        .param("accountNumber", "HAMZA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("HAMZA"));
    }

}
