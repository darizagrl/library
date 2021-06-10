package cucumber;

import com.example.library.persistence.entity.Book;
import com.example.library.persistence.repository.book_repo.BookRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class GetBookStepsDefinition {
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String bookResourceUrl
//            = "http://localhost:8081/books/";
//    @Autowired
//    private BookRepo bookRepo;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private Book expectedBook;
//    private Book actualBook;
//
//    @Before
//    public void setup() {
//        bookRepo.deleteAll();
//        expectedBook = new Book();
//    }
//
//    @Given("the book is saved with the title {string}")
//    public void theBookIsSavedWithTheTitle(String title) {
//        expectedBook.setTitle(title);
//        bookRepo.save(expectedBook);
//    }
//
//    @When("the client calls GET books\\/id")
//    public void theClientCallsGETBooksId() throws JsonProcessingException {
//        actualBook = objectMapper.readValue(restTemplate.getForEntity(
//                bookResourceUrl + 1, String.class).getBody(), Book.class);
//    }
//
//    @Then("the response contains the book with title {string}")
//    public void theResponseContainsTheBookWithTitle(String title) {
//        assertThat(actualBook.getTitle()).isEqualTo(title);
//    }
//
//    @And("a status code of GET books\\/id response is {string}")
//    public void aStatusCodeOfGETBooksIdResponseIs(String statusCode) {
//        String code = String.valueOf(restTemplate
//                .getForEntity(bookResourceUrl + 1, String.class).getStatusCode());
//        assertThat(code).isEqualTo(statusCode);
//    }
}
