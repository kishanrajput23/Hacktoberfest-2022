#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int main()
{
    int guess,nguess=1,num;
    srand(time(0));
    num=rand()%100+1;
   
    do
    {
        printf("Guess the right number between 1 to 100 : \n");
         scanf("%d",&guess);
    if(guess>num)
    {
        printf("LOWER NUMBER PLEASE : \n");
    }
    else if(num>guess)
    {
        printf("GREATER NUMBER PLEASE : \n");
    }
    else
    {
        printf("*CONGRATULATION !!! YOU GUESS THE RIGHT NUMBER. \n");
        printf("*YOU GUESS THE RIGHT NUMBER IN %d ATTEMPTS \n",nguess);
    }
    nguess++;
    }
    while(guess!=num);

}
