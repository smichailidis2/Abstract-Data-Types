=================================
2020030080 - Stergios Michailidis
=================================

Zip file content:

2020030080_adt_1.jar    ... main application, It creates all data structures during loading .bin file (coordinates file)
XYFileGen.jar           ... coordinates file generator
sources (folder)        ... source code for 2020030080_adt_1 and XYFileGen application (Exported zip files from Eclipse IDE)
Documentation (folder)  
bin_files (folder)      ... pre-generated coordinate files (.bin files)

Unzip 2020030080_Project1.zip file into a folder. We will call this foldes as 'working folder'
Run commnd line (or terminal) console and switch to the working folder.




How to execute applications
---------------------------
To run XYFileGen type

       java -jar XYFileGen.jar k=number of coordinates filename=output file name
f.e.
       java -jar XYFileGen.jar k=1000 filename=K1k.bin
       java -jar XYFileGen.jar k=50000 filename=K50k.bin    etc.

           bin file is created in the current working directory

If you prefer to use pre-generated coordinate files, copy them from bin_files folder to the working folder.


To run 2020030080_adt_1 application type

       java -jar 2020030080_adt_1.jar    and follow application's menu options
       
       2020030080_adt_1 app expects that coordinate file is located in the working directory.
       All files related to the disc data structures are created in the working directory.
       


File name conventions
---------------------

.lst  - Disc Linked List file
.hlst - Hash Disc Linked list file
.ser  - Disc list hash array serialization file

F.e. if K30k.bin is loaded, then K30k.lst, K30k.hlst are created. When exiting the application K30k.ser file is created.



Application notes
-----------------
XYFileGen:
XYFileGen application generates required number of coordinates. If generated random cordinate was already generated
the new one is rejected.

2020030080_adt_1:
1. When bin file is loaded (menu option 0), all data strucures are created.
2. Running menu option 9, HashDisc Linked List is restored, insert and search functions are avaliable. 

!!!! perigrafi ton ypoloipon leitoyrgion !!!!!





IMPLEMENTED FUNCTIONALITIES
---------------------------
(simple list of functionalities!!, kai to serialization)


NOT IMPLEMENTED FUNCTIONALITIES
--------------------------------
Due the lack of time ,... 
