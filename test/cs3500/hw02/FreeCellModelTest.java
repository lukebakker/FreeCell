package cs3500.hw03.test;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by lbakker on 9/23/16.
 */
public class FreeCellModelTest {

  FreeCellModel exampleGame = new FreeCellModel();
  FreeCellModel exampleGame2 = new FreeCellModel();


  // tests the get deck method
  @Test
  public void testGetDeck() {
    assertEquals(exampleGame.getDeck().size(), 52);
    String totalDeck = "";
    for (int i = 0; i < exampleGame.getDeck().size(); ++i) {
      totalDeck += exampleGame.getDeck().get(i).toString();
    }
    assertEquals(totalDeck, "A♥A♠A♦A♣2♥2♠2♦2♣3♥3♠3♦3♣4♥4♠4♦4♣5♥5♠5♦5♣6♥6"
            + "♠6♦6♣7♥7♠7♦7♣8♥8♠8♦8♣9♥9♠9♦9♣10♥10♠10♦10♣J♥J♠J♦J♣Q♥Q♠Q♦Q♣K♥K♠K♦K♣");
    assertEquals(exampleGame.getGameState(), "");
  }

  @Test
  public void reverseDeckTests() {
    List<Card> deck = exampleGame.getDeck();
    Collections.reverse(deck);
    exampleGame.startGame(deck, 4, 4, false);
    assertEquals(exampleGame.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
            "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
            "C3: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n" +
            "C4: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥");
    assertEquals(exampleGame.isGameOver(), false);
    for (int j = 0; j < 4; j++) {
      for (int i = 12; i >= 0; i--) {
        exampleGame.move(PileType.CASCADE, j, i, PileType.FOUNDATION, j);
      }
    }
    assertEquals(exampleGame.getGameState(), "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣," +
            " 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F4: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:");

    assertEquals(exampleGame.isGameOver(), true);
  }

  @Test
  public void testDoesShuffle() {

    //testing a to make sure a shuffled deck is different from an unshuffled one.
    exampleGame2.startGame(exampleGame.getDeck(), 5, 4, true);

    assertNotEquals(exampleGame.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♥, 2♦, 3♠, 4♣, 6♥, 7♦, 8♠, 9♣, J♥, Q♦, K♠\n" +
            "C2: A♦, 2♠, 3♣, 5♥, 6♦, 7♠, 8♣, 10♥, J♦, Q♠, K♣\n" +
            "C3: A♠, 2♣, 4♥, 5♦, 6♠, 7♣, 9♥, 10♦, J♠, Q♣\n" +
            "C4: A♣, 3♥, 4♦, 5♠, 6♣, 8♥, 9♦, 10♠, J♣, K♥\n" +
            "C5: 2♥, 3♦, 4♠, 5♣, 7♥, 8♦, 9♠, 10♣, Q♥, K♦");
  }

  @Test
  public void testGameState() {

    // tests with a sorted deck and moves
    assertEquals(exampleGame.getGameState(), "");

    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);

    exampleGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);

    assertEquals(exampleGame.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: K♥\n" +
            "O4:\n" +
            "C1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "C2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "C3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "C4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣");

    exampleGame.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
    assertEquals(exampleGame.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: K♥\n" +
            "O4:\n" +
            "C1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♠\n" +
            "C2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "C3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "C4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣");

  }

  // attempt to move to an occupied open pile
  @Test(expected = IllegalArgumentException.class)
  public void testGameStateFail() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);
    exampleGame.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
    exampleGame.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);
  }

  // attempt to move with an invalid index.
  @Test(expected = IllegalArgumentException.class)
  public void testGameStateFail2() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 0, 5, PileType.OPEN, 2);
  }

  // attempt to move to a foundation pile that is illegal.
  @Test(expected = IllegalArgumentException.class)
  public void testGameStateFail3() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
  }

  //tests what happens when there is a duplicate card
  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateCard() {
    List<Card> deck2 = exampleGame.getDeck();
    deck2.remove(50);
    deck2.add(new Card(Value.two, Suit.Spades));
    exampleGame.startGame(deck2, 4, 4, false);
  }

  //tests what happens when there are too many cards
  @Test(expected = IllegalArgumentException.class)
  public void testTooManyCard() {
    List<Card> deck2 = exampleGame.getDeck();
    deck2.add(new Card(Value.two, Suit.Spades));
    exampleGame.startGame(deck2, 4, 4, false);
  }

  //tests what happens when there are too few cards
  @Test(expected = IllegalArgumentException.class)
  public void testTooFewCard() {
    List<Card> deck2 = exampleGame.getDeck();
    deck2.remove(1);
    exampleGame.startGame(deck2, 4, 4, false);
  }


  //tests invalid number of cascade piles
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetup() {
    exampleGame.startGame(exampleGame.getDeck(), 3, 4, false);
  }

  //tests invalid number of cascade piles
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetup2() {
    exampleGame.startGame(exampleGame.getDeck(), 0, 8, false);
  }

  //tests invalid number of open piles
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetup3() {
    exampleGame.startGame(exampleGame.getDeck(), 8, 0, false);
  }

  //tests invalid number of cascade piles
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetup4() {
    exampleGame.startGame(exampleGame.getDeck(), 2, 3, false);
  }

  //tests moving a card that isnt an ace onto a cascade pile that doesnt work
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
  }

  //tests moving 2 cards onto open pile
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves2() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 1, 12, PileType.OPEN, 0);
    exampleGame.move(PileType.CASCADE, 1, 11, PileType.OPEN, 0);
  }

  //tests moving a card from an empty open pile
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves3() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.OPEN, 1, 12, PileType.CASCADE, 0);
  }

  //tests invalid cascade move
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves4() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
  }

  //tests invalid cardIndex input
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves5() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, 1, 11, PileType.OPEN, 0);
  }

  //tests invalid pile input
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves6() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.CASCADE, -1, 12, PileType.CASCADE, 0);
  }

  //tries to move from foundation pile to cascade
  // for my program this will be illegal
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves7() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.FOUNDATION, 1, 12, PileType.CASCADE, 0);
  }

  //tries to move from foundation pile to open
  // for my program this will be illegal
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves8() {
    exampleGame.startGame(exampleGame.getDeck(), 4, 4, false);
    exampleGame.move(PileType.FOUNDATION, 1, 12, PileType.OPEN, 0);
  }


  //tries to move before the game has started
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoves9() {
    exampleGame.move(PileType.CASCADE, 1, 12, PileType.OPEN, 0);
  }


}