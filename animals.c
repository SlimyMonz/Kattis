//
//  main.c
//  Alphabet_Animals
//
//  Created by Harry James Hocker on 8/30/21.
//

#include <stdio.h>
#include <stdlib.h> // malloc
#include <string.h> // strlen
#include <assert.h> // needed for scanf

#define MAXLIST 100000 // max of 100,000 words

char* addSingleMemory(char* word);
int compareLetters(char* lastWord, char* newWord);
char* gameLogic(long long unsigned int totalWords, char* lastWord, char** wordArray);
void freeAll(char** wordList, long long unsigned int totalWords);

int main() {
    
    // totalWords is the amount of words there are, input is a # the user must define
    long long unsigned int totalWords = 0;
    long long unsigned int prevWords = 0;
    // array to hold onto the 'latest' word (makes sense in context of the game)
    char lastWord[21];
    
    long long unsigned int input = 0;

    assert(scanf(" %s", lastWord) != 0);

    assert(scanf(" %lld", &input) < MAXLIST);

    // 2D array to hold a list of words based on input
    char** wordsArray = calloc(input, sizeof(char*));
    
    // for # from input, scanf a new word and insert into end of current array
    for (long long unsigned int i = 0; i < input; i++) {
        // array to hold each word input
        char word[21];
        
        assert(scanf(" %s", word) != 0);
        
        wordsArray[prevWords + i] = addSingleMemory(word);
        totalWords++;
    }
    
    printf("%s\n", gameLogic(input, lastWord, wordsArray));
    
    freeAll(wordsArray, input);
    return 0;
}

// functions:

// checks to find the first playable word from given list (if there is none, return ?)
// if there is a playable word, check if it can "eliminate" any players (IE: the last letter of this word does not match with any letter of the others in the given list)
char* gameLogic(long long unsigned int totalWords, char* lastWord, char** wordsArray) {
    
    long long unsigned int index = 0;
    long long unsigned int found = 0;
    
    for (long long unsigned int i = 0; i < totalWords; i++) {
        // if the letters of the game logic match, check to see if it's a "killer move"
        
        if (compareLetters(lastWord, wordsArray[i]) == 1) {
            if (found == 0) index = i;
            int flag = 0;
            for (long long unsigned int j = 0; j < totalWords; j++) {
                if (flag == 0) {
                    if (compareLetters(wordsArray[i], wordsArray[j]) == 1 && (i!=j)) {
                        flag = 1; // this means this word will NOT eliminate anyone
                    }
                }
                
            }
            // if the flag is 0, the word can eliminate others, otherwise return the first word that can be played
            if (flag == 0) { return (strcat(wordsArray[i], "!")); }
            found++;
        }
    }
    if (found > 0) return wordsArray[index];
    
    // default
    return "?";
}

// compares the index of each array to see if they are the same character bool
int compareLetters(char* lastWord, char* newWord) {
    long unsigned int strLen = strlen(lastWord);
    if (lastWord[strLen-1] == newWord[0]) return 1;
    return 0;
}

// create a dynamic chunk of memory for a word
char* addSingleMemory(char* word) {
    // allocate dynamic memory for word+1 after finding length of string
    char* newWord = calloc(strlen(word)+1, sizeof(char));
    // put word into new dynamic array
    strcpy(newWord, word);
    // return the new dynamic array
    return newWord;
}

// free memory
void freeAll(char** wordList, long long unsigned int totalWords) {
    for (int i = 0; i < totalWords; i++) {
        free(wordList[i]);
    }
    free(wordList);
}
