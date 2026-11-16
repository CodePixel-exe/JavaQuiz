package quiz.com.ui;

import quiz.Quiz;

/** Start Quiz
 * Allows the quiz to start by using the myQuiz and then the start method.
 */
public class StartQuiz {
    public static void main(String[] args){
        //call the constructor for the quiz.
        Quiz myQuiz = new Quiz();
        //Start the quiz.
        myQuiz.start();
    }
}
