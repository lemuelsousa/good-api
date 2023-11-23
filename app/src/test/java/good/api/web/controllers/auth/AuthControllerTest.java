package good.api.web.controllers.auth;

import good.api.App;
import good.api.web.controllers.auth.dto.SignupRequest;
import good.api.web.controllers.auth.dto.SignupResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthControllerTest {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    private AuthControllerTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    @DisplayName("Given Signup Request When Signup Then Response 200 Code With Signup Body")
    void successfullySignup() {
        final var signupRequest = new SignupRequest("user", "user@example.com", "303030");

        ResponseEntity<SignupResponse> signupResponse = testRestTemplate.postForEntity(
                "/api/v1/auth/signup",
                signupRequest,
                SignupResponse.class);

        assertEquals(signupResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(signupResponse.getHeaders().getContentType(), MediaType.APPLICATION_JSON);

        SignupResponse responseBody = signupResponse.getBody();

        assertNotNull(responseBody);
        assertEquals(responseBody.name(), signupRequest.name());
        assertEquals(responseBody.email(), signupRequest.email());
        assertFalse(responseBody.password().isEmpty());
    }

}