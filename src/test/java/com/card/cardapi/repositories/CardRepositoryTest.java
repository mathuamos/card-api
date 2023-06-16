package com.card.cardapi.repositories;

import com.card.cardapi.entities.Card;
import com.card.cardapi.entities.User;
import com.card.cardapi.utils.CardStatus;
import com.card.cardapi.utils.ResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CardRepositoryTest {




    @Autowired
    private CardRepository cardRepository1;

    @Mock
    private CardRepository cardRepository;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testFindCardByCreatedByUserIdAndCardStatus1() {
        Long userId = 1L;
        String status = "ACTIVE";
        Pageable pageable = Pageable.ofSize(10);

        Page<Card> result = cardRepository1.findCardByCreatedByUserIdAndCardStatus(userId, status, pageable);

        assertEquals(0, result.getTotalElements());
    }


    @Test
    public void testFindCardByCreatedByUserIdAndCardStatus() {
        Long userId = 1L;
        String status = "ACTIVE";
        Pageable pageable = Pageable.ofSize(10);

        // Create a list of cards for the mock repository to return
        List<Card> cards = Arrays.asList(
                createCard(1L, "Card 1", "ACTIVE"),
                createCard(2L, "Card 2", "INACTIVE")
        );

        // Create a Page object from the list of cards
        Page<Card> cardPage = new PageImpl<>(cards, pageable, cards.size());

        // Mock the repository method
        when(cardRepository.findCardByCreatedByUserIdAndCardStatus(userId, status, pageable)).thenReturn(cardPage);

        // Call the repository method
        Page<Card> result = cardRepository.findCardByCreatedByUserIdAndCardStatus(userId, status, pageable);

        // Assert the expected result
        assertEquals(cards.size(), result.getTotalElements());
    }

    @Test
    public void testFindAllByCardStatus() {
        String status = "ACTIVE";
        Pageable pageable = Pageable.ofSize(10);

        // Create a list of cards for the mock repository to return
        List<Card> cards = Arrays.asList(
                createCard(1L, "Card 1", "ACTIVE"),
                createCard(2L, "Card 2", "ACTIVE"),
                createCard(3L, "Card 3", "INACTIVE")
        );

        // Create a Page object from the list of cards
        Page<Card> cardPage = new PageImpl<>(cards, pageable, cards.size());

        // Mock the repository method
        when(cardRepository.findAllByCardStatus(status, pageable)).thenReturn(cardPage);

        // Call the repository method
        Page<Card> result = cardRepository.findAllByCardStatus(status, pageable);

        // Assert the expected result
        assertEquals(cards.size(), result.getTotalElements());
    }

    @Test
    public void testFindByIdAndCardStatus() {
        Long id = 1L;
        String cardStatus = "ACTIVE";

        // Create a Card object for the mock repository to return
        Card card = createCard(1L, "Card 1", "ACTIVE");

        // Create an Optional object containing the Card
        Optional<Card> optionalCard = Optional.of(card);

        // Mock the repository method
        when(cardRepository.findByIdAndCardStatus(id, cardStatus)).thenReturn(optionalCard);

        // Call the repository method
        Optional<Card> result = cardRepository.findByIdAndCardStatus(id, cardStatus);

        // Assert the expected result
        assertEquals(optionalCard, result);
        assertEquals(card, result.orElse(null));
    }


    @Test
    public void testGetAlCardStatusAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrColorContainingIgnoreCaseOrStatusContainsIgnoreCaseOrCreatedAt() {
        // Save test data
        Card card1 = new Card();
        card1.setName("Card 1");
        card1.setDescription("Important Card");
        card1.setColor("Blue");
        card1.setStatus("In Progress");
        card1.setCreatedAt(new Date());
        card1.setCardStatus("ACTIVE");
        cardRepository1.save(card1);

        Card card2 = new Card();
        card2.setName("Card 2");
        card2.setDescription("Another Card");
        card2.setColor("Red");
        card2.setStatus("Completed");
        card2.setCreatedAt(new Date());
        card2.setCardStatus("INACTIVE");
        cardRepository1.save(card2);

        String cardStatus = "ACTIVE";
        String searchKeyName = "card";
        String searchKeyDescription = "important";
        String searchKeyColor = "blue";
        String searchKeyStatus = "in progress";
        String searchKeyDate = "2023-06-14";
        Pageable pageable = Pageable.ofSize(10);

        // Execute the query
        Page<Card> result = cardRepository1.getAlCardStatusAndNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrColorContainingIgnoreCaseOrStatusContainsIgnoreCaseOrCreatedAt(
                cardStatus, searchKeyName, searchKeyDescription, searchKeyColor, searchKeyStatus, searchKeyDate, pageable);

        // Assert the expected result
        List<Card> cards = result.getContent();
        assertEquals(1, cards.size());

    }



    // Helper method to create a Card object
    public Card createCard(Long id, String name, String status) {
        Card card = new Card();
        card.setId(id);
        card.setName(name);
        card.setStatus(status);
        return card;
    }


}