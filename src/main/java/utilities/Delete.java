package utilities;

import java.io.File;
import java.io.IOException;

public class Delete {
	
	//Delete a file
    public static void DeleteAFile() {
        //get current project path
try{
    		
    		File file = new File("D:\\Rajedh\\ws\\IGP_TestAutomation\\ExecutionReports\\ExeVideo\\AutomationExecution.avi");
        	
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    			Thread.sleep(20000);
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	   
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
    
    }


}
