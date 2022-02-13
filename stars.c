//
//  main.c
//  Counting Stars
//
//  Created by Harry James Hocker on 9/11/21.
//

// general logic:

/*
 use a floodFill recursive code to fill out the data
 return the amount of stars at the end
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>

const int DX[] = {1, -1, 0, 0};
const int DY[] = {0, 0, 1, -1};

const char space = '#';

#define MAXR 100
#define MAXC 100

// checks to see that the index is inside the values of the arrays
bool withinArray(int x, int y, int rows, int columns)
{
    if (x < 0) return false;
    if (x >= rows) return false;
    
    if (y < 0) return false;
    if (y >= columns) return false;
    
    return true;
}

// returns a 2D array from bitmap input
void getMap(char array[][MAXC], int rows, int columns)
{
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < columns; j++)
        {
            char input = '0';
            assert(scanf(" %c", &input) != 0);
            array[i][j] = input;
        }
    }
}

// fills the specified data
void floodFill(char array[][MAXC], int x, int y, int rows, int columns)
{
    if (!withinArray(x, y, rows, columns)) return;
    if (array[x][y] == space) return;
    array[x][y] = space;
    
    for (int i = 0; i < 4; i++)
    {
        floodFill(array, x+DX[i], y+DY[i], rows, columns);
    }
}


int main(int argc, const char * argv[])
{
    int numCase = 0;
    int rows = 0, columns = 0, stars = 0;
    
    
        // static array
        char bitmap[MAXR][MAXC] = {0};
    
    while(scanf("%d %d", &rows, &columns) > 0) {
        numCase++;
        stars = 0;
        getMap(bitmap, rows, columns);
        
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (bitmap[i][j] == space) continue;
                floodFill(bitmap, i, j, rows, columns);
                stars++;
            }
        }
        printf("Case %d: %d\n", numCase, stars);
    }
    return 0;
}
