package quiz.com.ui.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    //For the options
    protected List<String> options = new ArrayList<>();
    private final int correctIndex;

    public MultipleChoiceQuestion(int questionType, String question, String answer, String[] options, int points) {
        super(questionType, question, answer, points);

        for (String choice : options) {
            this.options.add(choice);
        }
        this.options.add(answer);
        Collections.shuffle(this.options);

        this.correctIndex = this.options.indexOf(answer);

    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion: " +
                super.toString() +
                " options - " + options;
    }

    @Override
    public String getQuestion() {
        StringBuilder output = new StringBuilder(super.getQuestion() + "\n");

        //Add a question on set by a number
        for (int i = 0;  i < this.options.size(); i ++){
            output.append(i + 1).append(": ");
            output.append(options.get(i)).append("\n");
        }
        output.append("(Answer 1,2,3,4)");
        return output.toString();
    }


    public boolean isCorrect(String userSays){
        boolean isCorrect = false;
        try{
            //change user answer to int and subtract 1 as the index starts at 0
            int userIndex = Integer.parseInt(userSays) - 1;
            if(correctIndex == userIndex){
                isCorrect = true;
            }
        }catch (Exception e){
            isCorrect = false;
        }
        return isCorrect;
    }

}
