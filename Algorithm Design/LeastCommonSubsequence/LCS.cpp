#include <iostream>
#include <string>
#include <fstream>
#include <cstring>


using namespace std;

  
int lcs(char* X, char* Y, int m, int n)
{
    int L[m + 1][n + 1];
    int i, j;
  
    for (i = 0; i <= m; i++) {
        for (j = 0; j <= n; j++) {
            if (i == 0 || j == 0)
                L[i][j] = 0;
  
            else if (X[i - 1] == Y[j - 1])
                L[i][j] = L[i - 1][j - 1] + 1;
  
            else
                L[i][j] = max(L[i - 1][j], L[i][j - 1]);
        }
    }  
    return L[m][n];
}
  
int max(int a, int b)
{
    return (a > b) ? a : b;
}


void search(char* X, char* Y, char* match){

    int L = strlen(X);
    int M = strlen(Y);
    int N = strlen(match);

    for (int i = 0; i <= N - L; i++) {
        int j;
 
        for (j = 0; j < L; j++)
            if (match[i + j] != X[j])
                break;
 
        if (j == L)
            cout << "Pattern found at index "
                 << i << " of sequence 1" << endl;
    }

    for (int i = 0; i <= N - M; i++) {
        int j;
 
        for (j = 0; j < M; j++)
            if (match[i + j] != Y[j])
                break;
 
        if (j == M)
            cout << "Pattern found at index "
                 << i << " of sequence 2" << endl;
    }

}


int main(){

    string sequences[2];
    char sequenceOne[29];
    char sequenceTwo[27];
    int count=0;
    ifstream myFile("dna.txt");
    if (!myFile.is_open())
        throw runtime_error("Could not open file");
    while (! myFile.eof()) 
        {     
            myFile >> sequences[count];
            count++;
        }
    myFile.close();

    strcpy(sequenceOne, sequences[0].c_str());
    strcpy(sequenceTwo, sequences[1].c_str());
    int m = strlen(sequenceOne);
    int n = strlen(sequenceTwo);
    char matchSequence[] ={'G','C','C','G','G', 'C', 'C'};

    printf("Length of LCS is %d\n", lcs(sequenceOne, sequenceTwo, m, n));

    search(sequenceOne, sequenceTwo, matchSequence);

}