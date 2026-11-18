package quiz.com.ui.questions.io;

import java.io.*;
import java.util.ArrayList;

public class FileAccess {

    public FileAccess(String filename) {
    }

    /** File Reader
     * Reads in the files contents for the questions and answers.
     * @param filename to accept csv files
     * @return rows of the questions
     * @throws Exception if the file cannot be found.
     */
    public static ArrayList<String> readFromFile(String filename) throws Exception{
        ArrayList<String> rows = new ArrayList<>();
        try{
            FileReader myReader = new FileReader(filename);
            BufferedReader myBuffer = new BufferedReader(myReader);

            String line;
            //Adds lines if null
            while((line = myBuffer.readLine()) != null){
                if (!line.trim().isEmpty()){
                    rows.add(line);
                }
            }


            myBuffer.close();


        }catch (IOException ioe){
            throw new Exception("Exception: " + ioe.getMessage());
        }
        return rows;
    }

    public static void writeToFile(String filename, String userData) throws Exception{
        try{
            FileWriter myWriter = new FileWriter(filename);
            BufferedWriter outputBuffer = new BufferedWriter(myWriter);
            outputBuffer.write(userData);
            outputBuffer.close();
        }catch(IOException ioe){
            System.err.println("File not found." + ioe.getMessage());
        }
    }
    }


