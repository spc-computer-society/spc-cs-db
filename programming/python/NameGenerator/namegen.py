import random
import datetime
import time

"""
Name generator
By --- HYWong
"""


class Namegen:
    current_amount = 0
    results = []

    def __init__(self, amount: int):
        self.amount = amount

    def gen_eng(self):
        while self.current_amount < self.amount:
            name_list = open("src/eng/name.txt").read().splitlines()
            name = random.choice(name_list)
            surname_list = open("src/eng/surname.txt").read().splitlines()
            surname = random.choice(surname_list)
            fullname = name + " " + surname
            print(fullname)
            self.results.append(fullname)
            self.current_amount += 1
        self.save()

    def gen_chi(self):
        while self.current_amount < self.amount:
            name_list_1 = open("src/chi/name1.txt").read().splitlines()
            name1 = random.choice(name_list_1)
            name_list_2 = open("src/chi/name2.txt").read().splitlines()
            name2 = random.choice(name_list_2)
            surname_list = open("src/chi/surname.txt").read().splitlines()
            surname = random.choice(surname_list)

            y = random.randint(1, 100)
            if y < 95:
                fullname = surname + name1 + name2
            else:
                combined_name_list = name_list_1 + name_list_2
                single_name = random.choice(combined_name_list)
                fullname = surname + single_name
            print(fullname)
            self.results.append(fullname)
            self.current_amount += 1
        self.save()

    def gen_comb(self):
        while self.current_amount < self.amount:
            name_list_1 = open("src/combined/name1.txt").read().splitlines()
            unsplitname1 = random.choice(name_list_1)
            splitname1 = unsplitname1.split(":")
            name1_chi = splitname1[0]
            name1_eng = splitname1[1]
            name_list_2 = open("src/combined/name2.txt").read().splitlines()
            unsplitname2 = random.choice(name_list_2)
            splitname2 = unsplitname2.split(":")
            name2_chi = splitname2[0]
            name2_eng = splitname2[1]
            surname_list = open("src/combined/surname.txt").read().splitlines()
            unsplitsurname = random.choice(surname_list)
            splitsurname = unsplitsurname.split(":")
            surname_chi = splitsurname[0]
            surname_eng = splitsurname[1]
            combined_name_list = name_list_1 + name_list_2
            unsplitname = random.choice(combined_name_list)
            splitname = unsplitname.split(":")
            single_name_chi = splitname[0]
            single_name_eng = splitname[1]

            y = random.randint(1, 100)
            if y < 95:
                fullname = surname_chi + name1_chi + name2_chi + " " + surname_eng + " " + name1_eng + " " + name2_eng
            else:
                fullname = surname_chi + single_name_chi + " " + surname_eng + " " + single_name_eng
            print(fullname)
            self.results.append(fullname)
            self.current_amount += 1
        self.save()


    def save(self):
        filename = datetime.datetime.now().strftime("%d-%m-%Y|%H:%M:%S")
        with open(f"results/{filename}.txt", "w") as result:
            result.write("\n".join(self.results))
        print("Results has been successfully saved!")


try:
    a = int(input("Please input the number of names generated."))
    b = int(input("Please input the generate mode. 1 = English, 2 = Chinese, 3 = Chinese with Translated English"))
    start = time.time()
    if a > 0 and b in (1, 2, 3):
        if b == 1:
            Namegen(a).gen_eng()
        elif b == 2:
            Namegen(a).gen_chi()
        else:
            Namegen(a).gen_comb()

        end = time.time()
        print(f"{end - start} seconds taken to generate.\n"
              f"Thank you for using my tool!")

    else:
        print("Sorry, please input valid information!")

except ValueError:
    print("Sorry, please input valid information!")
