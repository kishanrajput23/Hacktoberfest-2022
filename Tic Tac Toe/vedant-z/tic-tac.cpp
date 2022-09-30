#include <iostream>
using namespace std;

void ticGrid()
{

    cout << "                 "
         << "Tic-Tac" << endl;
    cout << "_____________________________________________" << endl;

    cout << endl;

    cout << "+"
         << " "
         << "|"
         << " "
         << "o"
         << " "
         << "|"
         << " "
         << "o"
         << "        "
         << "Computer : +" << endl;

    cout << "---------" << endl;

    cout << "o"
         << " "
         << "|"
         << " "
         << "+"
         << " "
         << "|"
         << " "
         << "+"
         << "        "
         << "Human : o" << endl;

    cout << "---------" << endl;

    cout << "*"
         << " "
         << "|"
         << " "
         << "o"
         << " "
         << "|"
         << " "
         << "+" << endl;

    cout << endl;
}

void tacGrid(int a[3][3])
{

    cout << endl;

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (a[i][j] == 1)
                cout << "+"
                     << " ";
            else if (a[i][j] == 2)
                cout << "o"
                     << " ";
            else
                cout << "."
                     << " ";
        }
        cout << endl;
    }

    cout << endl;
}

int win(int a[3][3])
{
    if (a[0][0] == a[1][1] && a[1][1] == a[2][2] && a[0][0] == 2)
        return 1;
    else if (a[0][2] == a[1][1] && a[1][1] == a[2][0] && a[0][2] == 2)
        return 1;
    else
    {
        for (int i = 0; i < 3; i++)
        {

            if (a[i][0] == a[i][1] && a[i][1] == a[i][2] && a[i][0] == 2)
            {
                return 1;
            }
            else if (a[0][i] == a[1][i] && a[1][i] == a[2][i] && a[0][i] == 2)
            {
                return 1;
            }
        }
        return 0;
    }
}

int lose(int a[3][3])
{
    if (a[0][0] == a[1][1] && a[1][1] == a[2][2] && a[0][0] == 1)
        return 1;
    else if (a[0][2] == a[1][1] && a[1][1] == a[2][0] && a[0][2] == 1)
        return 1;
    else
    {
        for (int i = 0; i < 3; i++)
        {

            if (a[i][0] == a[i][1] && a[i][1] == a[i][2] && a[i][0] == 1)
            {
                return 1;
            }
            else if (a[0][i] == a[1][i] && a[1][i] == a[2][i] && a[0][i] == 1)
            {
                return 1;
            }
        }
        return 0;
    }
}

int main()
{

    ticGrid();

    int a[3][3] = {0};
    a[1][1] = 1;

    cout << "Initial environment 1" << endl;

    tacGrid(a);

    int c = 9;

    while (--c)
    {
        if (c % 2 == 0)
        {
            int x, y;

            cout << "----->Your chance" << endl;

            cin >> x >> y;
            --x;
            --y;
            a[x][y] = 2;

            tacGrid(a);

            if (win(a))
            {
                cout << "Yeah ! YOU WON :)" << endl;
                break;
            }
        }
        else
        {
            cout << "----->Bot played" << endl;
            int c = 0;
            for (int i = 0; i < 3; i++)
            {
                c = 0;
                for (int j = 0; j < 3; j++)
                {
                    if (a[i][j] == 0)
                    {
                        a[i][j] = 1;
                        c = 1;
                        break;
                    }
                }
                if (c == 1)
                    break;
            }

            tacGrid(a);

            if (lose(a))
            {
                cout << "YOU LOST!! :(" << endl;
                cout << "Better luck next time." << endl;
                break;
            }
        }
    }

    // cout << "Final Condition...." << endl;

    if (win(a) == 0 && lose(a) == 0)
    {
        cout << "GAME TIED" << endl;
        tacGrid(a);
    }

    return 0;
}
