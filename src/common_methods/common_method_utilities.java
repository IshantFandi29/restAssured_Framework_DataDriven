package common_methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_utilities {
	
	public static void evidenceFileCreator(String fileName, String request, String response) throws IOException
	{
		File newtextFile = new File("E:\\Testing classes\\restAssured_Framework\\" + fileName + ".text");
		if(newtextFile.createNewFile())
		{
			FileWriter dataWriter = new FileWriter(newtextFile);
			dataWriter.write("Requestbody is :\n" +request+ "\n\n");
			dataWriter.write("Responsebody is :\n" +response);
			dataWriter.close();
			System.out.println("request and response data is saved in :" +newtextFile.getName());
		}
		else
		{
			System.out.println(newtextFile.getName()+ "Already exist take a backup of it ! ");
		}
	}
}
