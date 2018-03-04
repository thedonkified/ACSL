/*
4F9D39, DEB456, 3DA8B9, A57CA7, 26A84A, 2FCFA3, 3AAB09, 89CBF5
1, 2, L, 2
5, 3, A, 4
3, 5, B, 2
6, 7, R, 5
4, 7, L, 6
*/
import java.util.Arrays;
import java.util.Scanner;

//Isaiah Cruz
//Metuchen High School

public class Walk {

    private int[][] array;
    
public Walk()
{
}

public void init()
    {array = new int[8][8];
        
    String[] input = new String[6];
    String[] output = new String[5];
    
    Scanner s = new Scanner(System.in);
    System.out.println("INPUT:\n");
    for(int k = 0; k < input.length; k++)
        {System.out.print((k + 1) + ". ");
        input[k] = s.nextLine();
        System.out.println();
        }
    
    setupArray(parseString(input[0]));
    
    for(int k = 1; k < 6; k++)
        {output[k - 1] = parseInput(parseString(input[k]));
        }
    }

public String hexToOctal(String hex)
    {return Integer.toString(Integer.parseInt(hex, 16), 8);
    }

public String parseString(String str)
    {String fullStr = "";
    for(int k = 0; k < str.length(); k++)
        {if(str.substring(k, k + 1).equals(","))
            fullStr += " ";
        else
            fullStr += str.substring(k, k + 1);
        }
    return fullStr;
    }

public void setupArray(String str)
    {Scanner s = new Scanner(str);
    
    String[] row = new String[8];
    
    int i = 0;
    while(s.hasNext())
        {row[i] = s.next();
        i++;
        }
    
    for(int k = 0; k < 8; k++)
        {for(int j = 0; j < 8; j++)
            {array[k][j] = Integer.parseInt(row[k].substring(j, j + 1));
            }
        }
    }

public String parseInput(String str)
    {Scanner s = new Scanner(str);
    int row = s.nextInt();
    int col = s.nextInt();
    
    String dirStr = s.next();

    int oldRow = 0;
    int oldCol = 0;
    
    int iterationNum = 0;
    
    switch(dirStr)
        {case "L":
            {oldRow = row;
            oldCol = col + 1;
            if(oldCol > 7)
                oldCol = 0;
            break;
            }
        case "R":
            {oldRow = row;
            oldCol = col - 1;
            if(oldCol < 0)
                oldCol = 7;
            break;
            }
        case "A":
            {oldRow = row - 1;
            oldCol = col;
            if(oldRow < 0)
                oldRow = 7;
            break;
            }
        case "B":
            {oldRow = row + 1;
            oldCol = col;
            if(oldRow > 7)
                oldRow = 0;
            break;
            }
        }
    
    iterationNum = s.nextInt();
    
    int[] result = followOrders(row, col, oldRow, oldCol, iterationNum);
    return result[0] + ", " + result[1];
    }

public int findDir(int row, int col, int oldRow, int oldCol)
    {int rowDiff = oldRow - row;
    int colDiff = oldCol - col;
    
    boolean N = false;
    boolean S = false;
    boolean E = false;
    boolean W = false;
    
    if(rowDiff == 1)
        E = true;
    else if(rowDiff == -1)
        W = true;
    
    if(colDiff == 1)
        N = true;
    else if(colDiff == -1)
        S = true;
    
    if(E)
        {if(N)
            return 1;
        else if(S)
            return 7;
        else
            return 0;
        }
    else if(W)
        {if(N)
            return 3;
        if(S)
            return 5;
        else 
            return 4;
        }
    else
        {if(N)
            return 2;
        else
            return 6;
        }
    }

public int[] followOrders(int row, int col, int oldRow, int oldCol, int iterationNum)
    {if(iterationNum == 0)
        return new int[]{row, col};
    int dir = findDir(row, col, oldRow, oldCol);
    int newRow = 0;
    int newCol = 0;
    
    int arrayVal = array[row][col];
    dir = Math.abs((dir - arrayVal) % 8);
    
    switch(dir)
        {case 0:
            {newRow = row;
            newCol = col + 1;
            if(newCol > 7)
                {newCol = 0;
                }
            break;
            }
        case 1:
            {newRow = row + 1;
            if(newRow > 7)
                {newRow = 0;
                }
            newCol = col + 1;
            if(newCol > 7)
                {newCol = 0;
                }
            break;
            }
        case 2:
            {newRow = row + 1;
            if(newRow > 7)
                {newRow = 0;
                }
            newCol = col;
            break;
            }
        case 3:
            {newRow = row + 1;
            if(newRow > 7)
                {newRow = 0;
                }
            newCol = col - 1;
            if(newCol < 0)
                {newCol = 7;
                }
            break;
            }
        case 4:
            {newRow = row;
            newCol = col - 1;
            if(newCol < 0)
                {newCol = 7;
                }
            break;
            }
        case 5:
            {newRow = row - 1;
            if(newRow < 0)
                {newRow = 7;
                }
            newCol = col - 1;
            if(newCol < 0)
                {newCol = 7;
                }
            break;
            }
        case 6:
            {newRow = row - 1;
            if(newRow < 0)
                {newRow = 7;
                }
            newCol = col;
            break;
            }
        case 7:
            {newRow = row - 1;
            if(newRow < 0)
                {newRow = 7;
                }
            newCol = col + 1;
            if(newCol > 7)
                {newCol = 0;
                }
            break;
            }
        }
    
    return followOrders(newRow, newCol, row, col, iterationNum--);
    }

public static void main(String[] args)
    {new Walk().init();
    }
    
}
