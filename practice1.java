import java.util.ArrayList;
import java.util.Collections;

public class practice1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> a =new ArrayList<>();
		a.add(2);
		a.add(5);
		a.add(1);
		Collections.sort(a);
		for(int j:a)
		{
			System.out.println(j);
		}
	}

}
