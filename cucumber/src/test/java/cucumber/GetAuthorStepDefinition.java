package cucumber;

import com.example.library.persistence.entity.Author;
import com.example.library.persistence.repository.author_repo.AuthorRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAuthorStepDefinition {
    private final String authorResourceUrl
            = "http://localhost:8081/authors/";
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private ObjectMapper objectMapper;
    private Author expectedAuthor;
    private Author actualAuthor;

    @Before
    public void setup() {
        authorRepo.deleteAll();
        expectedAuthor = new Author();
    }

    @Given("the author is saved with first name {string}, last name {string}")
    public void theAuthorIsSavedWithFirstNameLastNameAndId(String firstName, String lastName) {
        expectedAuthor.setFirstname(firstName);
        expectedAuthor.setLastname(lastName);
        authorRepo.save(expectedAuthor);
    }

    @When("the client calls GET author")
    public void theClientCallsGETAuthorWithId() throws Exception {
        actualAuthor = objectMapper.readValue(restTemplate.getForEntity(
                authorResourceUrl + 2, String.class).getBody(), Author.class);
    }

    @Then("the response contains the author with first name {string}, last name {string}")
    public void theResponseContainsTheWithFirstNameLastName(String firstName, String lastName) {
        assertThat(firstName).isEqualTo(actualAuthor.getFirstname());
        assertThat(lastName).isEqualTo(actualAuthor.getLastname());
    }

    @And("a status code of response is {string}")
    public void aStatusCodeOfResponseIs(String statusCode) {
        String code = String.valueOf(restTemplate
                .getForEntity(authorResourceUrl + 2, String.class).getStatusCode());
        assertThat(code).isEqualTo(statusCode);
    }
}
