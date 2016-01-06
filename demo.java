import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class demo {

	public static void main(String[] args)
	{
		 ArrayList<Integer> a= new ArrayList<>();
		 Scanner input = new Scanner(System.in);
		 int size=input.nextInt();
		 int inr;
		 int index=0;
		 for(int i=0;i<size;i++)
		 {
			 inr = input.nextInt();
			 a.add(inr);
		 }
		 index = a.get(0)+4;
		 int value=1;
		Collections.sort(a);
		for(int i=1;i<a.size() ;i++)
		{
			inr = a.get(i);
			if(inr<=index)
			{
				//skip
			}
			else
			{
				index = inr+4;
				value++;
			}
		}
		System.out.println(value);
	}
}
