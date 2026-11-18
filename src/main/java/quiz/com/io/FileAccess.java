package quiz.com.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileAccess {

    public FileAccess(String filename){
    }

    /** File Reader
     * Reads in the files contents for the questions and answers.
     * @param filename to accept csv files
     * @return rows of the questions
     * @throws Exception if the file cannot be found.
     */
    public static ArrayList<String> readFromFile(String filename) throws Exception{
        ArrayList<String> rows = new ArrayList<>();
        BufferedReader myBuffer;
        try{
            //Extract the filename in case of a relative path like src/main/resources
            String resource = filename.substring(filename.lastIndexOf('/')+1);
            InputStream inputStream = FileAccess.class.getClassLoader().getResourceAsStream(resource);

            if (inputStream != null) {
                //Uses Standard Character sets UTF 8 to tell java to read csv files using UTF 8 encoding.
                myBuffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            } else {
                FileReader myReader = new FileReader(filename);
                myBuffer = new BufferedReader(myReader);
            }

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
            outputBuffer.write(userData + "\n");
            outputBuffer.close();
        }catch(IOException ioe){
            System.err.println("File not found." + ioe.getMessage());
        }
    }
    }


