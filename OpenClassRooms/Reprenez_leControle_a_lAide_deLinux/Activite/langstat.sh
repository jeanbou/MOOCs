#!/bin/bash

# Assigned the first and the second params to variable
FILE=$1
PARAM2=$2

if [ ! -n "$FILE" ] # Check the avialability of the file or in terms of parameters, the existance of the first param
then
	echo "No parameters, RTFM:"
	cat RTFM.txt
elif [ ! -f "$FILE" ] # Check the existance of the file  
then
	echo "Given file $FILE does not exists"
elif [ ! -s "$FILE" ] # Check if the file is zero size or not
then
	echo "Given file $FILE is empty"
elif [ ! -n "$PARAM2" ] # No second parameter, so run the basic required functionality
then
	echo "Patience please, it computes the stat for the characters including - and <space>"
	# it also ignores case (however the dico.txt is well formated. just in case)
	grep -o . "$FILE" | sort -f | uniq -ic
elif [ "$PARAM2" == "-w" ] # calculate the number of lines, equivalent the numbers of lines
then 
	echo "The number of lines/words:"	
	wc -l "$FILE"
else
	echo "The second parameter $PARAM2 is not recognized, RTFM"
	cat RTFM.txt		
fi
