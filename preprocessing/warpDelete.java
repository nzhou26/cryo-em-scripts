import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class warpDelete {
	
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
	
		File selected = new File("selected");
		if (selected.exists()) {
			System.out.println("selected already existed");
		}else {
			selected.mkdir();
			System.out.println("selected directory created");
		};
		
		/**File removed = new File("removed");
		if (removed.exists()) {
			System.out.println("removed directory already existed");
		}else {
			removed.mkdir();
			System.out.println("removed created");
		};
		**/
		//System.out.println(args[0]);
		

		ArrayList <String> image = read(args[0]);		
		for (int i = 0; i < image.size(); i++){
			String trim = image.get(i).substring(0, (image.get(i).length() - 4));
			//String [] parse = trim.split("_");
			//String underscore = parse[0] + "_" + parse[1] + "_" + parse[2] + "_" + parse[3] + "_" + parse[4] + "." + parse[5] + "." + parse[6];
			String underscore = trim.substring(0, (trim.length() - 6)) + "." + trim.substring((trim.length() - 5 ), (trim.length() - 3 )) + "." + trim.substring((trim.length() - 2 ), trim.length());
			String tif = underscore + ".tif";
			image.set(i, tif);
			

		}

		for (int i = 0; i <image.size(); i++) {
			//System.out.println(image.get(i));

			try {
			//System.out.println(Paths.get(image.get(i)));
			System.out.println(Paths.get("selected/" + image.get(i)));
		        Files.move(Paths.get(image.get(i)), Paths.get("selected/" + image.get(i)), StandardCopyOption.REPLACE_EXISTING);

		    } catch (Exception e) {
		    	System.out.println("Failed to move " +image.get(i));
		      
		        //e.printStackTrace();
		    }
			
			
		}
		
	
	}
	
}
