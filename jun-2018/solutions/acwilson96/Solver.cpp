#include <stdio.h>
#include <vector>
#include <map>
#include <algorithm>
#include <tuple>

class Solver {

    public:

        bool debug = true;
    
        Solver() {}

        std::vector<std::tuple<int, int>> visited = {};

        bool solve(std::vector<std::vector<int>> maze) {
            this->visited = {};
            std::tuple<int,int> startNode = std::make_tuple(0, 1);
            return visitNode(maze, startNode);
        }

        bool nodeVisited(std::tuple<int, int> node) {
            int queryX = std::get<0>(node);
            int queryY = std::get<1>(node);
            for (auto entry: this->visited) {
                int currX = std::get<0>(entry); int currY = std::get<1>(entry);
                if (currX == queryX && currY == queryY)
                    return true;
            }
            int x = std::get<0>(node);
            int y = std::get<1>(node);
            return false;
        }

        void seenNode(std::tuple<int,int> node) {
            int x = std::get<0>(node);
            int y = std::get<1>(node);
            this->visited.push_back(node);
        }


        bool visitNode(std::vector<std::vector<int>> maze, std::tuple<int,int> currNode) {
            bool output = false;

            int x = std::get<0>(currNode);
            int y = std::get<1>(currNode);

            

            if (x == maze.size() -1 && y == maze[0].size() - 2) {
                return true;
            }
            seenNode(currNode);

            std::vector<std::tuple<int,int>> nodesToCheck = {};

            if (y!= 0 && maze[x][y-1] == 0) {
                std::tuple<int,int> nextNode = std::make_tuple(x, y-1);
                if (!nodeVisited(nextNode)) {
                    output = output || visitNode(maze, nextNode);
                }
            }
            
            if (x!= (maze.size() - 1) && maze[x+1][y] == 0) {
                std::tuple<int,int> nextNode = std::make_tuple(x+1, y);
                if (!nodeVisited(nextNode)) {
                    output = output || visitNode(maze, nextNode);
                }
            }
            
            if (x!= (maze[0].size() - 1) && maze[x][y+1] == 0) {
                std::tuple<int,int> nextNode = std::make_tuple(x, y+1);
                if (!nodeVisited(nextNode)) {
                    output = output || visitNode(maze, nextNode);
                }
            }

            if (x!= 0 && maze[x-1][y] == 0) {
                std::tuple<int,int> nextNode = std::make_tuple(x-1, y);
                if (!nodeVisited(nextNode)) {
                    output = output || visitNode(maze, nextNode);
                }
            }

            return output;
        }

};