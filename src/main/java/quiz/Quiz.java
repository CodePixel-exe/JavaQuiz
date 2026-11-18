package quiz;

import quiz.com.player.Player;
import quiz.com.ui.questions.MultipleChoiceQuestion;
import quiz.com.ui.questions.Question;
import quiz.com.ui.questions.TextQuestion;
import quiz.com.ui.questions.TrueFalseQuestion;
import quiz.com.io.FileAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Quiz {
    //Default name if the user does not enter a name
    protected String DEFAULT_NAME = "Anon";

    private final ArrayList<Question> quizQuestions = new ArrayList<>();
    private ArrayList <Player> players = new ArrayList<>();

    /** Start of the quiz
     * Makes arrays read in questions from a file and splits them into rows.
     */
    public Quiz() {
        try {
            //check how many instances there is of the questions to create and use a loop to create all the questions.
            ArrayList<String> questions = FileAccess.readFromFile("src/main/resources/questionsmerged.csv");
            Collections.shuffle(questions);

            for (int i = 0; i < questions.size(); i++) {
                String[] q = questions.get(i).split(",");
                //Gets the points and parses them as an integer to get their value
                int questionType = Integer.parseInt(q[0].strip());
                int points = Integer.parseInt(q[3].strip());

                switch (questionType) {
                    case 1: {
                        Question newQ = new TextQuestion(1, q[1], q[2], points);
                        quizQuestions.add(newQ);
                        break;
                    }
                    case 2:
                        Question newTF = new TrueFalseQuestion(2, q[1], Boolean.parseBoolean(q[1].strip()));
                        quizQuestions.add(newTF);
                        break;

                    case 3:
                        Question newMQ = new MultipleChoiceQuestion(3, q[1], q[2], q[4].split(";"), points);
                        quizQuestions.add(newMQ);
                        break;

                    default:
                        System.err.println("Something went wrong with the question order.");

                }

            }
        } catch (Exception e) {
            System.err.println("There was a problem loading questions.");
            e.printStackTrace();
        }

        try {
            ArrayList<String> playerRows = FileAccess.readFromFile("src/main/resources/results.csv");
            for (int i = 0; i < playerRows.size(); i++) {
                String[] p = playerRows.get(i).split(",");
                if (p.length == 3) {
                    String firstName = (p[0].strip());
                    String surname = (p[1].strip());
                    String scores = (p[2].strip());
                    players.add(new Player(firstName, surname, scores));
                }
            }
            }catch(Exception e) {
            System.err.println("No results file found");
        }
    }

    /** Start Quiz
     * Starts the quiz and loops through questions
     * Allows user to restart quiz.
     */
    public void start() {
        boolean runQuiz = true;
        while (runQuiz != false) {
            Scanner input = new Scanner(System.in);
            //Get user's name
            System.out.println("What is your name");
            String name = input.nextLine();
            if (name.isEmpty()) {
                name = DEFAULT_NAME;
            }
            Player livePlayer = null;
            for (Player p : players){
                if(p.getFirstName().equalsIgnoreCase(name)){
                    livePlayer = p;
                }
            }
            if (livePlayer == null){
                livePlayer = new Player(name, "");
                players.add(livePlayer);
            }


            System.out.println("Welcome " + livePlayer.getFirstName()+ " to the quiz of the century!");
            //Keep track of score
            int total = 0;
            for (int i = 0; i < quizQuestions.size(); i++) {
                System.out.print((i + 1) + ": ");
                total = total + askQuestion(quizQuestions.get(i));
            }

            livePlayer.recordScore(total);
            try{
                FileAccess.writeToFile("results.csv", name + ",," + livePlayer.getScoreAsString());



            }catch (Exception e){
                System.err.println("An error occurred while saving your results.");
            }
            System.out.println("Would you like to play again? y/n?: ");
            String choice = input.nextLine();

            if(choice.equals("n")){

               System.out.println("\nThank you for playing the quiz. GoodBye!");
                System.out.println("Your last score: " + livePlayer.getLastScore());
                System.out.println("Your best score: " + livePlayer.getHighestScore());
                System.out.println("You attempted this quiz: " + livePlayer.getPreviousAttempts() + " times");
                System.out.println(livePlayer);
               break;
            }else{
                System.out.println("\nRestarting quiz...");
            }

        }
    }

    /** Ask questions
     * Asks the user questions and gives them a point for every correct answer.
     * @param q for the questions.
     * @return score once they have answered 3 questions.
     */
    public int askQuestion(Question q){
        Scanner input = new Scanner(System.in);
        int score = 0;
        System.out.println(q.toString());
        //Get the user input. Assumes the type as string.
        String answer = input.nextLine().strip();
        if(answer.isEmpty()){
            System.out.println("No answer supplied. 0 points");
        }else{
            if (q.isCorrect(answer)){
                score = q.getPoints();
                System.out.println(answer + " is the correct answer. "+score+" point");

            }else{
                System.out.println(answer + " is the wrong answer. 0 points.");
            }
        }
        return score;
    }

}



