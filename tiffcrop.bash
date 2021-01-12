#!/bin/bash
mkdir tiffcrop
for ((i = 0; i < 10000; i++))
	do
	for file in ./*.tif 
		do
		if [ ! -f "tiffcrop/$file" ]; then
        		echo $(tiffcrop -U px -m 0,4608,0,0 $file tiffcrop/$file)
			echo $file
		fi
	done
	echo -e "\n\n==================\nwaiting\n==================\n"
	sleep 10
done
exit 0

