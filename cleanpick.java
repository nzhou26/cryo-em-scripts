import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class cleanpick {
	public static ArrayList <List<String>> read(String fileRead) throws Exception{
		File file = new File (fileRead);
		Scanner sc = new Scanner(file);
		ArrayList <List<String>> content = new ArrayList <List<String>>();
		while (sc.hasNextLine()) { 
			String fileContent = sc.nextLine();
			String[] elements = fileContent.split("\\s+");
			
			List<String> items = Arrays.asList(elements);
			
			if (!items.isEmpty()) {

				if (items.size() >=20) {
					content.add(items);
				}	
			}
		}
		sc.close();
		return content;
	}
	
	public static void header (String output) throws Exception{
		try(FileWriter fileWriter = new FileWriter(output)) {
					fileWriter.write("\n");
					fileWriter.write("data_");
					fileWriter.write("\n");
					fileWriter.write("\n");
					fileWriter.write("loop_");
					fileWriter.write("\n");
					fileWriter.write("_rlnCoordinateX #1");
					fileWriter.write("\n");
					fileWriter.write("_rlnCoordinateY #2");
					fileWriter.write("\n");
					fileWriter.write("_rlnMicrographName #3");
					fileWriter.write("\n");
					fileWriter.write("_rlnAutopickFigureOfMerit #4");
					fileWriter.write("\n");
					fileWriter.write("_rlnClassNumber #5");
					fileWriter.write("\n");
					fileWriter.write("_rlnAnglePsi #6");
					fileWriter.write("\n");
					fileWriter.close();
		} 	
	}
	
	public static void contentWriter(String output, List<String> content) throws Exception {
		try (FileWriter fileWriter = new FileWriter(output, true)){
			fileWriter.write(content.get(0));
			fileWriter.write(" ");
			fileWriter.write(content.get(1));
			fileWriter.write(" ");
			fileWriter.write(content.get(2));
			fileWriter.write(" ");
			fileWriter.write(content.get(3));
			fileWriter.write(" ");
			fileWriter.write(content.get(4));
			fileWriter.write(" 0");
			fileWriter.write(" 0.000000");
			fileWriter.write("\n");
			fileWriter.close();	
		}
		
	}
	
	public static void main (String [] args) throws Exception {
		File selected = new File("clean_pick");
		if (selected.exists()) {
			System.out.println("selected already existed");
		}else {
			selected.mkdir();
			System.out.println("selected directory created");
		}
		
		ArrayList <List<String>> content = read(args[0]);
		
		for (int j = 0; j < content.size(); j++) {
			File fileName = new File(content.get(j).get(3));
			String name = fileName.getName();
			content.get(j).set(3, name);
		}
			header("clean_pick/" + content.get(0).get(3) + ".star");
			System.out.println("generating manual pick file for " + content.get(0).get(3));
			ArrayList <String> createdFile = new ArrayList<String>();
			createdFile.add(content.get(0).get(3));
		int i = 0;
		for (i = 1; i < content.size(); i++) {
			
			if (createdFile.contains(content.get(i).get(3))) {
				contentWriter("clean_pick/" + content.get(i).get(3) + ".star", content.get(i));
			}else {
				header("clean_pick/" + content.get(i).get(3) + ".star");
				System.out.println("generating manual pick file for " + content.get(i).get(3));
				contentWriter("clean_pick/" + content.get(i).get(3) + ".star", content.get(i));
				createdFile.add(content.get(i).get(3));
			}
					
		}
		System.out.println(createdFile.size() + " clean pick files created");
		System.out.println(i + " particles in total");
		//System.out.println("avreage number of particles for each image: " + (createdFile.size()/i));
		
		
	}
}
