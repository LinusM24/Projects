import java.util.*;

public class Deck {

    public Stack<Card> deck = new Stack<>();

    public Deck () {
        String[] colors = {"Blue", "Red", "Green", "Yellow"};
        // Numbered Color Cards
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 14; j++) {
                Card card = new Card(j, colors[i]);
                deck.add(card);
            }
        }
        //Wild Cards
        for (int i = 14; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(i, "Wild");
                deck.add(card);
            }
        }
    }

    // Shuffles the deck
    public void shuffle (){
        // built in method!
        Collections.shuffle(deck);
    }

    // Deals 7 Cards to each player
    public void deal(Player player){
        for (int i = 0; i < 7; i++) {
            player.add(deck.pop());
        }
    }


    // for testing
    public void print() {
        for (Card card : deck)
            System.out.println(card.toString());
    }

}
