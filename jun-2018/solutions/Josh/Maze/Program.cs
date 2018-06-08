using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Maze
{
    class Program
    {
        const string path = "C:\\Users\\Josh\\Documents\\MiniHack\\jun-2018";
        static bool[,] maze_visited;

        static void Main(string[] args)
        {
            // Read in mazes
            List<int[,]> mazes = readMazes(Path.Combine(path, "mazes.txt"));
            Console.WriteLine("Imported " + mazes.Count, " mazes");

            List<bool> answers = new List<bool>();

            // Check if there is a solution
            foreach (int[,] maze in mazes)
            {
                int n = maze.GetLength(0);
                maze_visited = new bool[n, n];

                bool answer = isSolution(maze, 0, 1, n);
                Console.WriteLine(answer);
                answers.Add(answer);
            }

            Console.WriteLine("Done. Checking answers...");

            bool correct = CheckResults(Path.Combine(path, "answers.txt"), answers);

            Console.Write(correct);
            Console.ReadLine();
        }

        static bool CheckResults(string path, List<bool> answers)
        {
            string line;
            StreamReader file = new StreamReader(@path);

            while ((line = file.ReadLine()) != null)
            {
                // My answer
                int an_int = 0;
                if (answers.First()) an_int = 1;
                answers.RemoveAt(0);

                if (an_int != int.Parse(line)) return false;
            }
            return true;
        }

        static List<int[,]> readMazes(string path)
        {
            string line;
            StreamReader file = new StreamReader(@path);

            // Get number mazes
            line = file.ReadLine();
            int maze_count = int.Parse(line);

            List<int[,]> maze_list = new List<int[,]>();

            while ((line = file.ReadLine()) != null)
            {
                // Get size of maze
                int size = int.Parse(line);

                int[,] maze = new int[size, size];
                for (int i = 0; i < size; i++)
                {
                    line = file.ReadLine();
                    string[] line_split = line.Split(',');
                    for (int j = 0; j < size; j++)
                    {
                        maze[i, j] = int.Parse(line_split[j]);
                    }
                }
                maze_list.Add(maze);
            }
            return maze_list;
        }

        public static bool isSolution(int[,] maze, int i, int j, int n)
        {
            // Check out of bounds
            if (i >= n || i < 0 || j >= n || j < 0) return false;
            // Mark visited
            if (maze_visited[i, j]) return false;
            // Check if a wall has been hit
            else if (maze[i, j] == 1) return false;
            // Check if end has been reached
            else if (i == (n - 1) && j == (n - 2)) return true;
            // Else check other dimension
            maze_visited[i, j] = true;
            return isSolution(maze, i + 1, j, n) || isSolution(maze, i, j + 1, n)
                || isSolution(maze, i - 1, j, n) || isSolution(maze, i, j - 1, n);

        }
    }
}
