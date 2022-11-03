/* File: autocomplete.h
 * Course: CS216-00x
 * Project: Project 2
 * Purpose: the declaration for the class named Autocomplete.
 *
 */

#ifndef AUTOCOMPLETE_H
#define	AUTOCOMPLETE_H

#include <string>
#include "term.h"
#include "SortingList.h"

using namespace std;

class Autocomplete
{
    public:
        // default constructor
        Autocomplete();

        // inserts the newterm to the sequence
        void insert(Term newterm);

        // sort all the terms by query in lexicographical order
        // note that this function needs to be called before applying binary search
        void sort();

        // return the index number of the term whose query 
        // prefix-matches the given prefix, using binary search algorithm
        int binary_search(string prefix);


        void search(string key, int& first, int& last);

        // return all terms whose queries match the given prefix, 
        // in descending order by weight
        SortingList<Term> allMatches(string prefix);

        // display all the terms in the sequence
        void print();

    private:
        SortingList<Term> terms;

        int binary_searchHelper(SortingList<Term> terms, string key, int left_index, int right_index);
};

#endif	/* AUTOCOMPLETE_H */

