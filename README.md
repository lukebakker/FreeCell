# FreeCell

A program to simulate the game of freecell. 

If the user runs the main file in the 'Game' class (hw04/cs3500/hw03/Game) then the will see an output like this:

    F1:
    F2:
    F3:
    F4:
    O1:
    O2:
    O3:
    O4:
    C1: K♣, Q♠, 10♣, 9♠, 7♣, 6♠, 4♣, 3♠, A♣
    C2: K♦, Q♥, 10♦, 9♥, 7♦, 6♥, 4♦, 3♥, A♦
    C3: K♠, J♣, 10♠, 8♣, 7♠, 5♣, 4♠, 2♣, A♠
    C4: K♥, J♦, 10♥, 8♦, 7♥, 5♦, 4♥, 2♦, A♥
    C5: Q♣, J♠, 9♣, 8♠, 6♣, 5♠, 3♣, 2♠
    C6: Q♦, J♥, 9♦, 8♥, 6♦, 5♥, 3♦, 2♥

To move you enter 3 inputs on 1 line seperated by a space. For example to move the 'A♣' to F1 (free pile 4) then the input would look like this:
C1 8 O4.

    F1: A♣
    F2:
    F3:
    F4:
    O1:
    O2:
    O3:
    O4:
    C1: K♣, Q♠, 10♣, 9♠, 7♣, 6♠, 4♣, 3♠
    C2: K♦, Q♥, 10♦, 9♥, 7♦, 6♥, 4♦, 3♥, A♦
    C3: K♠, J♣, 10♠, 8♣, 7♠, 5♣, 4♠, 2♣, A♠
    C4: K♥, J♦, 10♥, 8♦, 7♥, 5♦, 4♥, 2♦, A♥
    C5: Q♣, J♠, 9♣, 8♠, 6♣, 5♠, 3♣, 2♠
    C6: Q♦, J♥, 9♦, 8♥, 6♦, 5♥, 3♦, 2♥


The first input is the pile to be moved from, the second input is the index of the card to be moved (K♣ in C1 would be index 1) and the 3rd argument is the pile to be moved to.
