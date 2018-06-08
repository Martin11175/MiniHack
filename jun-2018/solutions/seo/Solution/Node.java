package Solution;

public class Node /*implements Comparable*/ {
	int row;
	int col;
	//int dist;
	
	public Node(int r, int c){
		row = r;
		col = c;
		//dist = Integer.MAX_VALUE;
	}
	
	/*
	@Override
	public int compareTo(Object arg0) {
		if( ((Node)arg0).dist < this.dist){
			return 1;
		}else if(((Node)arg0).dist == this.dist){
			return 0;
		}else{
			return -1;
		}
	}
	*/
	
}
