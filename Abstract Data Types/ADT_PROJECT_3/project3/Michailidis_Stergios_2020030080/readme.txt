Για να τρέξουμε την εφαρμογή από commnd line

τοποθετούμε τα bin αρχεία με τα κλειδιά στον ίδιο φάκελο με τη εφαρμογή  και τρέχουμε την εφαρμογή με

java -jar smichailidis_2020030080.jar

 

Για να τρέξουμε την εφαρμογή από το IDE, πρέπει πρώτα να αλλάξουμε το current  path του project
έτσι ώστε να δείχνει στον φάκελο όπου βρίσκονται τα bin αρχεία.

Δεξί click στο project -> properties -> Run/Debug Settings -> Αριστερο click στην MainClass (1) -> edit ->
 -> Arguments -> Working Directory -> Other -> Επιλεγετε το directory με τα bin files σας -> apply and close
 

********Χρόνος εκτέλεσης του προγράμματος********

Μερικα δευτερόλεπτα , χωρίς τα B+ trees.

4 ώρες με τα B+ trees, χρησιμοποιώντας επεξεργαστή intel core i5 - 4690K στα 3,5 GHz , μνήμη RAM HyperX Fury DDR3 στα 1866 MHz και sata III ssd δισκο.


====================
Implementation notes
====================


Σχετικά με την προσθήκη μετρητών αριθμού αναθέσεων και συγκρίσεων.

Σε περίπτωση που μία σύγκριση κλειδιού γίνεται σε σύνθετη λογική παράσταση

 

π.χ.

if (i<num && node[i].key>key) 

 

τότε σπάω την συνθήκη σε δύο μέρη έτσι ώστε ο μετρητής να λειτουργήσει σωστά , όπως

 

If (i<num) {

     ++counter;

     If (node[i].key>key)

}

 

 

Πηγές:

 

https://programmer.group/b-tree-java-implementation.html

(στην συγκεκριμένη υλοποίηση εντόπισα και διόρθωσα λάθος στην μέθοδο search())



για AVL

https://www.geeksforgeeks.org/avl-tree-set-2-deletion/