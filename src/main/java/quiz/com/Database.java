package quiz.com;

import quiz.com.ui.questions.MultipleChoiceQuestion;
import quiz.com.ui.questions.Question;
import quiz.com.ui.questions.TextQuestion;
import quiz.com.ui.questions.TrueFalseQuestion;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection conn = null;

    public void connect(){
        String connectionUrl =
                "jdbc:mysql://localhost:3306/quiz?serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException e){
            System.err.println("There was an Issue:" + e.getMessage());
        }
    }
    public void disconnect(){
        try{
            conn.close();
        } catch (SQLException e) {
            System.err.println("Oops!!" + e.getMessage());
        }
    }


    public ArrayList<Question> getAllRows() throws SQLException {
        String sqlSelectAllPersons = "SELECT * FROM questions";

        PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);

        //executes the query.
        ResultSet rs = ps.executeQuery();

        ArrayList<Question> quizQuestions = new ArrayList<>();

        while (rs.next()) {
            int typeOfQ = rs.getInt("type");
            String quest = rs.getString("question");
            String ans = rs.getString("answer");
            int pts = rs.getInt("points");


            switch (typeOfQ) {
                case 1 -> {
                    //text Question
                    Question newQ = new TextQuestion(typeOfQ, quest, ans, pts);
                    quizQuestions.add(newQ);
                }
                //True/false question
                case 2 ->{
                    boolean answer = Boolean.parseBoolean(ans);
                    Question newTF = new TrueFalseQuestion(typeOfQ, quest, answer);
                    quizQuestions.add(newTF);
                }

                case 3 ->{
                    //multiple choice
                    String[] choices = rs.getString("multichoice").split(";");
                    Question newMC = new MultipleChoiceQuestion(typeOfQ, quest, ans, choices, pts);
                    quizQuestions.add(newMC);
                }
                default -> System.err.println("Question type was not recognised.");
            }
        }
        return quizQuestions;
    }

    public void insertRow(int typeOfQ, String quest, String ans, int pts, String multichoice) throws SQLException{
        String insertSQLQuestion = "INSERT INTO questions (answer, multichoice, points, question, type) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insertSQLQuestion);

        ps.setString(1, ans);
        ps.setString(2, multichoice);
        ps.setInt(3,pts);
        ps.setString(4,quest);
        ps.setInt(5,typeOfQ);

        int row = ps.executeUpdate();

        System.out.println(row);
    }
}
