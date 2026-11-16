package quiz.com.ui.questions;

/**Question class
 * This is used to create instances of the attributes needed for the questions
 * Checks if the answers the user will give are correct or not.
 */
public abstract class Question {
    private String question;
    private String answer;
    private int points;





    /**
     * Question Constructor
     * This constructor allows for instances to be made.
     * answer is converted to lowercase to prevent errors and ensure it isn't case-sensitive.
     *
     * @param question for the simple questions
     */
    public Question(int questionType, String question, String answer, int points) {
        this.question = question;
        this.answer = answer.toLowerCase();
        this.points = points;
    }

    public String getQuestion() {
        return question + "  (" + points+ "pts) ?";
    }

    public String getAnswer() {
        return answer;
    }


    /** Checks questions for the correct answer.
     *
     * @param userSays is the parameter that will handle the users input for the question.
     * @return true if the answer is correct, otherwise return false if incorrect.
     */
    public boolean isCorrect(String userSays) {
        //Check if there has been an answer given.
        if (userSays != null && userSays.length() > 0) {
            userSays = userSays.toLowerCase();
            if (userSays.equals(getAnswer())) {
                return true;

            }

        }
        return false;
    }
    public int getPoints() {
        return points;
    }


    @Override
    public String toString() {
        return "Q: " + getQuestion();
    }

}




