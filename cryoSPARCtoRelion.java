import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class cryoSPARCtoRelion {
	public static ArrayList <List<String>> read(String fileRead) throws Exception{
		File file = new File (fileRead);
		Scanner sc = new Scanner(file);
		ArrayList <List<String>> content = new ArrayList <List<String>>();
		while (sc.hasNextLine()) { 
			String fileContent = sc.nextLine();
			String[] elements = fileContent.split("\\s+");
			
			List<String> items = Arrays.asList(elements);
			if (!items.isEmpty()) {

				if (items.size() >=15) {
					content.add(items);
				}
				
			}
		}
		sc.close();
		return content;
	}
	
	public static void header (String input, String output) throws Exception{
		try(FileWriter fileWriter = new FileWriter(output)) {
			fileWriter.write("\n");
		File file = new File (input);
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) { 
			String fileContent = sc.nextLine();
			String[] elements = fileContent.split("\\s+");
			List<String> items = Arrays.asList(elements);
			if (!items.isEmpty()) {
				if (items.size() >=15) {
					sc.close();
					break;
					
				}
			}
			fileWriter.write(fileContent);
			fileWriter.write("\n");
		}
		}
	}
	
	
	public static void main (String [] args) throws Exception {
		ArrayList <List<String>> contentr = read(args[0]);
		ArrayList <List<String>> contentc = read(args[1]);
		//System.out.println(contentr.size());
		//System.out.println(contentc.size());
		String output = args[2];
		header(args[0], output);
		try (FileWriter fileWriter = new FileWriter(output, true)){
			int j = 0;
			int i =0;
			int count = 0;
			ArrayList <String> cryo = new ArrayList <String>();
			for (i = 0; i < contentc.size(); i++) {
				String[] elementsc = contentc.get(i).get(9).split("@");	
				File fileNamec = new File(elementsc[1]);
				String namec = fileNamec.getName();
				String esc = elementsc[0] + "@" + namec;
				cryo.add(esc);
			}
			
			for (j = 0; j < contentr.size(); j ++){
				System.out.println("i"+ i+ "j"+j);
				String[] elementsr = contentr.get(j).get(5).split("@");
				//System.out.println(elementsr[0]);				
				File fileNamer = new File(elementsr[1]);
				String namer = fileNamer.getName();
				String esr = elementsr[0] + "@" + namer;
				System.out.println(esr);
				if(cryo.contains(esr)) {
					for (int k =0; k < contentr.get(j).size(); k ++) {
						fileWriter.write(contentr.get(j).get(k));
						fileWriter.write(" ");
					}
					fileWriter.write("\n");
					//j = j +1;
					count = count +1 ;
					//break;
				}
				//j = j +1;
			}
			System.out.println("number of particles in " + args[0] + ": " + contentr.size());
			System.out.println("number of particles in " + args[1] + ": " + contentc.size());
			System.out.println("number of particles genreated in " + args[2] + ": " + count);
			
		}
	}
	
						
				
}
