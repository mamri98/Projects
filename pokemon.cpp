#include <string>
#include <fstream>
#include <vector>
#include <utility>
#include <stdexcept>
#include <sstream>
#include <iostream>

using namespace std;

void insertionSort(vector<int> &vect, int n, int &comparisons) {
    
    int j, key, k;
    
    for (j = 1; j < n; j++)
    {
        key = vect[j];
        k = j - 1;

        while (k >= 0 && vect[k] > key)
        {
            vect[k + 1] = vect[k];
            k = k - 1;
            comparisons += 1;
        }
        vect[k + 1] = key;
    }
}


void merge(vector<int> &vect, int const left, int const mid, int const right)
{
    auto const subArrayOne = mid - left + 1;
    auto const subArrayTwo = right - mid;
  
    // Create temp arrays
    auto *leftArray = new int[subArrayOne],
         *rightArray = new int[subArrayTwo];
  
    // Copy data to temp arrays leftArray[] and rightArray[]
    for (auto i = 0; i < subArrayOne; i++)
        leftArray[i] = vect[left + i];
    for (auto j = 0; j < subArrayTwo; j++)
        rightArray[j] = vect[mid + 1 + j];
  
    auto indexOfSubArrayOne = 0, // Initial index of first sub-vect
        indexOfSubArrayTwo = 0; // Initial index of second sub-vect
    int indexOfMergedArray = left; // Initial index of merged vect
  
    // Merge the temp arrays back into vect[left..right]
    while (indexOfSubArrayOne < subArrayOne && indexOfSubArrayTwo < subArrayTwo) {
        if (leftArray[indexOfSubArrayOne] <= rightArray[indexOfSubArrayTwo]) {
            vect[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
            indexOfSubArrayOne++;
        }
        else {
            vect[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
            indexOfSubArrayTwo++;
        }
        indexOfMergedArray++;
    }
    // Copy the remaining elements of
    // left[], if there are any
    while (indexOfSubArrayOne < subArrayOne) {
        vect[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
        indexOfSubArrayOne++;
        indexOfMergedArray++;
    }
    // Copy the remaining elements of
    // right[], if there are any
    while (indexOfSubArrayTwo < subArrayTwo) {
        vect[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
        indexOfSubArrayTwo++;
        indexOfMergedArray++;
    }
}


void mergeSort(vector<int> &vect, int const begin, int const end, int &comparisons)
{
    comparisons += 1;
    if (begin >= end) {
        return;
    }
    
    auto mid = begin + (end - begin) / 2;
    mergeSort(vect, begin, mid, comparisons);
    mergeSort(vect, mid + 1, end, comparisons);
    merge(vect, begin, mid, end);
}


void swap(int* a, int* b) 
{ 
    int t = *a; 
    *a = *b; 
    *b = t; 
} 


int partition (vector<int> &vect, int low, int high) 
{ 
    int pivot = vect[high];
    int i = (low - 1);
  
    for (int j = low; j <= high - 1; j++) 
    { 
        if (vect[j] < pivot) 
        { 
            i++;
            swap(&vect[i], &vect[j]); 
        } 
    } 
    swap(&vect[i + 1], &vect[high]); 
    return (i + 1); 
} 
  

void quickSort(vector<int> &vect, int low, int high, int &comparisons) 
{ 
    if (low < high) 
    { 
        comparisons += 1;
        int pi = partition(vect, low, high); 
  
        quickSort(vect, low, pi - 1, comparisons); 
        quickSort(vect, pi + 1, high, comparisons); 
    } 
    
} 
 

void printSorted(vector<int> vect){
 for (vector<int>::const_iterator i = vect.begin(); i != vect.end(); i++)
        cout << *i << ' ';
}



int main(){

    vector<int> pokePower;
    ifstream myFile("pokemonSortedSmall.csv");
    string line;   
    if (!myFile.is_open())
        throw runtime_error("Could not open file");
    int val;
    int count = 0;
    while (getline(myFile, line)){
        if (count > 0) {
            int pow;
            stringstream s_stream(line);
            vector<string> result;
            while (s_stream.good()) {
                string substr;
                getline(s_stream, substr, ',');
                result.push_back(substr);
            }
            pokePower.push_back(stoi(result[1]));
        }
        count++;
    }
    
    int n = pokePower.size();
    int comparisonQ, comparisonI, comparisonM = 0;
    
    insertionSort(pokePower, n, comparisonI);
    //cout << "InsertionSort comparisons: " << comparisonI << endl;

    mergeSort(pokePower, 0, n - 1, comparisonM);
    //cout << "MergeSort comparisons: " << comparisonM << endl;

    quickSort(pokePower, 0, n - 1, comparisonQ); 
    //cout << "QuickSort comparisons: " << comparisonQ << endl;

    printSorted(pokePower);
   
    
    return 0;

}