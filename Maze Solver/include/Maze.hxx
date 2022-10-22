#ifndef MAZE_HXX
#define MAZE_HXX

#include <iostream>
#include "Stack.hxx"

class Maze {
   private:
    int width;
    int height;
    Index** maze;
    Stack* PathStack = new Stack();

   public:
    Maze();
    ~Maze();
    void print();
    void setBlocked(int x, int y);
    void setPassed(int x, int y);
    void setBlank(int x, int y);
    bool isBlocked(int x, int y);
    bool isPassed(int x, int y);
    bool isBlank(int x, int y);
    bool isExit(int x, int y);
    bool isEntrance(int x, int y);
    void updateDeadEndsCount(Index current, int& deadEndsCount);
    void findNewPath(Index current, Index& next);
    void findPath();
    bool loadMaze();
    void printPath();
    void solve();
};

#endif  // !MAZE_HXX