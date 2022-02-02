# CS308 Metro Project

Weight: 100% of the class mark

Completion date: 5th April at 23:59

Introduction

The aim of this exercise is to design and implement a multi-graph ADT and to use the multi-graph to model the Boston Metro System in a program that is able to provide directions to passengers on how to get from one station to another. The system must have a graphical user interface, and follows a properly decoupled Model-View-Controller software architecture.

You'll need to decide on the basic functionality of your program, design a GUI for it. At the minimum, the system should be possible to:

·      enter a start location

·      enter a destination

·      display the planned route

Your GUI should be easy to use and attractive. You will need to design the internal structure of your back-end, and test the program as a whole. A key requirement is that both your design and implementation decouples the multi-graph ADT from the rest of the system.  Both in your design and implementation the GUI and the back-end of the system are fully decoupled.  You need to specify an application programmer's interface (API) through which the GUI will access the components of the code. The benefit of decoupling this application into two parts (a front-end GUI and the back-end implementation) by creating a well-defined API is to allow for different types of front-end clients to access your system and use its functionality. 

The design must take the form of UML class diagram that focuses on the functionality presented by the multi-graph ADT and must clearly show how the Boston Metro System will use the ADT. It should be an attempt at decoupling the multi-graph from the Boston Metro System with some notes about how to represent the graph in code and how to implement the graph search.

The design of the API should show all the types that API exposes and a brief description of the functionality it supports. Two issues are: 1) whether the API will be passive, in the sense that the application simply treats it as a source of information, or whether it will be active, by having functionality to make periodic call-backs; 2) what types will be exposed at the interface. Your API specification will be a complete description of the functionality of the program.

So you will need to devise a reasonable testing strategy that gives you some confidence that the application works reliably. 

This exercise also expects that you put in practice all the things that have been covered in the class, i.e. method specifications, Java assertions and JUnit tests.

This exercise will be carried out in groups (see group and lab allocation).

Submission Requirements

You should submit a zip file including all your code (all .java files), including text files with instructions on how to run them. Note that we primarily need your source code, so make sure that it is clean (do not include earlier or multiple versions). In addition to your source code, you could include also a jar file to run your system. If not then ensure that no class files are included in the submission.

In addition to the code the zip file should also include a document (maximum 16 sides of A4 in total) to cover the following content:

1.     A terse and precise description of what the application's purpose is; what kinds of functionality it supports; what your GUI looks like and how it is to be used. Think of this section as a compressed user manual: the kind of summary of an application often found on marketing flyers. It should also include screenshots of the GUI showing how it is organised and what functions it offers. 

2.     A UML diagram which shows the classes, interfaces, associations etc. and methods for your complete design including classes used to implement the multi-graph and the Boston Metro System, GUI, API, and back-end. The class diagram must be included as an image file that one can zoom in to see the full details! You should include parameters and return types in the method headings. This should be accompanied by a brief description of your diagram that explains the role of each interface and each class in the design; explains the relationships between the interfaces and classes that you have identified; each method that you have identified in each interface and class

3.      A brief document describing the overall design rationale. A brief overview of the representation you chose for the multi-graph along with a brief rationale which justifies your choice. Describe the extent to which the ADT presents a consistent abstraction and the adequate use of interface, and the extent to which the design presents a consistent abstraction and the adequate use of interface. Describe the extent to which the design decouples the multi-graph from the Boston Metro System, the extent to which the design decouples the front-end from the back-end.

4.     Specification of API. An overview of the API through which the GUI will access the components of the code. Also, a detailed specification describing each method in the API with the standard requires/modifies/effects form

5. A brief document outlining the rationale and results of your tests
6. Completed Peer assessment and Self-assessment form (separated instructions will be made available on myPlace near the time)  .
Process

This exercise will be carried out in two steps. The idea is to build your final submission gradually receiving feedback in between in order to improve the quality of the final submission.

1.     Each group  must prepare and bring to the lab in week 7 (w/c  28th Feb) an initial design of the Boston Metro System.

In the week after you will receive feedback for your submitted design by the group tutor and you will work to adjust your design to take the feedback into account,  refactor your code to follow your revised design and complete your Boston Metro System application.

Note: this will be treated as an informal process and will not be marked. 

2.     Based on this exercise, you should agree on common design on which you are going to base your implementation and you should start building your multi-graph and GUI(i.e. decide on the particular multi-graph representation to follow, the multi-graph traversal algorithm to use and get coding). 

Marking Scheme

The marks are divided equally between the design and the implementation, i.e. 40% each, plus 20% from the peer, self and group tutor assessment – more details can be found in the Marking Distribution document
