
# CSCI 6461 Computer Architecture 
This is our CSCI6461 Simple Computer Simulator for George Washington University, Fall 2019, Group 1.<br>

#### About the Mannual
Project Mannual is avilable at:<br>
http://www.mslcourses.com/CSCI6461Section10Fall2019/AdaptedKaislerFiles/ClassProjectDescription.doc
## Download and Execute
#### Usage Of this simulator
- Clone the Project first, and checkout master branch.
> git clone https://github.com/skyguidance/CSCI6461ComputerArchitecture.git
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
## :construction: Project Work In Progress for P2 :construction:
