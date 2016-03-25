import java.util.ArrayList;

public class GridProblem {

	private static ArrayList<Integer> startEndNodes = new ArrayList<Integer>();
	private static ArrayList<Integer> nodesVisited = new ArrayList<Integer>();

	public static int findYIndex(int num) {
		if (num % 5 == 0)
			return num / 5 - 1;
		return num / 5;
	}

	public static int findXIndex(int num) {
		int temp = num % 5 - 1;
		if (temp >= 0)
			return temp;
		return 4;
	}

	public static int findNextElement(int currentElement) {
		if (nodesVisited.contains(currentElement)) {
			return -1;
		}
		return currentElement;
	}

	public static int findNearestNeighbours(int currentElement, int[][] arr) {
		int X;
		int Y;
		
		X = findXIndex(currentElement);
		Y = findYIndex(currentElement);
		
		for(int i=1;i<=25;i++){
			System.out.println("X:"+findXIndex(i)+"Y:"+findYIndex(i)+"i:"+i+"arr:"+arr[findXIndex(i)][findYIndex(i)]);
		}
		int nextElement;

		if (Y + 1 < 5) {
			System.out.println("Two2");
			nextElement = findNextElement(arr[X][Y + 1]);
			if (nextElement >= 0)
				return nextElement;
		}
		if (X + 1 < 5) {
			System.out.println("One1");
			System.out.println(X+"X:Y"+Y);
			nextElement = findNextElement(arr[X + 1][Y]);
//			System.out.println(nextElement);
			if (nextElement >= 0)
				return nextElement;
		}

		if (X - 1 >= 0) {
			System.out.println("Four4");
			nextElement = findNextElement(arr[X - 1][Y]);
			if (nextElement >= 0)
				return nextElement;
		}

		if (Y - 1 >= 0) {
			System.out.println("Three3");
			nextElement = findNextElement(arr[X][Y - 1]);
			if (nextElement >= 0)
				return nextElement;
		}
		return -1;
	}

	public static void main(String[] args) {

		ArrayList<Integer> pathTraversed = new ArrayList<Integer>();

		int[][] grid = new int[5][5];
		int count = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grid[i][j] = count;
				count++;
			}
		}
		
		int[] temp = { 1, 22, 4, 17, 5, 18, 9, 13, 20, 23 };

		for (int i = 0; i < temp.length; i++) {
			startEndNodes.add(temp[i]);
		}
		nodesVisited.addAll(startEndNodes);
		int start;
		int end;
		for (int i = 0; i < 10; i += 2) {
			start = temp[i];
			end = temp[i + 1];
			int currentElement = start;
			int nextElement;
			while (currentElement != end) {
				pathTraversed.add(currentElement);
				nextElement = findNearestNeighbours(currentElement, grid);
				System.out.println("next"+nextElement);
				if (nextElement < 0) {
					System.out.println(start + "|" + end + "|" + currentElement
							+ "|" + nextElement);
					System.exit(0);
				}
				pathTraversed.add(nextElement);
				nodesVisited.add(nextElement);
				currentElement = nextElement;
			}
			for (int output : pathTraversed) {
				System.out.print(output);
			}
			pathTraversed.clear();
		}
	}

}
