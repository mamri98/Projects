#ifndef TREE_H
#define TREE_H
#include <iostream>

using namespace std;

class BST
{
    int data;
    BST *left, *right;      // left and right node
 
public:
    BST();          // constructors
 
    BST(int);
 
    BST* insert(BST*, int);

    BST* search(BST*, int);
 
    void inOrder(BST*);

    void postOrder(BST*);

    void preOrder(BST*);

    int findMax(BST*);
    
    int findMin(BST*);
};
 
#endif


