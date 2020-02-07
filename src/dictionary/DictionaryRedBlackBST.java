package dictionary;

import java.io.File;
import java.util.Scanner;

import redBlackBST_DS.RedBlackBST;
import redBlackBST_DS.RedBlackBST.ResizableCanvas;

public class DictionaryRedBlackBST {

	private static RedBlackBST<String,String> dictionaryTree = new RedBlackBST<String, String>(); 

	//Load Dictionary
	public static void loadDictionary(String fileName){
			
//		dictionaryTree = new RedBlackBST<String, String>();
		 File file = new File(fileName);
		 
		  
		  try {
			  	Scanner in = new Scanner(file);
			  	String line;
			  	String []wordDef;
//			  	String definition;
			  	
			  	while(in.hasNextLine()){
			  		
			  		line = in.nextLine();
			  		//wordDef[0]-> word   wordDef[1]-> definition
			  		wordDef = line.split(" > ");
			  		if(wordDef.length==1)
			  			dictionaryTree.put(wordDef[0], "No Definition");
			  		else
			  			dictionaryTree.put(wordDef[0], wordDef[1]);
			  	}
			  	in.close();
		     } 
		     
			catch (Exception e) {
		  
		        	 e.printStackTrace();
		   
		    } 

	}

	//Print dictionary size
	public static int dictionarySize(){
		return dictionaryTree.size();
	}
	
	//Graphically display Dictionary tree
	public static ResizableCanvas GraphicDisplay(){
		return dictionaryTree.draw();
	}
	
	//Print Dictionary tree height
	public static int dictionaryHeight(){
		return dictionaryTree.longestPath();
	}
	
	//Insert a word
	public static void insert(String word, String value){
		dictionaryTree.put(word, value);
	}
	
	//LookUp a word
	public static String lookUp(String key){
		
		return (String)dictionaryTree.get(key);
	}
	
	//Remove a word
	public static void remove(String key){
//		dictionaryTree.deleteMin();
		dictionaryTree.delete(key);
	}
	
	//Remove min word
	public static void removeMin(){
		dictionaryTree.deleteMinTree();
	}
	
	//remove max word
	public static void removeMax(){
		dictionaryTree.deleteMax();
	}
	
	
	
}
