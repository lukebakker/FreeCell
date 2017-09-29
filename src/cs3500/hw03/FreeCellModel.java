import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by lbakker on 9/22/16.
 */
public class FreeCellModel implements IFreeCellModel<Card> {

  private ArrayList<ArrayList<Card>> cascadePiles;
  private ArrayList<ArrayList<Card>> openPiles;
  private ArrayList<ArrayList<Card>> foundationPiles;

  /**
   * constructor for a FreeCellModel.
   */
  public FreeCellModel() {
    cascadePiles = new ArrayList<ArrayList<Card>>();
    openPiles = new ArrayList<ArrayList<Card>>();
    foundationPiles = new ArrayList<ArrayList<Card>>();
  }

  /**
   * Creates the deck of cards for the game in an ArrayList.
   *
   * @return the deck of cards.
   */

  public List<Card> getDeck() {
    ArrayList<Card> deck = new ArrayList<Card>();
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        deck.add(new Card(Value.values()[i], Suit.values()[j]));
      }
    }
    return deck;

  }

  /**
   * @param deck the deck to be checked.
   * @return true if the deck is a valid deck.
   */
  private boolean goodDeck(List<Card> deck) {
    List<Card> decDup = new ArrayList<Card>();
    Card test = new Card(Value.ace, Suit.Spades);
    Card test2 = new Card(Value.ace, Suit.Spades);
    if (test2.equals(test)) {
      return false;
    }
    for (int i = 0; i < deck.size(); i++) {
      for (int j = 0; j < decDup.size(); j++) {
        if (decDup.get(j).equal((deck.get(i)))) {
          return false;
        }
      }
      decDup.add(deck.get(i));
    }
    return (deck.size() == 52);
  }

  /**
   * Creates the starting conditions for the game
   *
   * @param deck            the deck to be dealt.
   * @param numCascadePiles number of cascade piles.
   * @param numOpenPiles    number of open piles.
   * @param shuffle         if true, shuffle the deck else deal the deck as-is.
   */
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    if (!goodDeck(deck)) {
      throw new IllegalArgumentException("Invalid Deck");
    }

    if (cascadePiles.size() != 0) {
      cascadePiles = new ArrayList<ArrayList<Card>>();
      openPiles = new ArrayList<ArrayList<Card>>();
      foundationPiles = new ArrayList<ArrayList<Card>>();
    }

    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("invalid number of piles");
    }

    for (int l = 0; l < numCascadePiles; l++) {
      cascadePiles.add(new ArrayList<Card>());
    }

    for (int k = 0; k < numOpenPiles; k++) {
      openPiles.add(new ArrayList<Card>());
    }

    for (int k = 0; k < 4; k++) {
      foundationPiles.add(new ArrayList<Card>());
    }

    if (shuffle) {
      Collections.shuffle(deck);
    }

    int i = 0;
    while (i < deck.size()) {
      for (int j = 0; j < numCascadePiles; j++) {
        if (i < 52) {
          cascadePiles.get(j).add(deck.get(i));
          i++;
        }
      }
    }
  }

  /**
   * @param sourceType       the type of the source pile (see {@link PileType}).
   * @param sourcePileNumber the pile number of the given type, starting at 0.
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0.
   * @param destType         the type of the destination pile (see {@link PileType}).
   * @param destPileNumber   the pile number of the given type, starting at 0.
   * @return returns true if the move being attempted is a valid move for freecell.
   */
  private boolean isValidMove(PileType sourceType, int sourcePileNumber,
                              int cardIndex, PileType destType, int destPileNumber) {
    if (getGameState().equals("")) {
      return false;
    }
    if (sourcePileNumber < 0) {
      return false;
    }
    if (sourceType.equals(PileType.CASCADE)) {
      if (cardIndex != cascadePiles.get(sourcePileNumber).size() - 1) {
        return false;
      }
      if (cascadePiles.get(sourcePileNumber).size() == 0) {
        return false;
      }
    }

    if (sourceType.equals(PileType.FOUNDATION)) {
      return false;
    }

    if (sourceType.equals(PileType.OPEN)) {
      if (cardIndex != 0) {
        return false;
      }
      if (openPiles.get(sourcePileNumber).size() != 1) {
        return false;
      }
    }

    if (destType.equals(PileType.OPEN)) {
      if (openPiles.get(destPileNumber).size() != 0 || destPileNumber > (openPiles.size() - 1)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @param destType       the type of the destination pile (see {@link PileType}).
   * @param destPileNumber the pile number of the given type, starting at 0.
   * @param toBeMoved      the card that is being moved to another stack.
   * @return true if the move is a valid move and will go on the stack.
   */
  private boolean canBeStacked(PileType destType, int destPileNumber, Card toBeMoved) {
    if (destType.equals(PileType.CASCADE)) {
      if (cascadePiles.get(destPileNumber).get(cascadePiles.get(destPileNumber).size() - 1)
              .getSuit().ordinal() % 2 == 0
              && toBeMoved.getSuit().ordinal() % 2 != 0) {
        if (cascadePiles.get(destPileNumber).get(cascadePiles
                .get(destPileNumber).size() - 1).getValue().ordinal()
                == toBeMoved.getValue().ordinal() - 1) {
          return true;
        }
      }
    }

    if (destType.equals(PileType.FOUNDATION)) {
      if (foundationPiles.get(destPileNumber).size() == 0) {
        return (toBeMoved.getValue().ordinal() == 0);
      }
      if (foundationPiles.get(destPileNumber).get(foundationPiles.get(destPileNumber).size() - 1)
              .getSuit().ordinal() == toBeMoved.getSuit().ordinal()) {
        if (foundationPiles.get(destPileNumber).get(foundationPiles
                .get(destPileNumber).size() - 1).getValue().ordinal()
                == toBeMoved.getValue().ordinal() - 1) {
          return true;
        }
      }
    }
    return (destType.equals(PileType.OPEN));
  }


  /**
   * @param sourceType       the type of the source pile (see {@link PileType}).
   * @param sourcePileNumber the pile number of the given type, starting at 0.
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0.
   * @param destType         the type of the destination pile (see {@link PileType}).
   * @param destPileNumber   the pile number of the given type, starting at 0.
   */

  public void move(PileType sourceType, int sourcePileNumber, int cardIndex, PileType destType,
                   int destPileNumber) {
    // will be changed.
    Card toBeMoved = new Card(Value.ace, Suit.Spades);

    if (!isValidMove(sourceType, sourcePileNumber, cardIndex, destType, destPileNumber)) {
      throw new IllegalArgumentException("this is not a valid move");
    }

    if (sourceType.equals(PileType.OPEN)) {

      toBeMoved = openPiles.get(sourcePileNumber).get(0);
      openPiles.get(sourcePileNumber).remove(openPiles.get(sourcePileNumber).size() - 1);

    }

    if (sourceType.equals(PileType.CASCADE)) {

      toBeMoved = (cascadePiles.get(sourcePileNumber)
              .get(cascadePiles.get(sourcePileNumber).size() - 1));
      cascadePiles.get(sourcePileNumber).remove(cascadePiles.get(sourcePileNumber).size() - 1);

    }

    if (!canBeStacked(destType, destPileNumber, toBeMoved)) {
      throw new IllegalArgumentException("can't be stacked");
    }

    if (destType.equals(PileType.FOUNDATION)) {

      foundationPiles.get(destPileNumber).add(toBeMoved);

    }

    if (destType.equals(PileType.OPEN)) {
      openPiles.get(destPileNumber).add(toBeMoved);

    }

    if (destType.equals(PileType.CASCADE)) {
      cascadePiles.get(destPileNumber).add(toBeMoved);
    }
  }

  /**
   * checks to see if the game is over.
   *
   * @return returns true if the game is over.
   */
  public boolean isGameOver() {
    int gameCounter = 0;
    for (int i = 0; i < 4; i++) {
      if (foundationPiles.get(i).size() == 13) {
        gameCounter += 13;
      }
    }
    return (gameCounter == 52);
  }

  /**
   * prints out the game.
   *
   * @return returns the state of the game in the form of a display string.
   */
  public String getGameState() {
    String gameState = "";
    if (cascadePiles.size() == 0) {
      return gameState;
    }

    for (int i = 0; i < foundationPiles.size(); i++) {
      gameState += "F" + (i + 1) + ":";
      if (foundationPiles.get(i).size() != 0) {
        gameState += " ";
      }
      for (int j = 0; j < foundationPiles.get(i).size(); j++) {

        gameState += foundationPiles.get(i).get(j).toString();
        if (j < foundationPiles.get(i).size() - 1) {
          gameState += ", ";
        }
      }
      gameState += System.lineSeparator();
    }

    for (int i = 0; i < openPiles.size(); i++) {
      gameState += "O" + (i + 1) + ":";
      if (openPiles.get(i).size() != 0) {
        gameState += " ";
      }
      for (int j = 0; j < openPiles.get(i).size(); j++) {
        gameState += openPiles.get(i).get(j).toString();
        if (j < openPiles.get(i).size() - 1) {
          gameState += ", ";
        }
      }
      gameState += System.lineSeparator();
    }

    for (int i = 0; i < cascadePiles.size(); i++) {
      gameState += "C" + (i + 1) + ":";
      if (cascadePiles.get(i).size() != 0) {
        gameState += " ";
      }
      for (int j = 0; j < cascadePiles.get(i).size(); j++) {
        gameState += cascadePiles.get(i).get(j).toString();
        if (j < cascadePiles.get(i).size() - 1) {
          gameState += ", ";
        }
      }
      if (i < cascadePiles.size() - 1) {
        gameState += System.lineSeparator();
      }
    }
    return gameState;
  }


}

