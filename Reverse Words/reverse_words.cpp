#include <iostream>
#include <limits>
#include <list>
#include <string>
#include <fstream>

using namespace std;

ifstream fin;

void readInput(list<string> &words);
void print_original_list(list<string> &words);
void print_reversed_list(list<string> &words);

int main() {
	
	list<string> words;
	
	// Read into list
	readInput(words);
	
	// Print original list
	print_original_list(words);
	
	// Print list in reverse order
	print_reversed_list(words);
	
	return 0;
}

// Function to read in input to list
void readInput(list<string> &words) {
	
	list<string>::iterator liter;
	string sen;
	
	// Open input file
	fin.open("input.txt");
	
	// Read input from file
	liter = words.begin();
	
	while(getline(fin, sen)) {
		words.push_back(sen);
	}	
	
	fin.close();
	
	return;
}

void print_original_list(list<string> &words) {
	
	list<string>::iterator liter;
	
	cout << "\nList in order: " << endl << endl;
	liter = words.begin();
	while(liter != words.end()) {
		cout << *liter << endl;
		liter++;
	}
	
	return;
}

// Gets a list of sentences and prints
// each sentence in reverse order
void print_reversed_list(list<string> &words) {
	list<string>::iterator liter;
	
	string original;
	string reversed;
	string::size_type pos;
	
	cout << "\nList in reverse order:" << endl << endl;
	
	liter = words.begin();
	while(liter != words.end()) {
		// Set the string at liter as the original string
		original = *liter;
		
		// Set the reversed string to be empty
		reversed = "";
		
		for(int i = 0; i < original.size(); i = pos + 1) {
			// Search for " " starting at i and check if pos == original.size()
			pos = original.find(" ", i);
			if(pos == string::npos)
				pos = original.size();
			
			// Insert word at the start of the string
			reversed.insert(0, original.substr(i, pos-i));
			
			// Insert a blank if not the last word
			if(!(pos == original.size()))
				reversed.insert(0, " ");
		}
		cout << reversed << endl;
		liter++;
	}
	
	return;
}
