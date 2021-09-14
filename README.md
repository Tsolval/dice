# Welcome to DICE
Dice is a flexible, text-based dice rolling system inspired by the system used by (Roll20)[http://www.roll20.net].  It can be deployed stand-alone and accessed by API or included in an application for direct use.

## Dice Rolling Commands
roll [{NUM}]d{DIE}[![!|?][[<|>]EXT]][d|k[l|h][DROP]]

NUM - The number of Dice to roll. Defaults to 1.
d   - Used to separate Number of Dice and Die Size.
DIE - The number of sides on the die to be rolled. 
!   - Indicate when to explode die.  Defaults to compound explosions.  Use !! to append explosions and add ? to use penetrating explosions.  By default, die explode when they roll their maximum value, but you can change this behavior by specifying the explosion target and optional range indicator.  See Exploding Die below for more details.

## Modify Results
You can perform simple math on the result of a die roll, such as adding or subtracting modifiers.

### Modify Results Example

1d6 + 3

## Exploding Die
A die explodes when it hits its explosion target, which is typically its maximum value.  When a die explodes, it is rolled again and the new roll is added to the result.

When called with a single !, Dice will compound the results, returning the value as if it was a single die roll.  If you specify !!, Dice will return the value of the additional die as if die were added to the pool.  Some die systems use a penetrating explosion rule where you subtract 1 from subsequent rolls.

By default, Dice will explode on the maximum value of a die, but you can specify a new explosion target.  1d6!1 will only explode if a 1 is rolled.  1d6!<3 will explode if a 1 or 2 is rolled.  1d6!>4 will explode if a 5 or 6 is rolled.

### Exploding Die Examples
Assume you are rolling a 1d6, exploding on a 6, and the Dice explode twice, rolling 6, 6, and 1.  Dice will return the following results given the specified command.
1d6!  - [13]    (6 + 6 + 1)
1d6!! - [6,6,1]
1d6!? - [11]    (6 + 6-1 + 1-1)
1d6!!? - [6,5]

## Dropping or Keeping Die
Sometimes when you roll die, you want to drop the lowest or keep the highest die.  In the Dungeons and Dragons system, for example, you generate stats by rolling 4d6 and dropping the lowest die and you roll at Advantage or Disadvantage with 2d20, keeping or dropping the highest.

d | dl | dh - drop die.  By default, drops the lowest die (dl).  Use dh to drop the highest die instead.
k | kl | kh - keep die.  By default, keep the highest die (kh).  Use kl to keep the lowest die instead.

### Drop or Keep Examples
You can often specify the same roll with either drop or keep.
DROP    KEEP     EFFECT
4d6d1   4d6k3    roll 4d6, drop the lowest 1, keep the highest 3 - D&D Stat roll
2d20d1  2d20k1   roll 2d20, drop the lowest 1, keep the highest 1 - D&D Advantage roll
2d20dh1 2d20kl1  roll 2d20, drop the highest 1, keep the lowest 1 - D&D Disadvantage roll

## Target Value
If specified, Die results will be evaluated to determine whether or not they were successful.  The user can specify the target number and an operator.  If the operator is not specified, it defaults to equal or greater (>=).  Other allowed operators are less-than (<), greater-than (>), and equals (=).  These can be combined. (<=, >=).  

### Target Examples
3d6>4 - roll 3d6 and report any result of 5 or 6 as a success.
3d6>=4 - roll 3d6 and report any result of 4, 5, or 6 as a success.

## Critical Results
If specified, Die results will be evaluated to determine whether they are critical successes or critical failures.  By default, a die is a critical success at its maximum value and a critical failure at its minimum value.  When specifying a critical success target, any number greater than the specified value is also a critical.  When specifying a critical failure target, any number less than the specified value is also a critical.  The user can specify the target number and an operator.  If the operator is not specified, it defaults to equal or greater (>=) for successes and equal or less (<=) for failures.

### Critical Examples
2d20c - roll 2d20, reporting critical success on a roll of 20.
2d20cf - roll 2d20, reporting critical success on a roll of 20 and critical failure on roll of 1.
8d6!s8c10f1 - roll 8d6, using compounding explosions.  Collect successes when the value is 8 or greater, critical successes when the value is 10 or greater, and critical failure if any die result is 1.

## Rerolling Dice
Sometimes you want to reroll die, especially when they are less than some value.  If specified, dice can be re-rolled if they meet the criteria.

### Rerolling Die Examples 
3d6r1 - Roll 3d6, rerolling any result of 1.
3d6ro1 - Roll 3d6, rerolling any ones, but only one time.

## FATE Die 
The FATE System uses a special d6 where 2 sides have the value 1, 2 sides have a value of 0, and 2 sides have a value of -1.  Rolling a dF rolls one of these fate die.

### FATE Die Example
4dF - Roll 4 FATE Die

## Grouping Die Rolls 
There are several ways to group die rolls.  If you just want to group them for math purposes, i.e. to add results together before performing another operation, just use parenthesis.  

If you want to collect die results to operate on, use square brackets.  You can operate on totals with a comma, or combine individual results with a + operator.

### Grouping Examples
ROLL                 RESULT
(2d8+3d4)/2        - ([3,4]+[1,3,4])/2 - (7+8)/2 - 15/2 - 7.5
[2d8,3d8]k1        - [[8,4], [5,1,7]]k1 - [12,13]k1 - 13
[2d8+3d8]k1        - [[8,4], [5,1,7]]k1 - [8,4,5,1,7]k1 - 8


## Math Functions
You can surround portions of the rolls with parenthesis and can specify the following math functions:

floor(...) - Find the result between the parenthesis and round it down. 
ceil(...) - Find the result between the parenthesis and round it up. 
round(...) - Find the result between the parenthesis and round it to the nearest whole number. 
abs(...) - Find the result between the parenthesis and make it a positive number. 

### Math Functions Examples 
floor((1d8+1d4+5)/2) - return the floor of half of 1d8, 1d4, and 5.
floor((2d8+3d4)/2)   - ([3,4]+[1,3,4])/2 - (7+8)/2 - 15/2 - 7.5
