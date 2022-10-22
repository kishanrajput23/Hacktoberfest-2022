#include <iostream>
#include "../include/Maze.hxx"
using namespace std;

int main() {
    Maze* maze = new Maze();
   if (maze->loadMaze()) {
        maze->solve();
        maze->printPath();
    }
    return 0;
}