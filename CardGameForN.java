public class CardGameForN{
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
        // [dayu] variable nameing can be more clear
        int n = players.size(), m = deck.length;
        int cardPerPlayer = m / n;
        int[] maxCards = new int[n];
        int[] playerScores = new int[n];

        List<List<Integer>> eachPlayrsDecks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            eachPlayrsDecks.add(new ArrayList<>());
        }

        // deal the cards one by one
        for(int j = 0; j < n; j++){
            for(int i = 0; i + j < m; i += n){
                eachPlayrsDecks.get(j).add(deck[i + j]);
                maxCards[j] = Math.max(deck[i + j], maxCards[j]);
            }
        }
        //Radom deal the leftover cards
        int leftover = m % n;
        if (m % n != 0){
            Random rd = new Random();
            for(int i = 0; i < leftover; i++){
                //Randomly choose a player to give leftover
                int next = rd.nextInt(n);
                int currentCard = deck[deck.length - 1 - i];
                eachPlayrsDecks.get(next).add(currentCard);
                maxCards[i] = Math.max(currentCard, maxCards[i]);
            }
        }

        //Compare cards, calculate score
        for(int i = 0; i < cardPerPlayer; i++){
            int biggestCard = eachPlayrsDecks.get(0).get(i);
            int winner = 0;
            for (int j = 0; j < n; j++){
                if(eachPlayrsDecks.get(j).get(i) > biggestCard){
                    winner = j;
                    biggestCard = eachPlayrsDecks.get(j).get(i);
                }
            }
            playerScores[winner] += n;
        }
        int lastWinner = -1;
        for(int i = 0; i < n; i++){ 
            int leftBiggestCard = 0;
            if(eachPlayrsDecks.get(i).size() > cardPerPlayer){
                if(eachPlayrsDecks.get(i).get(cardPerPlayer) > leftBiggestCard){
                    lastWinner = i;
                    leftBiggestCard = eachPlayrsDecks.get(i).get(cardPerPlayer);
                }
            }
        }
        playerScores[lastWinner] += leftover;

        List<int[]> res = new ArrayList<>();
        res.add(maxCards);
        res.add(playerScores);

        return res;
    }


    public static void main(String[] args) {
        List<String> players = Arrays.asList("Player 1", "Player 2", "Player 3");

        int[] deck = generateDeck(56);
        suffleCard(deck);
        List<int[]> playerResults = playGame(players, deck);
        int[] scores = playerResults.get(1);
        int[] cardNums = playerResults.get(0);

        int maxScore = 0;
        int maxCardNum = 0;
        int winnerIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            int score = scores[i];
            int cardNum = cardNums[i];
            System.out.println(players.get(i) +  " - Score: " + score + ", Max Card: " + cardNum);
            if (score > maxScore) {
                maxScore = score;
                winnerIndex = i;
            }
            if(score == maxScore){
                if(cardNum > maxCardNum){
                    winnerIndex = i;
                }
            }
        }

        System.out.println("---------------------------");

        System.out.println("Winner: " + players.get(winnerIndex) + " - Score: " + maxScore);

        for (int i = 0; i < players.size(); i++) {
            if (i != winnerIndex) {
                System.out.println("Loser: " + players.get(i) + " - Score: " + scores[i]);
            }
        }
    }
}




