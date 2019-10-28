#include <stdio.h>
#include <string.h>

int main(void) {
    int ParaPointer = 0;
    int WordPointer = 0;
    char Para[] = "George Washington University. Computer science. Computer architecture. This is a great class. It is interesting. A wonderful class.";
    char Word[] = "is";
    int SentenseNo = 1;
    int WordNo = 1;
    int CompareWordLength = strlen(Word);
    char CharFromPara;
    char CharFromWord;

    while (1) {
        label1:
        if (ParaPointer==strlen(Para)){
            printf("Not Found!\n");
            return 0;
        }
        CharFromPara = Para[ParaPointer];
        CharFromWord = Word[WordPointer];
        if (CharFromPara == CharFromWord) {
            int ComparePointer = 0;
            while (1) {
                label2:
                if (ComparePointer == CompareWordLength) {
                    printf("Sentence ID:%d\n", SentenseNo);
                    printf("Word ID:%d\n", WordNo);
                    return 0;
                }
                CharFromPara = Para[ParaPointer + ComparePointer];
                CharFromWord = Word[WordPointer + ComparePointer];
                if (CharFromPara == CharFromWord){
                    ComparePointer++;
                    goto label2;
                }
                break;
            }
        }
        while (1) {
            ParaPointer++;
            CharFromPara = Para[ParaPointer];
            if (CharFromPara == ' ') {
                WordNo++;
                ParaPointer++;
                break;
            }

            if (CharFromPara == '.') {
                SentenseNo++;
                WordNo = 1;
                ParaPointer++;
                CharFromPara = Para[ParaPointer];
                if (CharFromPara == ' ') {
                    ParaPointer++;
                }
                break;
            }
        }
        goto label1;
    }
}