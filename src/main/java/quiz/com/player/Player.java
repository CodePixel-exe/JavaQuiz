package quiz.com.player;

import java.util.Stack;

public class Player extends Person {
    private Stack<Integer> previousScores = new Stack<>();

    public Player(String firstName, String surname) {
        super(firstName, surname);
    }

    public Player(String firstName, String surname, String scores) {
        this(firstName, surname);

        String[] csvScores = scores.split(";");
        for (String val : csvScores) {
            try {
                int iVal = Integer.parseInt(val);
                previousScores.add(iVal);
            } catch (NumberFormatException nfe) {
                System.err.println("Issue parsing previous score information.");
            }
        }
    }


    public int getHighestScore() {
        if (previousScores.size() > 0) {
            Stack<Integer> working = (Stack<Integer>) previousScores.clone();

            int maxValue = working.pop();
            int remainingValues = working.size();
            for (int i = 0; i < remainingValues; i++) {
                int currentValue = working.pop();
                if (currentValue > maxValue) {
                    maxValue = currentValue;

                }
            }
            return maxValue;
        } else {

            return 0;
        }
    }

    public void recordScore (int score){
        previousScores.add(score);
    }

    public int getLastScore(){
        if (!previousScores.isEmpty()) {
           return previousScores.peek();
        } else{
        return 0;
        }
    }

    public int getPreviousAttempts(){
        return previousScores.size();
    }

    public String getScoreAsString(){
        StringBuilder output = new StringBuilder();

        //Add a question on set by a number
        for (int i = 0;  i < previousScores.size(); i ++){
            if(i > 0){
                output.append(";");
            }
            output.append(previousScores.get(i));
        }
        return output.toString();
    }



    public String toString() {
        return "Player: " + super.toString() +
                " previousScores=" + previousScores;
    }
}
