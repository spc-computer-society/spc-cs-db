#include <stdio.h>

int main()
{
    int arr[] = { 1, 3, 5 };

    for (int i = 0; i < 3; i++) { // for loop
        printf("%d", arr[i]);
    }
	printf("\n");

    int a = 0;
    while (arr[a] != 3) { // while loop (pre-test)
        printf("%d", arr[a]);
		a++;
    }
	printf("\n");

    a = 0;
    do { // do while loop (post-test)
        printf("%d", arr[a]);
		a++;
    } while (arr[a] != 3);
	printf("\n");
}