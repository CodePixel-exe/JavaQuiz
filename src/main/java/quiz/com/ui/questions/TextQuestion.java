package quiz.com.ui.questions;

public class TextQuestion extends Question{
    /**
     * TextQuestion Constructor
     * This constructor allows for instances to be made.
     *
     * @param question
     * @param answer to the question
     * @param points of the question
     * @param questionType type of question
     */
    public TextQuestion(int questionType , String question, String answer, int points) {
        super(questionType, question, answer, points);
    }
}
