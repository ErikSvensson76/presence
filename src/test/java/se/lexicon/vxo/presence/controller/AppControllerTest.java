package se.lexicon.vxo.presence.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import se.lexicon.vxo.presence.entity.AppRole;
import se.lexicon.vxo.presence.entity.UserRole;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestEntityManager
@Transactional
public class AppControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestEntityManager em;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void given_nothing_getLoginForm_success() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login-form"));
    }

    @Test
    public void given_empty_model_getRegister_success() throws Exception{
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("form"));
    }


    @Test
    public void given_form_processForm_success() throws Exception{
        em.persistAndFlush(new AppRole(UserRole.APP_USER));
        em.persistAndFlush(new AppRole(UserRole.APP_ADMIN));

        mockMvc.perform(post("/users/process")
                    .param("email", "test@test.com")
                    .param("firstName", "Test")
                    .param("lastName", "Testsson")
                    .param("password","password123")
                    .param("passwordConfirmation", "password123")
                    .param("street", "Teststreet 1")
                    .param("zipCode", "12345")
                    .param("city", "TDDVille")
                    .param("homeNumber", "0470123456")
                    .param("mobileNumber", "070123456")
                    .param("gitHubLink", "https://github.com/ErikSvensson76")
                    .param("linkedInURL", "https://www.linkedin.com/in/erik-svensson-474a64184/?originalSubdomain=se"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
        em.flush();
    }

    @Test
    public void given_form_processForm_not_valid() throws Exception{
        mockMvc.perform(post("/users/process")
                    .param("email", "1s34@test.c")
                    .param("firstName", "")
                    .param("lastName", "")
                    .param("password","password123")
                    .param("passwordConfirmation", "password1234")
                    .param("street", "Teststreet 1")
                    .param("zipCode", "12345")
                    .param("city", "TDDVille")
                    .param("homeNumber", "0470123456")
                    .param("mobileNumber", "070123456")
                    .param("gitHubLink", "https://github.com/ErikSvensson76")
                    .param("linkedInURL", "https://www.linkedin.com/in/erik-svensson-474a64184/?originalSubdomain=se"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("form"))
                .andExpect(model().attributeHasFieldErrors("form", "email", "firstName", "lastName", "passwordConfirmation"));
    }


}
