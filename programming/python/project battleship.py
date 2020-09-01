import arcade
import numpy as np
import random

# Set how many rows and columns we will have
ROW_COUNT = 7
COLUMN_COUNT = 7

WIDTH = 25
HEIGHT = 25
MARGIN = 10

SCREEN_WIDTH = 600  # (WIDTH + MARGIN) * COLUMN_COUNT + MARGIN + 400
SCREEN_HEIGHT = 600  # (HEIGHT + MARGIN) * ROW_COUNT + MARGIN + 400
SCREEN_TITLE = "Battleship"

colour = arcade.color.WHITE
grid1 = np.zeros((8, 8), dtype=int)
grid2 = np.zeros((8, 8), dtype=int)
range1 = 8
totalships = 16
num_ofships = 4
ship_size = 4


class end(arcade.View):

    def on_show(self):
        arcade.set_background_color(arcade.color.AMAZON)

    def on_draw(self):
        arcade.start_render()
        arcade.draw_text("")
        arcade.draw_text("You lost", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, arcade.color.WHITE, font_size=60,
                         anchor_x="center")


class Insturctions(arcade.View):

    def on_show(self):
        arcade.set_background_color(arcade.color.BLUE)

    def on_draw(self):
        arcade.start_render()
        arcade.draw_text("Battleship", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, arcade.color.WHITE, font_size=60,
                         anchor_x="center")
        arcade.draw_text("1:start game OR 2:quit game", SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3, arcade.color.WHITE,
                         font_size=20,
                         anchor_x="center")

    def on_key_press(self, key, _modifiers):
        if key == arcade.key.NUM_1:
            game_view = Gameview()
            self.window.show_view(game_view)
        elif key == arcade.key.NUM_2:
            arcade.close_window()
        elif key == arcade.key.NUM_3:
            turn_screen = Turnview()
            self.window.show_view(turn_screen)


class Turnview(arcade.View):

    def on_show(self):
        arcade.set_background_color(arcade.color.AMAZON)

    def on_draw(self):
        arcade.start_render()
        arcade.draw_text("Best turns")
        arcade.draw_text("Best turns", SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3, arcade.color.WHITE,
                         font_size=20,
                         anchor_x="center")


class Gameview(arcade.View):

    def __init__(self):

        super().__init__()
        self.cross_list = None
        self.circle_list = None
        self.wincon = 0
        self.wincon2 = 0
        self.turns = 0
        self.column1 = 0
        self.row1 = 0

        arcade.set_background_color(arcade.color.AMAZON)

    # take care of sprite loading
    def setup(self):
        self.cross_list = arcade.SpriteList(use_spatial_hash=True)
        self.circle_list = arcade.SpriteList(use_spatial_hash=True)

        self.cross = arcade.Sprite("pictures/circle.png", 0.5)
        self.cross.center_x = SCREEN_HEIGHT
        self.cross.center_y = 2
        self.cross_list.append(self.cross)

        self.circle = arcade.Sprite("pictures/cross.png", 0.5)
        self.circle.center_x = SCREEN_HEIGHT
        self.circle.center_y = 1
        self.circle_list.append(self.circle)

    def on_show(self):
        for i in range(4):
            botx1 = random.randint(0, 4)
            boty1 = random.randint(0, 7)
            for i2 in range(4):
                if i2 == 0:
                    botx1 = botx1 + 0
                else:
                    botx1 = botx1 + 1
                grid2[botx1][boty1] = 2

    # def save(self):

    def on_draw(self):

        arcade.start_render()
        boats = 0

        #   arcade.draw_text("The opponent  has placed its ships ", 10, 4000, arcade.csscolor.WHITE, 18)
        # add starting screen
        for column in range(range1):
            for row in range(range1):
                x = (MARGIN + WIDTH) * column + MARGIN + WIDTH // 2
                y = (MARGIN + HEIGHT) * row + MARGIN + HEIGHT // 2

                if grid1[row][column] == 1 and boats < totalships:
                    colour = arcade.color.BLUE
                elif grid1[row][column] == 4:  # hit
                    colour = arcade.color.RED
                elif grid1[row][column] == 6:  # miss
                    colour = arcade.color.YELLOW
                else:
                    colour = arcade.color.WHITE

                arcade.draw_rectangle_filled(x, y, WIDTH, HEIGHT, colour)

        for column in range(range1):
            for row in range(range1):
                x = (MARGIN + WIDTH) * column + MARGIN + WIDTH // 2 + 300
                y = (MARGIN + HEIGHT) * row + MARGIN + HEIGHT // 2
                if grid2[row][column] == 2:
                    colour2 = arcade.color.BLACK
                elif grid2[row][column] == 3:  # hit
                    colour2 = arcade.color.GREEN
                else:
                    colour2 = arcade.color.WHITE

                arcade.draw_rectangle_filled(x, y, WIDTH, HEIGHT, colour2)
        scoretext = f"Turn: {self.turns}"
        arcade.draw_text(scoretext, 500, 500, arcade.color.WHITE, 18)

    # self.circle_list.draw()
    # self.cross_list.draw()

    def print_turns(self):
        with open("text", "a") as opened_file:
            opened_file.write(f"{self.turns}")

    def translate_input(self, x, y):

        column4 = int(x // (WIDTH + MARGIN))
        self.row1 = int(y // (HEIGHT + MARGIN))
        print(f"{self.row1},{column4}")
        self.column1 = column4 - 9
        print(f"{self.row1},{self.column1}")
        return self.row1, self.column1

    def check_placement(self):
        occurence = np.count_nonzero(grid1 == 1)
        return occurence

    def win_check(self):
        AI = np.count_nonzero(grid1 == 4)
        player = np.count_nonzero(grid2 == 3)
        if player == 16:
            return 0
        elif AI == 16:
            return 1

    def user_input(self, row, column):
        if grid2[row][column] == 2:
            grid2[row][column] = 3
        else:
            grid2[row][column] = 5
            # miss

    def AI_input(self):
        botx = random.randint(0, 7)
        boty = random.randint(0, 7)
        if grid1[botx][boty] == 1:
            x = int(botx * (HEIGHT + MARGIN))
            y = int(boty * (HEIGHT + MARGIN))
            self.wincon2 += 1
            grid1[botx][boty] = 4
        else:
            grid1[botx][boty] = 6
            # miss

    def on_mouse_press(self, x, y, button, modifiers):
        # position boats
        order = 0

        no = 0
        column = int(x // (WIDTH + MARGIN))
        row = int(y // (HEIGHT + MARGIN))

        if self.check_placement() <= 14:
            for no1 in range(4):
                y1 = column + no1
                grid1[row][y1] = 1
            print(self.check_placement())
        elif self.check_placement() == 16:
            self.translate_input(x, y)
            self.user_input(self.row1, self.column1)
            print(f"hit on{x},{y}")
            #  self.setup(x, y, no)
            self.AI_input()
            self.turns += 1

        elif self.win_check() == 0 or self.win_check() == 1:
            self.print_turns()
            arcade.close_window()
            end_screen = end()
            self.window.show_view(end_screen)
            # arcade.close_window()

    #  if grid2[row][column] == 2:
    # grid2[row][column] == 3
    # wincon += 1 , add win condition


def main():
    window = arcade.Window(SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_TITLE)
    start_view = Insturctions()
    window.show_view(start_view)
    arcade.run()


if __name__ == "__main__":
    main()
