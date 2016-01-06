import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class companyurl {
	public static void main(String[] args) throws IOException, InterruptedException
	{
		CSVReader reader = new CSVReader(new FileReader("c:\\Users\\tannishk\\Downloads\\listofcompanies.csv"));
	     String [][] nextLine = new String[100000][1000];
	     int count=0;
	     while ((nextLine[count] = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	        System.out.println(nextLine[count][0]);
	        count++;
	     }
	     CSVWriter writer = new CSVWriter(new FileWriter("c:\\Users\\tannishk\\Downloads\\listofcompanies2.csv"));
	     // feed in your array (or convert your data to an array)
	     String[] entries = new String[10000];
	   //  writer.writeNext(entries);
		// writer.close();
	   String temp[] = new String[2];
	     for(int i=0;i<count;i++)
	     {
	    	 try
	    	 {
		String company = nextLine[i][0].replace(" ", "+");
		  Document doc = Jsoup.connect("http://www.bing.com/search?q="+company+"+linkedin+company").get();  
          String title = doc.title();  
          Element link = doc.select("#b_results > li:nth-child(1) > div > div > cite").first();
          if(link==null)
        	  link = doc.select("#b_results > li:nth-child(1) > div.b_attribution > cite").first();
          if(link==null)
        	  link = doc.select("#b_results > li:nth-child(2) > div > div > cite").first();
          String text = null;
          if(link.text()!=null)
          text = link.text();
          if(text.contains("company")==false)
          {
        	  Element link2=doc.select("#b_results > li:nth-child(2) > div > div > cite").first();
        	  if(link2==null)
            	  link2 = doc.select("#b_results > li:nth-child(1) > div.b_attribution > cite").first();
              if(link2==null)
            	  link2 = doc.select("#b_results > li:nth-child(2) > div.b_attribution > cite").first();
              
        	  text=link2.text();
        	  // #b_results > li:nth-child(2) > div.b_attribution > cite
        	  //#b_results > li:nth-child(1) > div.b_attribution > cite
          }
          if(text.contains("company")==true)
          {
          System.out.println(text);
          temp[0]=nextLine[i][0];
          temp[1]=text;
          }
          else
          {
           System.out.println("no such company exists");
           temp[0]=nextLine[i][0];
           temp[1]="";
          }
          writer.writeNext(temp);
          Thread.currentThread().sleep(1000);
	     } catch(Exception e)
	 	    {
		    // do nothing 	
		    }
	     }
	   
	     writer.close();
	}
}
//#b_results > li:nth-child(1) > div.b_attribution > cite
//#b_results > li:nth-child(2) > div > div > cite