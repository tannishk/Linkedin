import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
class jdbcexec
{
	Connection conn;
	void insert(inot item,String name) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/linkedin";
		String user = "root";
		String password = "root";
		conn = DriverManager.getConnection(url, user,password);
		PreparedStatement pstmt = conn.prepareStatement("insert into linking2 values(?,?,?,?,?,?,?,?,?,?);");
		pstmt.setString(1, name);
		pstmt.setString(2, item.companyname);
		pstmt.setString(3, item.content1);
		pstmt.setString(4, item.specialtags1);
		pstmt.setString(5, item.link1);
		pstmt.setString(6, item.industry1);
		pstmt.setString(7, item.headquarters1);
		pstmt.setInt(8, item.founded1);
		pstmt.setString(9, item.type1);
		pstmt.setString(10, item.size);
		pstmt.execute();
		
	}
}
class inot
{
	String companyname;
	String content1;
	String specialtags1;
	String link1;
	String industry1;
	String headquarters1;
	int founded1;
	String type1;
	String size;
}
public class index {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
	//	for(int i=1;i<=10;i++)
		//{
		//Scanner input = new Scanner(System.in);
		String htmlurl;
		String next[];
		 CSVReader reader = new CSVReader(new FileReader("c:\\Users\\tannishk\\Downloads\\listofcompanies2.csv"));
		while((next=reader.readNext())!=null)
		{
			 

				jdbcexec jb = new jdbcexec();
				inot item = new inot() ;
		String name;
		System.out.println("Enter the name");
		name =next[0];
			System.out.println("Enter the url");
			htmlurl = next[1];	
			if(htmlurl==null)
				{
				item.companyname=null;
				item.content1=null;
				item.founded1=0;
				item.headquarters1=null;
				item.headquarters1=null;
				item.link1=null;
				item.size=null;
				item.type1=null;
				item.specialtags1=null;
				jb.insert(item, name);
			//	continue;
				
				}
			if(htmlurl.contains("www")==true)
			{
				try{
				Document doc = Jsoup.connect(htmlurl).timeout(10*1000).get();
	            if(htmlurl.startsWith("www"))
	            {
	            	htmlurl.replace("www", "https://www.");
	            }
			
			/* Elements information = doc.getElementsByClass("basic-info-description");//#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-description
	      for(Element a: information )
	      {
	    	  Elements c = a.getElementsByAttribute("p");
	    	  for(Element k : c )
	    	  {
	    		 System.out.print(k.text());
	    	  }
	      }*/
			System.out.println(doc.title());
			String companyname;
			Element companyto = doc.select("#body > div.top-bar.with-wide-image.with-nav.big-logo > div.header > div.left-entity > div > h1").first();
			if(companyto==null)
			{
				companyto = doc.select("#body > div.top-bar.with-wide-image.with-nav.big-logo > div.header > div.left-entity > div > h1").first();
			}
			companyname = companyto.text();
			if(companyname!=null)
			{
			item.companyname=companyname;
			}
			System.out.print(companyname);
			Element contenttag = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-description > p").first();
			if(contenttag==null)
			{
				 contenttag = doc.select("#content > div.basic-info.viewmore-container.abbreviated > div.basic-info-description > p").first();
			}
			String content = null ;
			if(contenttag!=null)
			content = contenttag.text();
			item.content1=content;
			System.out.println("Description");
		     System.out.println(content);	
		     Element specialtiestag = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > div > p").first();
	         System.out.println("Specialities");
	         if(specialtiestag!=null)
	         {
	        	 System.out.println(specialtiestag.text());
	        	 item.specialtags1=specialtiestag.text();
	         }
	         
	         Element link = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.website > p").first();
	         if(link==null)
	         {
	        	 link = doc.select("#content > div.basic-info.viewmore-container.abbreviated > div.basic-info-about > ul > li.website > p").first();
	         }
	         if(link!=null)
	         {
	        	 System.out.println(link.text());
	        	 item.link1=link.text();
	         }
	         Element industry = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.industry > p").first();
	         if(industry==null)
	         {
	        	 industry = doc.select("#content > div.basic-info.viewmore-container.abbreviated > div.basic-info-about > ul > li.industry > p").first();
	         }
	         if(industry!=null)
	         {
	        	 System.out.println(industry.text());
	        	 item.industry1=industry.text();
	         }
	         Element headquarters = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.vcard.hq > p").first();
	         if(headquarters == null)
	         {
	        	 headquarters = doc.select("#content > div.basic-info.viewmore-container.abbreviated > div.basic-info-about > ul > li.vcard.hq > p").first();
	         }
	         if(headquarters!=null)
	         {
	         System.out.println(headquarters.text());
	         item.headquarters1=headquarters.text();
	         }
	         Element founded = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.founded > p").first();
	         if(founded==null)
	         {
	        	 founded = doc.select("#content > div.basic-info.viewmore-container.abbreviated > div.basic-info-about > ul > li.founded > p").first();
	         }
	         if(founded!=null)
	         {
	         System.out.println(founded.text());
	         item.founded1=Integer.valueOf(founded.text());
	         }
	         Element type = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.type > p").first();
	         if(type==null)
	         {
	        	 type = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.type > p").first();
	         }
	         if(type!=null)
	         {
	         System.out.println(type.text());
	         item.type1=type.text();
	         }
	         
	         Element size = doc.select("#content > div.basic-info.viewmore-container.abbreviated.with-image > div.basic-info-about > ul > li.company-size > p").first();
	         if(size==null)
	         {
	        	 size = doc.select("#content > div.basic-info.viewmore-container.abbreviated > div.basic-info-about > ul > li.company-size > p").first();
	         }
	         if(size!=null)
	         {
	         System.out.println(size.text());
	         item.size=size.text();
	         }
	         
	         
				jb.insert(item, name);
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e)
	         {
				e.printStackTrace();
	         }
			}
	       //  input.close();
		 }
		 reader.close();
		}

}
