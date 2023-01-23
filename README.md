# System Test Plan Manager

Program was created as a school assignment in a group of two. It stores information regarding system 
tests as well as a history of tests that have previously been run, so users can see how their tests
have progressed over time. It is capable of reading and writing program state to formatted files to 
preserve program state.

GUI code was provided alongside UML class diagram with descriptions of what the given methods should
do. All other code and tests were written from scratch. Program can be started from the main method 
in SystemTestPlanGUI. A new test plan must be created with the add test plan button before new tests 
can be created. Files can be read or written to using the File button in the top left of the GUI.

Junit tests must be ran with P2/ as the base directory because all file IO tests use this directory
as a base for relative file paths.
