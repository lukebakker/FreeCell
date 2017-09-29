/**
 * Created by lbakker on 9/22/16.
 */
package cs3500.hw03;

public class Card {

  private final Suit suit;
  private final Value number;


  /**
   * Constructor for the Card class.
   *
   * @param number is the number on the card.
   * @param suit   is the suit of the card.
   */
  public Card(Value number, Suit suit) {
    this.number = number;
    this.suit = suit;

  }

  /**
   * @param c card to be checked.
   * @return true if they are equal.
   */
  public boolean equal(Card c) {
    return this.suit.ordinal() == c.suit.ordinal()
            && this.number.ordinal() == c.number.ordinal();

  }

  /**
   * gets the suit.
   *
   * @return the suit of the card
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * gets the value of the card.
   *
   * @return value enum.
   */
  public Value getValue() {
    return this.number;
  }

  /**
   * turns a card into a string output.
   *
   * @return the string form of a card.
   */
  public String toString() {
    String finalString = "";
    switch (number) {
      case ace:
        finalString += "A";
        break;
      case two:
        finalString += "2";
        break;
      case three:
        finalString += "3";
        break;
      case four:
        finalString += "4";
        break;
      case five:
        finalString += "5";
        break;
      case six:
        finalString += "6";
        break;
      case seven:
        finalString += "7";
        break;
      case eight:
        finalString += "8";
        break;
      case nine:
        finalString += "9";
        break;
      case ten:
        finalString += "10";
        break;
      case jack:
        finalString += "J";
        break;
      case queen:
        finalString += "Q";
        break;
      case king:
        finalString += "K";
        break;
      default:
        finalString = "invalid card";
    }
    switch (suit) {
      case Spades:
        finalString += "♠";
        break;
      case Hearts:
        finalString += "♥";
        break;
      case Diamonds:
        finalString += "♦";
        break;
      case Clubs:
        finalString += "♣";
        break;
      default:
        finalString = "invalid card";
        break;
    }
    return finalString;
  }
}



