#!/bin/bash
filecpp=$1
filetxt=$2
filext=$3
g++ $filecpp -o $filext
if [ $? -ne 0 ]
then
	echo "Compilation Error"
else
	./$filext > $filetxt # ./$filext < inputfile1.txt > $filetxt
	if [ $? -ne 0 ]
	then
		echo "Runtime Error"
	else		
		diff $filetxt "outputfile1.txt" # diff $filetxt outputfile1.txt
		if [ $? -ne 0 ]
		then
			echo "Output Not Match error_1"
		else
			echo "Correct Answer_1"
		fi
	fi
fi
