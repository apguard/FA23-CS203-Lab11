import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Encrypter {

	StringBuffer fun = new StringBuffer();
    private String encrypted;
    
    static ArrayList<String> list = new ArrayList<String>();

int shift=4;
    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	/**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	readFile(inputFilePath);
    	 fun.delete(0, fun.length());
    	
    	try(PrintWriter output = new PrintWriter(encryptedFilePath)){
   	 for( String element : list ) {
    		 char [] ch = element.toCharArray();
    		 String el = element;
    		 encrypted = "";
    	for (int i = 0; i< ch.length; i++ ) {
    		
    	    if(Character.isUpperCase(ch[i])) {
    	    	
    	    	char ch1 =  (char) (((int ) el.charAt(i) + shift-65) %26+65 ); 	
    	    	fun.append(ch1);
    	    	
    	}
 
    	    
    	    else if(Character.isLowerCase(ch[i])) {
    	    	char ch1 = (char) (((int)el.charAt(i) + shift -97) % 26 + 97);
    	    	fun.append(ch1);
    	    	
    	    }
    	    else {
    	    	
    	    	fun.append(ch[i]);
    	    }
    	    
    	     
    	  }
    	 }
    	 output.println(fun);
     	
    	 output.close();
    	}
    		
    	catch (Exception e) {
    		System.out.println("error" + e.toString());
    	}
    	   //
 
    }
    
    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	fun.delete(0, fun.length());
    	
    	readFile(messageFilePath);
    	//System.out.println(encrypted);
    	
    	try(PrintWriter output = new PrintWriter(decryptedFilePath)){
    		
    	
    	 setShift(26-shift);
    	 
    	 for( String element : list ) {
    		 char [] ch = element.toCharArray();
    		 String el = element;
    		 encrypted = "";
    		 System.out.println(fun);
    	for (int i = 0; i< ch.length; i++ ) {
    		
    	    if(Character.isUpperCase(ch[i])) {
    	    	
    	    	char ch1 =  (char) (((int ) el.charAt(i) + shift-65) % 26+65 ); 	
    	    	fun.append(ch1);
    	    }
    	    	
    	    	    else if(Character.isLowerCase(ch[i])) {char ch1 = (char) (((int)el.charAt(i) + shift -97) % 26 + 97);
        	    	fun.append(ch1);}
    	    
    	    else {
    	    	fun.append(ch[i]);
    	    	
    	    	
    	    }
    	     
    	  }
    	 }
    	 output.println(fun);
     	
    	 output.close();
    	}
    		
    	catch (Exception e) {
    		System.out.println("error" + e.toString());
    	}
    	   //
    	 
    	
    }
    

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        String line = "";
        list.clear();     
        try(Scanner fileScanner = new Scanner(Paths.get(filePath))) {
        while(fileScanner.hasNextLine()) {
        	 line = fileScanner.nextLine();
        	message = line;
        	
        		list.add(line);
        }
        //TODO: Read file from filePath
        fileScanner.close();
        }
        catch(Exception e) {
        	System.out.println("Error" + e.toString());
        }
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try(PrintWriter output = new PrintWriter(filePath)){
    		output.write(data);
    	output.close();	
    	}
    	catch (Exception e) {
    		System.out.println("error" + e.toString());
    	}
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
