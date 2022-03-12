#include "Tree.h"
#include <fstream>
#include <string>
#include <iostream>


using namespace std;
 

BST::BST(){                 // constructor
    data = 0;
    left = NULL;
    right = NULL;
}
 

BST::BST(int key)           // constructor
{
    data = key;
    left = NULL;
    right = NULL;
}

BST* BST::search(BST* root, int key)
{
    if(root == NULL)                        // if key not found
        return NULL;
    if (root->data == key){                 // if key found
        cout << "found!";
       return root;
    }
    else if (key > root->data)              // if larger, search into right subtree
       return search(root->right, key);
 
    else
        return search(root->left, key);     // else, search through left subtree
    
}
 

BST* BST::insert(BST* root, int key)
{
    if (root == NULL)                   
        return new BST(key);

    if (key > root->data)                       // if current num greater than current root, insert into right subtree
        root->right = insert(root->right, key);
    else
        root->left = insert(root->left, key);
 
    return root;
}
 

void BST::inOrder(BST* root)            // print in-order
{
    if (root == NULL) 
        return;
    
    inOrder(root->left);
    cout << root->data << endl;
    inOrder(root->right);
}
 
void BST::preOrder(BST* root)           // print pre-order
{
    if (root == NULL) 
        return;
    
    cout << root->data << endl;
    preOrder(root->left);
    preOrder(root->right);
}

void BST::postOrder(BST* root)          // print in post-order
{
    if(root == NULL)
        return;

    postOrder(root->left);
    postOrder(root->right);
    cout << root->data << endl;
}

int BST::findMax(BST* root)             // find max value
{
    if(root->right == NULL)             // no more right data nodes
        return root->data;
    else
        return findMax(root->right);
}

int BST::findMin(BST* root)             // find minimum value
{
    if(root->left == NULL)              // no more left data nodes
        return root->data;
    else
        return findMin(root->left);
}

void maxHeapify(int arr[], int size, int root)         //
{
    int largest = root;                    // current root is the largest element
    int left = 2 * root + 1;               // 0 index sequencing
    int right = 2 * root + 2; 
    
    
    if (left < size && arr[left] > arr[largest])        // left node larger than current largest?
        largest = left;

    if (right < size && arr[right] > arr[largest])      // same with right
        largest = right;
  
    if (largest != root)                                // if either one of the above occured, recurse and swap roots
    {
        swap(arr[root], arr[largest]);
        maxHeapify(arr, size, largest);
    }
}


void createMaxHeap(int arr[], int size)
{

    int startIndex = (size / 2) - 1;               // index starts from the last node that isnt a leaf
  
    for (int i = startIndex; i >= 0; i--) {     // reverse for loop going from start index to first element of original array
        maxHeapify(arr, size, i);                  // call max Heapify
    }
}


void printMaxHeap(int arr[], int n)             // To print max heap as specified
{  
    for (int i = 0; i < n; ++i)
        cout << arr[i] << " ";
    cout << "\n";
}


int main()
{

    int arr[129];                   // found array size to be fixed in all test data, sorry for the laziness!
    int count = 0;
    int x;
    ifstream myFile("test1.csv");               // read and input into array from file
    if(!myFile.is_open())
        throw runtime_error("Couldn't open file");
    while (! myFile.eof()) 
        {     
            myFile >> arr[count];
            count++;
        }
    myFile.close();

    BST b, *root = NULL;                // initialize pointers
    root = b.insert(root, arr[0]);      // first element is root
    for(int i = 1; i < 130; i++)
    {
        b.insert(root, arr[i]);
    }
   
    
    //b.preOrder(root);                   
    //b.postOrder(root);
    //b.inOrder(root);
    //int z = b.findMax(root);

    int size = sizeof(arr) / sizeof(arr[0]);
  
    //createMaxHeap(arr, size);
    //printMaxHeap(arr, size);
    
    return 0;
}