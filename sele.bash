#!/bin/bash 
mkdir legit
for file in ./*.tif
	do 
		f=$(basename -- "$file")
		num=${f:7:1}
		if [[ $num == "0" || $num == "2" || $num == "4" || $num == "6" || $num == "8" ]];
		then
			echo $(mv $file legit)
			echo $file
		fi
	done

