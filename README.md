# CSCI 6461 Computer Architecture
This is our 6461 Simple Computer Simulator<br>
Project Mannual:http://www.mslcourses.com/CSCI6461Section10Fall2019/AdaptedKaislerFiles/ClassProjectDescription.doc
#### Clone This Project

git clone https://github.com/skyguidance/CSCI6461ComputerArchitecture.git

#### Update to the update-to-date version

> git pull 

#### Detail about "LOAD MEM" Button

LOAD MEM expects a CSV file which contains formatted MEM data. It should have 2 fields, which are 


<Memory Location(Binary String)>,<Data(Binary String)>.


Here is an example:


>000000000001,0000011100011111<br>000000000010,0000011100011111<br>000000000011,0000011100011111<br>000000000100,0000011100011111


The example above load data "0000011100011111" to memory location 01,10,11,100.

#### Usage of the assembly code translator
1. Download your testing program (assembly code) and save it into a txt file.<br>
2. Find the FileRead class in our project (src->FileReader->FileRead), and set the txtPath (Line 138) to yours.<br> 
(Remember to use 2 backslash when you want to indicate slash in string!) <br>
3. Run this class, and you will get the "Instruction.csv" file, which is compatible to the "LOAD MEM" function memtioned above.<br>


#### About testing 
  
 There are some cases that could not be implied   

## :construction: Project Work In Progress for P2 :construction:
