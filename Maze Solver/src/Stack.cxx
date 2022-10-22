#include "../include/Stack.hxx"
#include <iostream>

Stack::Stack() {
    top = nullptr;
}

Stack::~Stack() {
    while (top != nullptr) {
        pop();
    }
}

void Stack::push(Index index) {
    Node* node = new Node(index);
    node->next = top;
    top = node;
}

Index Stack::pop() {
    if (isEmpty()) {
        std::cout << "Stack is empty" << std::endl;
        return Index(-1, -1);
    }
    Node* node = top;
    top = top->next;
    Index index = node->index;
    delete node;
    return index;
}
Index Stack::peek() {
    if (isEmpty()) {
        std::cout << "Stack is empty" << std::endl;
        return Index(-1, -1);
    }
    return top->index;
}

void Stack::print() {
    Node* node = top;
    while (node != nullptr) {
        std::cout << node->index.x << ", " << node->index.y << std::endl;
        node = node->next;
    }
}

bool Stack::isEmpty() {
    return top == nullptr;
}

void Stack::clear() {
    while (top != nullptr) {
        pop();
    }
}
