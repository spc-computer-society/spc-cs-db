#include <stdio.h>
#include <string.h>

// struct for organizing and grouping a list of variables
struct Student {
	char name[256];
	char class;
	int form;
	int class_no;
};

int main() {
	struct Student student1; // creating an instance of struct Student
	
	// accessing member variables with . operator
	strcpy(student1.name, "Chan Tai Man");
	student1.class = 'A';
	student1.form = 6;
	student1.class_no = 32;

	printf("%s: F.%d%c (%d)\n", student1.name, student1.form, student1.class, student1.class_no);

	return 0;
}