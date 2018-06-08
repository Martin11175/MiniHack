#include <stdio.h>
#include <vector>
#include "Solver.cpp"
#include <iostream>
#include <sstream>
#include <string>

#include <string>
#include <iostream>
#include <sstream>
#include <fstream>

using namespace std;

std::vector<std::vector<std::vector<int>>> tests = {};


void printMaze(vector<vector<int>> maze) {
    fprintf(stderr, "\nMaze Size: %lu\n", maze.size());
    for (vector<int> line: maze) {
        for (int elem: line) {
            fprintf(stderr, "%d,", elem);
        }
        fprintf(stderr, "\n");
    }
}

void addToTests(vector<vector<int>> maze) {
    tests.push_back(maze);
}


int main(int argc, char *argv[]) {

    Solver *s = new Solver();

    

    int state = 0;
    std::ifstream file("../../mazes.txt");
    std::string line; 

    int mazeDim;
    vector<vector<int>> currMaze;
    while (std::getline(file, line))
    {
        // Skip First Line
        if (state == 0) {
            state = 1;
            continue;
        }
        else if (state == 1) {
            mazeDim = atoi(line.c_str());

            currMaze =  {};
            state = 2;
            
        }
        else if (state == 2) {
            mazeDim--;

            vector<int> currLine = {};
            for (char c: line) {
                if (c == ',')
                    continue;
                else {
                    currLine.push_back(c - '0');
                }
            }
            currMaze.push_back(currLine);

            if (mazeDim == 0) {
                addToTests(currMaze);
                state = 1;
            }
        }
    }

    // for (auto test: tests) {
    //     printMaze(test);
    // }

    s->debug = false;
    for (int i = 0; i < tests.size(); i++) {
        auto test = tests[i];
        fprintf(stderr, "\n --- MAZE %d ---", i);
        printf("\n\t%i\n", s->solve(test));
    }
    // printf("\n%i", s->solve(tests[0]));
    // printf("\n%i", s->solve(tests[1]));
    // printf("\n%i", s->solve(tests[2]));

    return 1;
}