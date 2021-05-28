package cucumber;

import com.example.library.persistence.entity.Author;
import com.example.library.persistence.repository.author_repo.AuthorRepo;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostAuthorStepsDefinition {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String authorResourceUrl
            = "http://localhost:8081";
    private List<ResponseEntity<Author>> responseEntities;
    private List<Author> expectedAuthors;
    @Autowired
    private AuthorRepo authorRepo;

    @Before
    public void setup() {
        authorRepo.deleteAll();
        expectedAuthors = new ArrayList<>();
        responseEntities = new ArrayList<>();
    }

    @Given("the author with")
    public void theAuthorWithFirstNameLastName(List<Author> authors) {
        expectedAuthors.addAll(authors);
    }

    @When("the client calls POST {string}")
    public void theClientCallsPOST(String request) {
        expectedAuthors
                .forEach(author -> responseEntities
                        .add(restTemplate
                                .postForEntity(authorResourceUrl + request, author, Author.class)));
        authorRepo.saveAll(expectedAuthors);
    }

    @Then("the status code is {string}")
    public void theStatusCodeIsCreated(String statusCode) {
        responseEntities.stream()
                .map(author -> String.valueOf(author.getStatusCode()))
                .forEach(code -> assertThat(code).isEqualTo(statusCode));
    }

    @And("the authorRepo contains the author with")
    public void theAuthorRepoContainsTheAuthorWith(List<Author> authors) {
        List<Author> authorList = authorRepo.findAll();
        assertThat(authorList.size()).isEqualTo(authors.size());
    }
}
