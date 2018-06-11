# Maze Solver
## 8-Jun-2018

## Introduction
This time the problem is.... Maze Solving! We will provide you with an input file, consisting of several mazes, composed of `0`s and `1`s, and your task will be to decide if a path exists from the start point to the end point, for each maze in the file. As you can imagine this can be very simple, with a small maze and a single unbroken path from end to beginning, but it can also be highly complex, with large maps and complex branching.

You will have access to both simple mazes to ensure your solutions are correct, and complex ones to perfect your exploration code! In terms of milestones everyone should aim to be able to solve the simple input file, which will consist of two small mazes, one of which has a single path to being correct, and the other without a valid path. Once you have achieved this, try your solution on the larger file, as they will be more challenging to solve.

As the 'challenge' aspect of this event, we're going to attempt to optimise for speed. If you want your solution evaluated, please submit it up to the `solutions` sub-folder, noting the execution command (and compilation if applicable) in a comment at the top of your solution file and we'll execute it on a Linux environment using `time` to measure the user execution time.

To make it more interesting, we're also going to stipulate that your program should be single-threaded! No parallelism allowed! As the way this exercise is constructed could easily be broken down into lots of chunks for easy speed gains, we instead want you to think about your algorithmic approach to tackling this problem.

## Maze Examples
Here is an example of a simple maze that could appear in the test file:

```javascript
1,0,1,1,1,1,1,1,1,1,1
1,0,1,0,0,0,0,0,0,0,1
1,0,1,1,1,0,1,0,1,1,1
1,0,0,0,0,0,1,0,1,0,1
1,1,1,1,1,0,1,1,1,0,1
1,0,1,0,1,0,0,0,1,0,1
1,0,1,0,1,0,1,1,1,0,1
1,0,0,0,0,0,0,0,0,0,1
1,1,1,0,1,0,1,1,1,0,1
1,0,0,0,1,0,0,0,1,0,1
1,1,1,1,1,1,1,1,1,0,1
```

The maze will consist of 'walls' made from `1`s and 'paths' made from `0`s. The entrance to each maze will always be the second character in the first row, the 'start' of the maze. If the maze has a path, it will always end at the second to last character in the bottom row. A path from the start to the end of the maze will consist of an uninterrupted path made by adjacent `0`s directly from the start point to the end point. There may be branches that lead nowhere, or multiple paths that lead to the end, but all you have to decide is if there is any valid path. Moving diagonally is not allowed.

The small input file will have 2 mazes (`mazes_small.txt`), and the large will have 100 (`mazes.txt`) both of which are available in this repository along with the expected responses in `answers_small.txt` and `answers.txt` respectively for self-evaluation.

Here is the maze above, solved:

```javascript
1,#,1,1,1,1,1,1,1,1,1
1,#,1,0,0,0,0,0,0,0,1
1,#,1,1,1,0,1,0,1,1,1
1,#,#,#,#,#,1,0,1,0,1
1,1,1,1,1,#,1,1,1,0,1
1,0,1,0,1,#,0,0,1,0,1
1,0,1,0,1,#,1,1,1,0,1
1,0,0,0,0,#,#,#,#,#,1
1,1,1,0,1,0,1,1,1,#,1
1,0,0,0,1,0,0,0,1,#,1
1,1,1,1,1,1,1,1,1,#,1
```

For a visualisation of each maze in `mazes.txt`, see the `images` sub-folder.

## Input & Output
Your solution can be in any language you are comfortable working in (as always), but your program must take in a single argument, a path to a file containing the descriptions of the mazes to be solved. This file will be in the following format:

* The first line will be a single integer, `n`, which is the number of mazes in the file.
The `n` mazes will be supplied in the following format:
* A line containing a single integer, `m`, giving the number of lines that make up the first maze. In the example, this number would be `13`.
  * The following `m` lines will be the characters of the maze
  * This pattern continues for the total amount of mazes.
* As always, there is no need to validate the input, you can safely assume that any input file we provide will always be in this format.

For output, you should create a single `.txt` file called `output.txt`, with each line showing whether or not a maze has a valid path. Use `1` for mazes that do have a path, and `0` for mazes that don't have a path. In order to check correctness of each submitted solution, we will diff this output file against our own correct one.

## The Rules
* The starting point is always the second character in the top row of the maze.
* The end point is always the second-last character on the last line of the maze.
* The maze will always be square.
* Moving diagonally is not allowed.
* Single-threaded program.
* A path is only valid if it consists of a sequence of adjacent `0`s leading directly from the start point to the end point.

## Results
| Name            | Execution time (ms) | Language      |
| --------------- | ------------------- | ------------- |
| Jonathan Gilchrist | 109 ms | Rust |
