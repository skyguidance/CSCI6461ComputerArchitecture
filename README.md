
# CSCI 6461 Computer Architecture 
This is our CSCI6461 Simple Computer Simulator for George Washington University, Fall 2019, Group 1.<br>

#### About the Mannual
Project Mannual is avilable at:<br>
http://www.mslcourses.com/CSCI6461Section10Fall2019/AdaptedKaislerFiles/ClassProjectDescription.doc
## Download and Execute
#### Usage Of this simulator
- Clone the Project first, and checkout master branch.
> git clone https://github.com/skyguidance/CSCI6461ComputerArchitecture.git <br>
> git checkout master
- Use [IDEA](https://www.jetbrains.com/idea/) to Open the project.
- Run src/Inferface/GUI class to start the simulator.
#### Common TroubleShooting
- Create a folder called "out" to store the temporary class files for IDEA.
- Import(IDEA File->Project Structure->Project Settings->Libraries->Add JAR) lib/junit.jar to avoid the junit package missing error.

## Usage
#### Basic Detail

Our PC will initial with value **6** (means the first instr is fetched from MEM[6]). Some registers is protected by default, that means you cannot edit the value. However, you could click "God Mode" the enable the editing for easy debugging. When you click the "God Mode", it will automatically triggered a memory dump (print all memory data which not contains all zero).

#### Detail about "LOAD MEM" Button

LOAD MEM expects a CSV file which contains formatted MEM data. It should have 2 fields, which are 


<Memory Location(Binary String,12 bits)>,<Data(Binary String,16 bits)>.


Here is an example:


>000000000001,0000011100011111<br>000000000010,0000011100011111<br>000000000011,0000011100011111<br>000000000100,0000011100011111


The example above load data "0000011100011111" to memory location 001,010,011,100.

 - If you come up with a error of loading the file (Java Long Number Converting Problem), make sure your csv file format to UTF-8 (which is **NOT** the default of the Microsoft Excel). You can use NotePad++ to convert.

#### Usage of the assembly code translator (Only Support P1 Stage. Need update)
1. Download your testing program (assembly code) and save it into a txt file.<br>
2. Find the FileRead class in our project (src->FileReader->FileRead), and set the txtPath (Line 138) to yours.<br> 
(Remember to use 2 backslash when you want to indicate slash in string!) <br>
3. Run this class, and you will get the "Instruction.csv" file, which is compatible to the "LOAD MEM" function memtioned above.<br>


#### About testing 
  
 **Testing Program 0 (P1)**<br>
 This is the basic testcase for Stage 1 to test 5 LOAD/STORE Instr.<br>
 **Testing Program 1 (P2)**<br>
A program that reads 20 numbers (integers) from the keyboard, prints the numbers to the console printer, requests a number from the user, and searches the 20 numbers read in for the number closest to the number entered by the user. Print the number entered by the user and the number closest to that number. Your numbers should not be 1…10, but distributed over the range of 0 … 65,535. Therefore, as you read a character in, you need to check it is a digit, convert it to a number, and assemble the integer. <br>
 **Testing Program 2 (P3)**<br>
A program that reads a set of a paragraph of 6 sentences from a file into memory. It prints the sentences on the console printer. It then asks the user for a word. It searches the paragraph to see if it contains the word. If so, it prints out the word, the sentence number, and the word number in the sentence.<br>
## :construction: Project Work In Progress for P3 :construction:
For Testing Program 2: <br>
- For this stage, the Card Reader I/O Port is implemented as a file I/O in our simulator. So in that case if requested an IN instr at Card Reader (DevID = 2), the simulator will request an .txt file for this specific input.<br>
- Testing Program 2 requires to read a set of paragraph of 6 sentences. The sample file is contained in the file **TestProgram2-Paragraph.txt**. <br>
- For the main string compare logic, please refer to the C program contains in this project. The file is called **TestProgram2-Logic.c**. Reading this C file gives you a basic idea of what I'm trying to do in the assamble code. For a easy understanding, I also upload my hand-written translation notes **TestProgram2-HandWrittenLogic.pdf**. This includes some memory location identifier. And I've comments the main compare logic part in the assemable code, so it is much easier for you to read and understand.(the variable in the comment is the same as the C code and the hand-written logic).
