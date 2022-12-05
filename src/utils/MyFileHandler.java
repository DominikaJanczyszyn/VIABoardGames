package utils;

import parser.ParserException;
import parser.XmlJsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class crated to manage reading and writing to binary files
 * @author Dominika Janczyszyn
 * @version 1.0
 */

public class MyFileHandler {
    /**
     * Writes the given object to a file with the given file name
     * @param fileName name of the file
     * @param obj object that will be save to a file
     * @throws FileNotFoundException exception that is thrown when program can't find a file
     * @throws IOException exception that is thrown when IO exception accrued
     */
    public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException
    {
        ObjectOutputStream writeToFile = null;

        try
        {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        }
        finally
        {
            if (writeToFile != null)
            {
                try
                {
                    writeToFile.close();
                }
                catch (IOException e)
                {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    /**
     * Read Object from a file with  given file name
     * @param fileName name of the file
     * @return obj Object that is in the file
     * @throws FileNotFoundException exception that is thrown when program can't find a file
     * @throws IOException exception that is thrown when IO exception accrued
     * @throws ClassNotFoundException exception that is thrown when class is not found
     */
    public static Object readFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try
        {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            try
            {
                obj = readFromFile.readObject();
            }
            catch (EOFException eof)
            {
                //Done reading
            }
        }
        finally
        {
            if (readFromFile != null)
            {
                try
                {
                    readFromFile.close();
                }
                catch (IOException e)
                {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return obj;
    }
//////////////////////////////////////////////////Testing///////////////////////////////////////
    public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException
    {
        Scanner readFromFile = null;
        ArrayList<String> strs = new ArrayList<String>();

        try
        {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);

            while (readFromFile.hasNext())
            {
                strs.add(readFromFile.nextLine());
            }
        }
        finally
        {
            if (readFromFile != null)
            {
                readFromFile.close();
            }
        }

        String[] strsArray = new String[strs.size()];
        return strs.toArray(strsArray);
    }
    /**
     * Writes given object to a XML file with a given name
     * @param fileName name of the file
     * @param obj object that will be saved to a file
     * @throws ParserException exception which is thrown when obj cannot be parsed into file
     */
    public static void writeToXMLFile(String fileName, Object obj) throws ParserException
    {
        XmlJsonParser parser;

        try
        {
            parser = new XmlJsonParser();
            parser.toXml(obj, fileName);
        }
        catch (ParserException e)
        {
            System.out.println("Parser Error");
        }
    }
}
