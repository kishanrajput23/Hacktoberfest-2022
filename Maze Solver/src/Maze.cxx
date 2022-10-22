#include "../include/Maze.hxx"
#include <fstream>
#include <iostream>
#include <regex>
#include <sstream>
using namespace std;

Maze::Maze() {
    width = 0;
    height = 0;
    maze = nullptr;
    PathStack = new Stack();
}

Maze::~Maze() {
    for (int i = 0; i < height; i++) {
        delete[] maze[i];
    }
    delete[] maze;
}

void Maze::print() {
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (maze[i][j].blocked) {
                std::cout << "X";
            } else if (maze[i][j].passed) {
                std::cout << "O";
            } else {
                std::cout << "-";
            }
        }
        std::cout << std::endl;
    }
}

void Maze::setBlocked(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        maze[y][x].blocked = true;
    }
}

void Maze::setPassed(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        maze[y][x].passed = true;
    }
}

void Maze::setBlank(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        maze[y][x].blocked = false;
        maze[y][x].passed = false;
    }
}

bool Maze::isBlocked(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        return maze[y][x].blocked;
    }
    cout << "Out of bounds" << endl;
    return false;
}

bool Maze::isPassed(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        return maze[y][x].passed;
    }
    cout << "Out of bounds" << endl;
    return false;
}

bool Maze::isExit(int x, int y) {
    return x == width - 1 && y == height - 1;
}

bool Maze::isEntrance(int x, int y) {
    return x == 0 && y == 0;
}

bool Maze::isBlank(int x, int y) {
    if (x >= 0 && x < width && y >= 0 && y < height) {
        return !maze[y][x].blocked && !maze[y][x].passed;
    }
    cout << "Out of bounds" << endl;
    return false;
}

void Maze::updateDeadEndsCount(Index current, int& deadEndsCount) {
    deadEndsCount = 4;
    if (current.x + 1 < width && isBlank(current.x + 1, current.y)) {
        deadEndsCount--;
    }
    if (current.y + 1 < height && isBlank(current.x, current.y + 1)) {
        deadEndsCount--;
    }
    if (current.x - 1 >= 0 && isBlank(current.x - 1, current.y)) {
        deadEndsCount--;
    }
    if (current.y - 1 >= 0 && isBlank(current.x, current.y - 1)) {
        deadEndsCount--;
    }
}

void Maze::findNewPath(Index current, Index& next) {
    if (current.x + 1 < width && isBlank(current.x + 1, current.y)) {
        next = Index(current.x + 1, current.y);
        PathStack->push(next);
        setPassed(current.x + 1, current.y);
    } else if (current.y + 1 < height && isBlank(current.x, current.y + 1)) {
        next = Index(current.x, current.y + 1);
        PathStack->push(next);
        setPassed(current.x, current.y + 1);
    } else if (current.x - 1 >= 0 && isBlank(current.x - 1, current.y)) {
        next = Index(current.x - 1, current.y);
        PathStack->push(next);
        setPassed(current.x - 1, current.y);
    } else if (current.y - 1 >= 0 && isBlank(current.x, current.y - 1)) {
        next = Index(current.x, current.y - 1);
        PathStack->push(next);
        setPassed(current.x, current.y - 1);
    }
}

void Maze::findPath() {
    Stack* DecisionStack = new Stack();
    Index current = maze[0][0];
    Index next;
    bool isDeadEnd = false;
    int deadEndCount = 0;
    while (!isExit(current.x, current.y)) {
        updateDeadEndsCount(current, deadEndCount);
        findNewPath(current, next);
        if (deadEndCount < 3) {
            DecisionStack->push(Index(current.x, current.y));
        } else if (deadEndCount == 4) {
            if (DecisionStack->isEmpty()) {
                cout << "No path found" << endl;
                PathStack->clear();
                break;
            }
            while (PathStack->peek().x != DecisionStack->peek().x &&
                   PathStack->peek().y != DecisionStack->peek().y) {
                setBlocked(PathStack->peek().x, PathStack->peek().y);
                PathStack->pop();
                if (PathStack->isEmpty()) {
                    cout << "No solution" << endl;
                    PathStack->clear();
                    return;
                }
            }
            DecisionStack->pop();
        }
        current = PathStack->peek();
        if (isExit(current.x, current.y + 1) ||
            isExit(current.x + 1, current.y)) {
            cout << "Exit" << endl;
            break;
        }
        print();
        cin.get();
    }
}

bool Maze::loadMaze() {
    std::ifstream t;
    t.open("../../question_02/data/Maze.txt", ios::in);
    std::stringstream buffer;
    buffer << t.rdbuf();
    string col = "columnsize: ";
    string row = "rowsize: ";
    // find the column size
    int colsize = buffer.str().find(col);
    int rowsize = buffer.str().find(row);
    if (colsize == string::npos || rowsize == string::npos) {
        cout << "Invalid file" << endl;
        return false;
    }
    // string to be searched
    string mystr = buffer.str();
    regex regexpcol("(columnsize: )([0-9]+)");
    regex regexprow("(rowsize: )([0-9]+)");
    smatch mcol;
    smatch mrow;
    regex_search(mystr, mcol, regexpcol);
    regex regexpheight("([0-9]+)");
    regex_search(mystr, mcol, regexpheight);
    regex_search(mystr, mrow, regexprow);
    regex regexpwidth("([0-9]+)");
    regex_search(mystr, mrow, regexpwidth);

    height = stoi(mcol.str(0));
    width = stoi(mrow.str(0));

    std::cout << endl;
    std::cout << "width: " << width << endl;
    std::cout << "height: " << height << endl;
    std::cout << endl;
    int line = 0;
    maze = new Index*[height];
    for (int i = 0; i < height; i++) {
        maze[i] = new Index[width];
    }

    // regex expression for pattern to be searched
    regex regexp("\n([0-9]+)( )([0-9]+)");

    // flag type for determining the matching behavior (in this case on string
    // objects)
    smatch m;

    // regex_search that searches pattern regexp in the string mystr
    while (regex_search(mystr, m, regexp)) {
        string height_width = m.str(0);
        regex regexp1("([0-9]+)( )");
        smatch m1;
        regex_search(height_width, m1, regexp1);
        // remove whitespace from m1.str(0)
        string height = m1.str(0);
        height.erase(remove(height.begin(), height.end(), ' '), height.end());
        regex regexp2("( )([0-9]+)");
        smatch m2;
        regex_search(height_width, m2, regexp2);
        // remove whitespace from m2.str(0)
        string width = m2.str(0);
        width.erase(remove(width.begin(), width.end(), ' '), width.end());
        setBlocked(stoi(height), stoi(width));

        // suffix to find the rest of the string.
        mystr = m.suffix().str();
    }
    setBlocked(0, 0);
    setBlocked(width - 1, height - 1);
    return true;
}

void Maze::printPath() {
    PathStack->print();
}

void Maze::solve() {
    findPath();
    print();
}