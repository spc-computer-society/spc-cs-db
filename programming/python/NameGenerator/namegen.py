import random
import datetime
import time

"""
Name generator
Coder: HYWong

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
"""


class Namegen:
    current_amount = 0
    results = []

    def __init__(self, amount: int):
        self.amount = amount

    def gen_eng(self):  # This generates an English name. Invoked when mode is set to 1.
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

    def gen_chi(self):  # This generates a Chinese name. Invoked when mode is set to 2.
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

    def gen_comb(self):  # This generates a combined name (Chinese name with translated English). Invoked when mode is set to 3.
        while self.current_amount < self.amount:
            name_list_1 = open("src/combined/name1.txt").read().splitlines()
            unsplitname1 = random.choice(name_list_1)
            name1_chi, name1_eng = unsplitname1.split(":")
            name_list_2 = open("src/combined/name2.txt").read().splitlines()
            unsplitname2 = random.choice(name_list_2)
            name2_chi, name2_eng = unsplitname2.split(":")
            surname_list = open("src/combined/surname.txt").read().splitlines()
            unsplitsurname = random.choice(surname_list)
            surname_chi, surname_eng = unsplitsurname.split(":")
            combined_name_list = name_list_1 + name_list_2
            unsplitname = random.choice(combined_name_list)
            single_name_chi, single_name_eng = unsplitname.split(":")
            
            y = random.randint(1, 100)
            if y < 95:
                fullname = surname_chi + name1_chi + name2_chi + " " + surname_eng + " " + name1_eng + " " + name2_eng
            else:
                fullname = surname_chi + single_name_chi + " " + surname_eng + " " + single_name_eng
            print(fullname)
            self.results.append(fullname)
            self.current_amount += 1
        self.save()


    def save(self):  # This method saves the results into a file located in dir "results', with date and time included.
        filename = datetime.datetime.now().strftime("%d-%m-%Y|%H:%M:%S")
        with open(f"results/{filename}.txt", "w") as result:
            result.write("\n".join(self.results))
        print("Results has been successfully saved!")


try:
    a = int(input("Please input the number of names generated."))
    b = int(input("Please input the generate mode. 1 = English, 2 = Chinese, 3 = Chinese with Translated English"))
    start = time.time()  # This records the starting time.
    if a > 0 and b in (1, 2, 3):
        if b == 1:
            Namegen(a).gen_eng()
        elif b == 2:
            Namegen(a).gen_chi()
        else:
            Namegen(a).gen_comb()

        end = time.time()  # This records the ending time.
        print(f"{end - start} seconds taken to generate.\n"  # This calculates the required time to generate the amount of names.
              f"Thank you for using my tool!")

    else:
        print("Sorry, please input valid information!")

except ValueError:
    print("Sorry, please input valid information!")
