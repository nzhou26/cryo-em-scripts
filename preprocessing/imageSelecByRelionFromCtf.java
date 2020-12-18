
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class imageSelecByRelionFromCtf {
	
	
	public static ArrayList <String> read(String fileRead) throws Exception{
		File file = new File (fileRead);
		Scanner sc = new Scanner(file);
		ArrayList <String> image = new ArrayList <String> ();
		while (sc.hasNextLine()) { 
			String fileContent = sc.nextLine();
			String[] elements = fileContent.split("\\s+");

			List<String> items = Arrays.asList(elements);

			if (!items.isEmpty()) {

				if (items.get(0).contains("mrc")){
					String path = items.get(0);
					String fileName = new File(path).getName();
					image.add(fileName);
				}
			}
		}
		sc.close();
		return image;
	}
	
	
	public static void main (String [] args) throws Exception {
	
		File newDirectory = new File("selected");
		if (newDirectory.exists()) {
			System.out.println("directory already existed");
		}else {
			newDirectory.mkdir();
			System.out.println("directory created");
		};
		ArrayList <String> image = read(args[0]);		
		for (int i = 0; i <image.size(); i++) {
			

			try {

		        Files.move(Paths.get(image.get(i)), Paths.get("selected/" + image.get(i)), StandardCopyOption.REPLACE_EXISTING);
			System.out.println(image.get(i) + " moved");
		    } catch (Exception e) {
		    	System.out.println("Failed to move " +image.get(i));
		        //e.printStackTrace();
		    }
			
			
		}
		
	
	}
	

}
