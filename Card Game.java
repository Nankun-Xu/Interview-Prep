import java.util.*;

public class CardGame{
    //1. Generate the deck
    public static int[] generateDeck(int num){
        int[] deck = new int[num];
        for(int i = 0; i < num; i++){
            deck[i] = i + 1;
        }
        return deck;
    }

    //2. Simulate the shuffling
    public static void suffleCard(int[] deck){
        //traverse arr from last idx and swap arr[lasti] with a random index element before lasti;
        //update lasti
        Random random = new Random();
        for(int i = deck.length - 1; i >= 0; i--){//Last index we keep updating
            int randomIdx = random.nextInt(i + 1);//i also need to be included
            //Swap deck[randomIdx] and deck[i]
            int temp = deck[randomIdx];
            deck[randomIdx] = deck[i];
            deck[i] = temp;
        }
    }

    //Deal and Score counting
    public static int[][] playGame(int[] deck){
        //give half of cards to each player and both maintain a card with max number
        ArrayList<Integer> deck1 = new ArrayList<>();
        ArrayList<Integer> deck2 = new ArrayList<>();
        

        for(int i = 0; i < deck.length; i++){
            if(i % 2 != 0){
                deck1.add(deck[i]);
            }else{
                deck2.add(deck[i]);
            }
        }

        //simulate deal process 
        int playerScore1 = 0, playerScore2 = 0;
        int max1 = 0, max2 = 0;
        for(int i = 0; i < deck1.size(); i++){
            if (deck1.get(i) > deck2.get(i)){
                playerScore1 += 2;
            }else{
                playerScore2 += 2;
            }
            max1 = Math.max(max1, deck1.get(i));
            max2 = Math.max(max2, deck2.get(i));
        }

        return new int[][]{{playerScore1, max1}, {playerScore2, max2}};
    }


    public static void main(String[] args){
        int[] deck = generateDeck(52);
        suffleCard(deck);
        int[][] res = playGame(deck); 
        int playerScore1 = res[0][0], playerScore2 = res[1][0];
        int maxCard1 = res[0][1], maxCard2 = res[1][1];

        if (playerScore1 > playerScore2) {
            System.out.println("Player 1 wins with a score of " + playerScore1);
            System.out.println("Player 2 loses with a score of " + playerScore2);
        } else if (playerScore2 > playerScore1) {
            System.out.println("Player 2 wins with a score of " + playerScore2);
            System.out.println("Player 1 loses with a score of " + playerScore1);
        } else {
            System.out.println("Two players earn the same score, let's compare their max card");
            if(maxCard1 > maxCard2){
                System.out.println("Player 1 wins with a max card number " + maxCard1);
                System.out.println("Player 2 loses with a max card number " + maxCard2);
            }else{
                System.out.println("Player 2 wins with a max card number " + maxCard2);
                System.out.println("Player 1 loses with a max card number " + maxCard1);
            }
        }
    }
}