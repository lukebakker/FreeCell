package cs3500.hw03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.CharBuffer;
import java.io.*;


/**
 * Created by lbakker on 10/2/16.
 */


class Game {
  /**
   * @param args reading from the command line.
   * @throws IOException throws an exception if the input is invalid.
   */
  public static void main(String[] args) throws IOException {
    try {
      Reader br = new InputStreamReader(System.in);
      IFreeCellController control = new FreeCellController(br, System.out, new FreeCellModel());
      IFreeCellModel model = new FreeCellModel();
      control.playGame(model.getDeck(), model, 4, 4, false);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}

/**
 * defines the class for a freecellcontroller.
 */
public class FreeCellController implements IFreeCellController<Card> {

  Readable rd;
  Appendable ap;
  IFreeCellModel<Card> model;

  /**
   * @param rd    buffered reader.
   * @param ap    buffered writer.
   * @param model the version of the game that is being played.
   */
  FreeCellController(Readable rd, Appendable ap, IFreeCellModel<Card> model) {
    this.rd = rd;
    this.ap = ap;
    this.model = model;
  }

  /**
   * @param deck        the list of cards to be used in the game.
   * @param model       the model to be used to run the functions of the game.
   * @param numCascades the number of cascade piles to be used.
   * @param numOpens    the number of open piles to be used.
   * @param shuffle     is the deck shuffled.
   */
  public void playGame(List<Card> deck, IFreeCellModel<Card> model, int numCascades,
                       int numOpens, boolean shuffle) {
    Scanner scan = new Scanner(this.rd);
    String intervalString = "";
    intervalString = scan.nextLine();
    scan.close();
    String delims = "[ ]+";
    String[] strings = intervalString.split(delims);
    String result = "";
    ArrayList<String> list = new ArrayList<String>();


    PileType sourcePile = PileType.OPEN;// will be changed.
    PileType destinationPile = PileType.OPEN;// will be changed.
    int counter = 0;
    int sourceNum = -1;
    int index = -1;
    int destinationNum = -1;
    for (int i = 0; i < strings.length; i++) {
      if (!strings[i].isEmpty()) {
        list.add(strings[i]);
      }
    }


    for (int i = 0; i < list.size(); ++i) {
      try {
        if (list.get(i).substring(0, 1).equals("C")) {
          if (isInt(list.get(i).substring(1, (list.get(i).length())))) {
            if (counter == 0) {
              sourceNum = Integer.parseInt(list.get(i).substring(1, (list.get(i).length())));
              sourcePile = PileType.CASCADE;
              counter++;
            }
            if (counter == 2) {
              destinationNum = Integer.parseInt(list.get(i).substring(1, (list.get(i).length())));
              destinationPile = PileType.CASCADE;
              counter++;
            }
          }
        }

        if (list.get(i).substring(0, 1).equals("O")) {
          if (isInt(list.get(i).substring(1, (list.get(i).length())))) {
            if (counter == 0) {
              sourceNum = Integer.parseInt(list.get(i).substring(1, (list.get(i).length())));
              sourcePile = PileType.OPEN;
              counter++;
            }
            if (counter == 2) {
              destinationNum = Integer.parseInt(list.get(i).substring(1, (list.get(i).length())));
              destinationPile = PileType.OPEN;
              counter++;
            }
          }
        }

        if (list.get(i).substring(0, 1).equals("F")) {
          if (isInt(list.get(i).substring(1, (list.get(i).length())))) {
            if (counter == 2) {
              destinationNum = Integer.parseInt(list.get(i).substring(1, (list.get(i).length())));
              destinationPile = PileType.FOUNDATION;
              counter++;
            }
          }
        }

        if (counter == 1) {
          if (isInt(list.get(i).substring(0, (list.get(i).length())))) {
            index = Integer.parseInt(list.get(i).substring(0, (list.get(i).length())));
            counter++;
          }
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    }
    try {
      model.move(sourcePile, sourceNum, index, destinationPile, destinationNum);
      ap.append(this.model.getGameState());
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IOException x) {

    }

  }

  /**
   * @param input the string to be checked.
   * @return return true if the string can be parsed as an int.
   */
  static boolean isInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }


}
