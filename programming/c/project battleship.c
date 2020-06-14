#include<stdio.h>
#include<time.h>
#include<ctype.h>
#include<unistd.h>


char board[8][8],board2[8][8],board3[8][8];
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
    int y,x,x1,y1,k,e,sh,AIno,dire;
    char class[6];
    //class[0]="Battleship";
    //class[1]="cruiser";
    //class[2]="destroyer";
    //class[3]="sub";


    for(sh=0;sh<4;sh++){
        printf("Please input coordinates and direction(1 or 2) of ship %d:\n",sh);
        scanf("%d,%d,%d",&x,&y,&dire);
        if(dire==2){
            for(e=0;e<4;e++){
                board[x][y]='X';
                y=y+1; //If dire ==2 horizontal
            }
            //classno=classno-1;
        }
        else{
            for(e=0;e<4;e++){
                 board[x][y]='X';
                 x=x+1; //If dire ==1 vertical
                 //classno=classno-1;
            }
        }
    }



    printf("\nIt is player 2 turn to input\n\n");
    for(AIno=0;AIno<4;AIno++){
        x1=rand()%4-0;
        y1=rand()%7;
        dire=rand()%2-1;
        if(dire==2){
            for(k=0;k<4;k++){
                board2[x1][y1]='X';
                y1=y1+1; //If dire ==2 horizontal
            }
          //classno=classno-1;
        }
        else{
            //If dire ==1 vertical
            for(k=0;k<4;k++){
                board2[x1][y1]='X';
              x1++;
            }     //classno=classno-1;
        }

   //y=0 to 4
   }
    printf("Player 2 input complete\n");
    //sleep(5);
    printf("\nLet the games begin\n\n");
    //sleep(5);
}

void inputs(){
    int x,y,x1,y1,enu=0;
    char number1,number2;
    srand(time(NULL));

    printf("\nIt is players turn to fire,please in put a coord:");
    scanf("%d,%d",&y,&x);
    if(isdigit(y)==1 || isdigit(x)==1){
        printf("Invalid input please input again");
        scanf("%d,%d",&y,&x);
    }
    else{
        number1=board3[y][x];
        if(number1=='X'){
            board2[y][x]='O';
            printf("Hit\n");
            bot1=bot1+100;
         }
         else{
             board2[y][x]='N';
             printf("Miss\n");
         }
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
    while(bot1<1200||bot2<1200){
        inputs();
        initialize();
    }
    if(bot1=1200){
        printf("Player1 wins");
    }
    else{
        printf("AI wins");
    }
    return 0;
}
