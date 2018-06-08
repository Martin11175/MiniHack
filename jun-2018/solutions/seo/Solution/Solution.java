package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
	ArrayList<int[][]> mazes = new ArrayList<>();
	//int[row][col]
	ArrayList<Integer> ans = new ArrayList<>();
	
	public Solution(String file) throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader(file));
		int howmany = Integer.parseInt(rd.readLine());
		for(int i=0; i<howmany; i++){
			int lines = Integer.parseInt(rd.readLine());
			int[][] maze = new int[lines][lines];
			for(int j=0; j<lines; j++){
				String[] temp = rd.readLine().split(",");
				for(int k=0; k<lines; k++){
					maze[j][k] = Integer.parseInt(temp[k]);
				}
			}
			mazes.add(maze);
		}
	}
	
	public ArrayList<Node> neighbour(Node n, int[][] maze){
		ArrayList<Node> temp = new ArrayList<>();
		//up
		if(n.row != 0 && maze[n.row-1][n.col] == 0){
			temp.add(new Node(n.row-1, n.col));
		}
		//down
		if(n.row != maze.length-1 && maze[n.row+1][n.col] == 0){
			temp.add(new Node(n.row+1, n.col));
		}
		//left
		if(n.col != 0 && maze[n.row][n.col-1] == 0){
			temp.add(new Node(n.row, n.col-1));
		}
		//right
		if(n.col != maze.length-1 && maze[n.row][n.col+1] == 0){
			temp.add(new Node(n.row, n.col+1));
		}
		return temp;
	}
	
	public int solve(int[][] maze){
		//PriorityQueue<Node> q = new PriorityQueue<>();
		LinkedList<Node> q = new LinkedList<>();
		int[][] visited = new int[maze.length][maze.length];
		for(int i=0; i<visited.length; i++){
			for(int j=0; j<visited.length; j++){
				visited[i][j] = 0;
			}
		}
		
		Node s = new Node(0,1); //row 0 col 1 (second one on the first row)
		q.add(s);
		while(!q.isEmpty()){
			Node n = q.poll();
			visited[n.row][n.col] = 1;
			if(n.row == maze.length-1 && n.col == maze.length - 2){
				return 1;
			}else{
				ArrayList<Node> neigh = neighbour(n, maze);
				for(int k=0; k < neigh.size(); k++){
					Node bour = neigh.get(k);
					if(visited[bour.row][bour.col] == 1){
						neigh.remove(bour);
					}
				}
				q.addAll(neigh);				
			}
		}
		return 0;
	}
	
	public void solveAll() throws IOException{
		File f = new File("myAns.txt");
		FileWriter wrrr = new FileWriter("asdf.txt");
		/*
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		*/
		for(int[][] maze : mazes){
			int x = solve(maze);
			System.out.println(x);
			wrrr.write(x + "\n");
		}
		wrrr.close();
		System.out.println("done");
	}

	public void print(){
		for(int[][] maze : mazes){
			for(int[] line : maze){
				for(int c : line){
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args){
		try {
			Solution sol = new Solution("mazes_small.txt");
			Solution sol2 = new Solution("mazes.txt");
			//sol.print();
			sol.solveAll();
			sol2.solveAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
