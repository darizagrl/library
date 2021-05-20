package com.example.demo.cucumber;

import com.example.demo.persistence.entity.Author;
import com.example.demo.persistence.repository.AuthorRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FindAuthorsSteps {
    @Autowired
    private TestRestTemplate testRestTemplate;
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
        actualAuthors.addAll(Arrays.asList(
                objectMapper.readValue(
                        testRestTemplate.getForEntity("/authors", String.class)
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
}
