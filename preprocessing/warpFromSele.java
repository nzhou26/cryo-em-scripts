import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class warpFromSele {
	public static ArrayList <String> read(String fileRead) throws Exception{
		File file = new File (fileRead);
		Scanner sc = new Scanner(file);
		ArrayList <String> image = new ArrayList <String> ();
		while (sc.hasNextLine()) { 
			String fileContent = sc.nextLine();
			String[] elements = fileContent.split("\\s+");

			List<String> items = Arrays.asList(elements);

			if (!items.isEmpty()) {

				if (items.get(0).contains("CtfFind")){
					String path = items.get(0);
					String fileName = new File(path).getName();
					image.add(fileName);
				}
			}
		}
		sc.close();
		return image;
	}
	
	public static ArrayList <List<String>> warpRead(String fileRead, ArrayList <String> para) throws Exception{
		File file = new File (fileRead);
		Scanner sc = new Scanner(file);
		ArrayList <List<String>> content = new ArrayList <List<String>>();
		while (sc.hasNextLine()) { 
			String fileContent = sc.nextLine();
			String[] elements = fileContent.split("\\s+");
			
			List<String> items = Arrays.asList(elements);
			
			if (!items.isEmpty()) {

				if (items.get(0).contains("_rln")) {
					para.add(items.get(0));
					
				}
				if (items.size() >=5) {
					content.add(items);
				}
			}
		}
		sc.close();
		return content;
	}
	
	public static void header (String input, String output) throws Exception{
		File file = new File (input);
		Scanner sc = new Scanner(file);
		try(FileWriter fileWriter = new FileWriter(output)) {
			while (sc.hasNextLine()) {
				String fileContent = sc.nextLine();
				String[] elements = fileContent.split(" ");
				if (elements.length <=5) {
					fileWriter.write(fileContent);
					fileWriter.write("\n");
				}
		} 
	
		}catch (IOException e) {
			System.out.println("IOException");
		}
		sc.close();
	}
	
	
	public static void main (String [] args) throws Exception {
		ArrayList <String> seleImage = read(args[0]);
		ArrayList <String> para = new ArrayList<String>();
		ArrayList <List<String>> warpPart = warpRead(args[1], para);
		String output = args[2];
		header(args[1], output);
		
		
		
		try(FileWriter fileWriter = new FileWriter(output, true)) {
			for (int i = 0; i < warpPart.size(); i++) {
				for (int j = 0; j < seleImage.size(); j++) {
					if (warpPart.get(i).get(14).equals(seleImage.get(j))) {
						for (int k =0; k < warpPart.get(i).size(); k++) {
							fileWriter.write(warpPart.get(i).get(k));
							//System.out.print(warpPart.get(i).get(k));
							fileWriter.write(" ");
							//System.out.print("");
							
					    }
						fileWriter.write("\n");
						
					}
				}
				System.out.print("now on " + warpPart.get(i).get(13));

			}
			fileWriter.close();
		}catch (IOException e) {
			System.out.println("IOException");
		}

		
		
		
		
	}
	
}
