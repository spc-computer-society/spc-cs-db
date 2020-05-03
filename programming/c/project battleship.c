#include<stdio.h>
#include<time.h>

char board[8][8],board2[8][8];
int bot1=0,bot2=0,a;


void initialize(){
    int x,y,o;
    printf("player 1:   score:%d\n",bot1);
    printf("_ |");
    for(o=0;o<8;o++){
        printf("_%d|",o);
    }
    printf("\n");
    for(x=0;x<8;x++){
        printf("_%d|",x);
        for(y=0;y<8;y++){
         printf("_%c|",board[x][y]);
        }
    printf("\n");
    }
    printf("\nplayer 2:   score:%d\n",bot2);
    printf("_ |");
    for(o=0;o<8;o++){
        printf("_%d|",o);
    }
    printf("\n");
    for(x=0;x<8;x++){
        printf("_%d|",x);
        for(y=0;y<8;y++){
            printf("_%c|",board2[x][y]);
        }
    printf("\n");
    }
}

void ship(){
    int y,x,o=5,sh,i,e,number5=0,ko=4,x1,y1,k;
    for(sh=0;sh<3;sh++){
        printf("Please input coordinates of ship %d:\n",sh);
        scanf("%d,%d",&x,&y);

        for(e=0;e<3;e++){
            x=x+e;
            board[x][y]='X';
        }
    }

//for(sh=1;sh<5;sh++){
//printf("Please input coordinates of ship %d:\n",sh);
//scanf("%d,%d",&y,&x);

//for(e=1;e<=o;e++){

//board[y][x]='X';
//}
//o--;



    printf("\nIt is player 2 turn to input");
    for(number5=0;number5<3;number5++){
      x1=rand()%5-3;
      y1=rand()%7;
        for(k=0;k<3;k++){
            x1=x1+k;
            board2[x1][y1]='.';
       }
   }
}

void inputs(){
    int x,y,x1,y1,enu=0;
    char number1,number2;
    srand(time(NULL));
    printf("\nIt is players turn to fire,please in put a coord:");
    scanf("%d,%d",&y,&x);
    number1=board2[y][x];
    if(number1=='.'){
        board2[y][x]='O';
        printf("Hit\n");
        bot1=bot1+100;
     }
     else{
        board2[y][x]='N';
        printf("Miss\n");
      }

    printf("Player 2 turn");

    x1=rand()%10;
    y1=rand()%10;
    number2=board[y1][x1];
    if(number2=='X'){
         board[y1][x1]='O';
         printf("\nHit\n");
         bot2=bot2+100;
      }
      else{
         board[y1][x1]='N';
         printf("\nMiss\n");
      }
}







int main(){
    int player,input,k,bot=0;

    initialize();
    ship();
    initialize();

    while(bot1<12|| bot2<12){
        inputs();
        initialize();
    }
/*  if(bot1=){
        printf("Player1 wins");
    }
    else{
        printf("AI wins");
    }*/
    return 0;
}




