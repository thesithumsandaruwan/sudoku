# Sudoku Game

a 9-by-9 Sudoku puzzle where number 1, 2, 3, 4, 5, 6, 7, 8, and 9 appear only once in a row and column.
In addition, the 9-by-9 is divided into nine 3-by-3 sub regions, where the number 1 to 9 appear only once.
Some of the numbers in the puzzle is filled and the player should fill the rest while satisfying the above constraints.

The player can select the level of the game and the number of filled locations decided:
- easy level
- medium level
- hard level

will fill 35-45, 30-40, and 25-35 randomly selected locations in the puzzle, respectively.

## Game description
- New - New game will be started
- Reset - All the user filled values will be deleted
- Show Solution - Show the solution by displaying the fillable text boxes withthecorrect numbers. The Reset button get disabled when this button is clicked.  Text boxes
- Non editable text boxes - Will display some of the numbers of thepuzzlewhich are already selected to display to the user
- Editable text boxes - These text boxes allow the user to enter numbers. Itshould not allow any symbol other than the numerical characters 1, 2, 3, …, and9. Moreover, if the player enters wrong number, the text-box which holds the wrong number gets highlighted in Red Color and it will return to regular color when the wrongly entered value is deleted and keeps the text-box empty.
