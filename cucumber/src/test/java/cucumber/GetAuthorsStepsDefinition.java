package cucumber;

import com.example.library.persistence.entity.Author;
import com.example.library.persistence.repository.author_repo.AuthorRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAuthorsStepsDefinition {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String authorResourceUrl
            = "http://localhost:8081/authors";
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Author> expectedAuthors;
    private List<Author> actualAuthors;

    @Before
    public void setup() {
        expectedAuthors = new ArrayList<>();
        actualAuthors = new ArrayList<>();
        authorRepo.deleteAll();
    }

    @Given("the following authors")
    public void theFollowingAuthors(List<Author> authors) {
        expectedAuthors.addAll(authors);
        authorRepo.saveAll(authors);
    }

    @When("a user requests get all the authors")
    public void aUserRequestsGetAllTheAuthors() throws JsonProcessingException {
        actualAuthors.addAll(Arrays.asList(objectMapper
                .readValue(restTemplate
                        .getForEntity(authorResourceUrl, String.class)
                        .getBody(), Author[].class)));
    }

    @Then("all the authors are returned")
    public void allTheAuthorsAreReturned() {
        validateAuthors();
    }

    private void validateAuthors() {
        assertThat(expectedAuthors.size()).isEqualTo(actualAuthors.size());
        IntStream.range(0, actualAuthors.size())
                .forEach(index -> validateAuthor(expectedAuthors.get(index),
                        actualAuthors.get(index)));
    }

    private void validateAuthor(final Author expected, final Author actual) {
        assertThat(expected.getFirstname()).isEqualTo(actual.getFirstname());
        assertThat(expected.getLastname()).isEqualTo(actual.getLastname());
    }

    @And("a status code is {string}")
    public void aStatusCodeIs(String statusCode) {
        String code = String.valueOf(restTemplate
                .getForEntity(authorResourceUrl, String.class).getStatusCode());
        assertThat(code)
                .isEqualTo(statusCode);
    }
}
