/**
 * Created by lbakker on 10/2/16.
 */
package cs3500.hw03;
import java.util.List;

/**
 * interface for a freecell Controller.
 */
public interface IFreeCellController<K> {
  /**
   * @param deck        the list of cards to be used in the game.
   * @param model       the model to be used to run the functions of the game.
   * @param numCascades the number of cascade piles to be used.
   * @param numOpens    the number of open piles to be used.
   * @param shuffle     is the deck shuffled.
   */
  void playGame(List<K> deck, IFreeCellModel<K> model, int numCascades,
                int numOpens, boolean shuffle);
}