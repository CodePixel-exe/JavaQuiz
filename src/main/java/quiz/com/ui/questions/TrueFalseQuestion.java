package quiz.com.ui.questions;

public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(int questionType, String question, Boolean answer) {
        super(questionType, question, answer.toString(), 1);
    }

    @Override
    public String getQuestion() {
        return super.getQuestion() + "\n (Answer True or False)";
    }
}
