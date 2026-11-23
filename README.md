# Java Quiz
This program is a quiz made in Java featuring various types of questions such as, multiple choice, true/false, and hard questions.
The quiz relies on a CSV file to load in questions and saves results to a CSV file such as the player's name and their previous scores.

## Check Java version
>[!IMPORTANT]
>Do this before running to check your version of Java
Run the following command to check for the version of Java on your computer in PowerShell:
```powershell
java -version
```
This should show you the version of Java you have currently installed.

## Download Java
If this doesn't show anything or you get an error in PowerShell go to the official website for the Java downloads and download the latest version of Java:
https://www.oracle.com/java/technologies/downloads/

## How to run 
### (Zip file):
To run this quiz from an IDE, download the zipped folder in the releases section of the repository.
Extract the folder to a suitable location such as your desktop and then open this in your chosen IDE such as IntelliJ, Visual Studio code, etc.

### (Jar Only):
To run this quiz, head over to the releases section for the JavaQuiz repository.
Once you have downloaded the jar, move it to a suitable location.
**(For ease of use please move the jar file to your desktop and not in a long file directory.)**

Link to releases for zip and jar file:
https://github.com/CodePixel-exe/JavaQuiz/releases

## Methods to run the .jar file in PowerShell:
Method 1:
Open PowerShell in windows and run the following command:
```powershell
java -jar quiz-java-1.0-SNAPSHOT.jar
```
Method 2: Should you get an error saying "Java is not recognised", run this command if Java is not in your path variables.
```powershell
 & "$env:JAVA_HOME\bin\java.exe" --enable-native-access=ALL-UNNAMED -jar .\target\quiz-java-1.0-SNAPSHOT.jar
```


