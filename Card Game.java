//Card Game

//Part 1
// 2 players, 1 deck of 52 cards [1…52] unique integers, deal out cards
// each player will take out the first card on the top of their stack of cards
// whichever one has the highest value card wins that round
// they will get those 2 points (one for each card)
// continue until cards run out
// winner has the highest score
// print out who is the winner + score, loser + score

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
        int max1 = 0, max2 = 0;

        for(int i = 0; i < deck.length; i++){
            if(i % 2 != 0){
                deck1.add(deck[i]);
                max1 = Math.max(max1, deck[i]);
            }else{
                deck2.add(deck[i]);
                max2 = Math.max(max1, deck[i]);
            }
        }

        //simulate deal process 
        int playerScore1 = 0, playerScore2 = 0;
        for(int i = 0; i < deck1.size(); i++){
            if (deck1.get(i) > deck2.get(i)){
                playerScore1 += 2;
            }else{
                playerScore2 += 2;
            }
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

//want to play with N players, input is list of strings (len N) with their names
//deck has M cards of di‍‍‌‍‌‍‌‌‍‌‍‍‌‌‍‍‌‌‍‌stinct integers (1-M)
//M=56 cards
//3 players
//18 cards per player
//2 cards leftover
//print out who is the winner + score, losers + scores

public class CardGame{
    //1. Generate the deck
    public static int[] generateDeck(int num){
        int[] deck = new int[num];

        for(int i = 0; i < num; i++){
            deck[i] = i + 1;
        }

        return deck;
    }

    //2. Shuffle the card
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
    public static List<int[]> playGame(List<String> players, int[] deck){
        int n = players.size(), m = deck.length;
        int cardPerPlayer = m / n;
        int[] maxCards = new int[n];
        int[] playerScores = new int[n];

        //Deal cards
        List<List<Integer>> playersCards = new ArrayList<>();

        for(int i = 0; i < m; i += n){
            for(int j = 0; j < n; j++){
                playersCards.get(j).add(deck[i]);
                maxCards[j] = Math.max(deck[i], maxCards[j]);
            }
        }
        //Radom deal the leftover cards
        if (m % n != 0){
            Random rd = new Random();
            int remain = m % n;
            for(int i = 0; i < remain; i++){
                int next = rd.nextInt(n + 1);
                playersCards.get(next).add(deck[deck.length - i]);
                maxCards[j] = Math.max(deck[i], maxCards[j]);
            }
        }

        //Compare cards, calculate score and update max card number
        for(int i = 0; i < m; i++){
            
        }
    }

    public static void main(String[] args) {
        int numPlayers = 3;
        String[] players = {"Player 1", "Player 2", "Player 3"};

        int[] deck = generateDeck(56);
        suffleCard(deck);
        List<int[]> playerResults = playGame(deck, players);

        int maxScore = Integer.MIN_VALUE;
        int winnerIndex = -1;
        for (int i = 0; i < playerResults.size(); i++) {
            int[] result = playerResults.get(i);
            int score = result[0];
            System.out.println(players[i] + " - Score: " + score + ", Max Card: " + result[1]);
            if (score > maxScore) {
                maxScore = score;
                winnerIndex = i;
            }
        }

        System.out.println();

        System.out.println("Winner: " + players[winnerIndex] + " - Score: " + maxScore);

        for (int i = 0; i < playerResults.size(); i++) {
            if (i != winnerIndex) {
                System.out.println("Loser: " + players[i] + " - Score: " + playerResults.get(i)[0]);
            }
        }
    }
}




