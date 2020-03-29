#include <stdio.h>
int main(){
    char in[8];
    scanf("%s",in);
    if(in[0] == '5' || in[0] == '6' || in[0] == '9'){
        printf("Mobile\n");
    }else if(in[0] == '2' || in[0] == '3'){
        printf("Fixed\n");
    }else{
        printf("Impossible?\n");
    }
    return 0;
}
