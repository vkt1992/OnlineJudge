#!/bin/bash
filecpp=$1
filetxt=$2
filext=$3
g++ $filecpp -o $filext
if [ $? -ne 0 ]
then
	echo "Compilation Error"
else
	./$filext > $filetxt # ./$filext < inputfile3.txt > $filetxt
	if [ $? -ne 0 ]
	then
		echo "Runtime Error"
	else	
		diff $filetxt "outputfile3.txt" # diff $filetxt outputfile3.txt
		if [ $? -ne 0 ]
		then
			echo "Output Not Match error_3"
		else
			echo "Correct Answer_3"
		fi
	fi
fi
