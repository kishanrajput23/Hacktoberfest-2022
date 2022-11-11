#include <iostream>
#include <stdlib.h>
using namespace std;

struct node
{
    int data;
    struct node *next;
};

void trivarsal(struct node *ptr)
{
    while (ptr != NULL)
    {
        cout << "Element are :- " << ptr->data << endl;
        ptr = ptr->next;
    }
}
// case 1 --> delete at first 
// struct node *Deleteatfirst(struct node *head){
//     struct node *ptr=head;
//     head=head->next;
//     free(ptr);
//     return head;
// }

// case 2 --> deletion a element at given index
// struct node *DeleteAtIndex(struct node *head,int index){
//     struct node *ptr=head;
//     struct node *q = head->next;
//     for(int i=0;i<index-1;i++){
//         ptr->next;
//         q->next;
//     }
//     ptr->next = q->next;
//     free(q);
//     return head;



// case 3 :- delete at last element
struct node *DeleteAtLast(struct node *head){
    struct node *p = head;
    struct node *q = head->next;
    while(q->next != NULL){
        p= p->next;
        q= p->next;
    }
    p ->next = NULL;
    free(q);
    return head;
}


int main()
{
    struct node *head;
    struct node *second;
    struct node *third;
    struct node *fourth;
    struct node *fifth;
    struct node *six;
    struct node *seven;
    struct node *eight;
    head = (struct node *)malloc(sizeof(struct node));
    second = (struct node *)malloc(sizeof(struct node));
    third = (struct node *)malloc(sizeof(struct node));
    fourth = (struct node *)malloc(sizeof(struct node));
    fifth = (struct node *)malloc(sizeof(struct node));
    six = (struct node *)malloc(sizeof(struct node));
    seven = (struct node *)malloc(sizeof(struct node));
    eight = (struct node *)malloc(sizeof(struct node));

    head->data = 1;
    head->next = second;

    second->data = 2;
    second->next = third;

    third->data = 3;
    third->next = fourth;

    fourth->data = 4;
    fourth->next =fifth;

    fifth->data = 5;
    fifth->next =six;

    six->data = 6;
    six->next =seven;
    
    seven->data = 7;
    seven->next =eight;

    eight->data = 8;
    eight->next = NULL;

    cout<<"before deletion :- "<<endl;
    trivarsal(head);

    // head = Deleteatfirst(head);
    // cout<<"after deletion :- "<<endl;
    // trivarsal(head);
    // head = DeleteAtIndex(head,1);
    // cout<<"after deletion At between :- "<<endl;
    // trivarsal(head);
    head = DeleteAtLast(head);
    cout<<"after deletion At last :- "<<endl;
    trivarsal(head);
    return 0;
}
