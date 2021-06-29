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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class GetBooksStepDefinition {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String bookResourceUrl
            = "http://localhost:8081/books";
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Book> expectedBooks;
    private List<Book> actualBooks;

    @Before
    public void setup() {
        expectedBooks = new ArrayList<>();
        actualBooks = new ArrayList<>();
        bookRepo.deleteAll();
    }

    @Given("the following books")
    public void theFollowingBooks(List<Book> books) {
        expectedBooks.addAll(books);
        bookRepo.saveAll(books);
    }

    @When("a user requests get all the books")
    public void aUserRequestsGetAllTheBook() throws JsonProcessingException {
        actualBooks.addAll(Arrays.asList(objectMapper
                .readValue(restTemplate
                        .getForEntity(bookResourceUrl, String.class)
                        .getBody(), Book[].class)));
    }

    @Then("all the books are returned")
    public void allTheBooksAreReturned() {
        validateBooks();
    }

    private void validateBooks() {
        assertThat(expectedBooks.size()).isEqualTo(actualBooks.size());
        IntStream.range(0, actualBooks.size())
                .forEach(index -> validateBook(expectedBooks.get(index),
                        actualBooks.get(index)));
    }

    private void validateBook(final Book expected, final Book actual) {
        assertThat(expected.getTitle()).isEqualTo(actual.getTitle());
    }

    @And("a status code is {string} for a book")
    public void aStatusCodeIs(String statusCode) {
        String code = String.valueOf(restTemplate
                .getForEntity(bookResourceUrl, String.class).getStatusCode());
        assertThat(code)
                .isEqualTo(statusCode);
    }
}
