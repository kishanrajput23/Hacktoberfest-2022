#ifndef STACK_HXX
#define STACK_HXX

#include <iostream>

struct Index {
    int x;
    int y;
    bool blocked;
    bool passed;
    Index() : x(0), y(0), blocked(false), passed(false) {}
    Index(int x, int y) : x(x), y(y), blocked(false), passed(false) {}
};

struct Node {
    Index index;
    Node* next;
    Node(Index index) : index(index), next(nullptr) {}
};

class Stack {
   private:
    Node* top;

   public:
    Stack();
    ~Stack();
    void push(Index index);
    Index pop();
    Index peek();
    bool isEmpty();
    void print();
    void clear();
};

#endif  // !STACK_HXX