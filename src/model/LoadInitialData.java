package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;


public class LoadInitialData
{
    public static void main(String[] args)
    {
        StudentList students = new StudentList();
        BoardGamesList boardGames = new BoardGamesList();
        String[] studentArray = null;
        String[] boardGamesArray = null;

        try
        {
            studentArray = MyFileHandler.readArrayFromTextFile("students.txt");
            boardGamesArray = MyFileHandler.readArrayFromTextFile("games.txt");
            for(int i = 0; i<studentArray.length; i++)
            {
                String temp = studentArray[i];
                String[] tempArr = temp.split(",");
                String firstName = tempArr[0];
                String lastName = tempArr[1];
                int VIAID = Integer.parseInt(tempArr[2]);

                students.addMember(new Student(firstName, lastName, VIAID));
            }
            for(int i = 0; i<boardGamesArray.length; i++){
                String temp = boardGamesArray[i];
                String[] tempArr = temp.split(",");
                String name = tempArr[0];
                String numberOfPlayers = tempArr[1];
                String description = tempArr[2];

                boardGames.addBoardGame(new BoardGame(name, numberOfPlayers, description));

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not found, or could not be opened");
        }

        try
        {
            MyFileHandler.writeToBinaryFile("students.bin", students);
            MyFileHandler.writeToBinaryFile("games.bin", boardGames);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening file ");
        }
        catch (IOException e)
        {
            System.out.println("IO Error writing to file ");
        }

        System.out.println("Done");
        System.out.println(students);
        System.out.println(boardGames);
    }
}
