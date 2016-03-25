import java.util.ArrayList;


public class ListAddition {
	public static ArrayList<Integer> newList = new ArrayList<Integer>();
	
	public static void addList(ArrayList<Integer> list){
		
		if(list.size() == 1)
			return;
		else
		{
			list.set(1, list.get(0)+list.get(1));
			list.remove(0);
			System.out.println(list.get(0));
			addList(list);
		}
	}
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			newList.add(i+1);
		}
		addList(newList);
		System.out.println(newList.get(0));
	}

}
