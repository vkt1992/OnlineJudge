#!/bin/bash
filecpp=$1
filetxt=$2
filext=$3
g++ $filecpp -o $filext
if [ $? -ne 0 ]
then
	echo "Compilation Error"
else
	./$filext > $filetxt # ./$filext < inputfile2.txt > $filetxt
	if [ $? -ne 0 ]
	then
		echo "Runtime Error"
	else
		diff $filetxt "outputfile2.txt" # diff $filetxt outputfile2.txt
		if [ $? -ne 0 ]
		then
			echo "Output Not Match error_2"
		else
			echo "Correct Answer_2"
		fi
	fi
fi
