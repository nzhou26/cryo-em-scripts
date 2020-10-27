#!/bin/bash
mkdir lowpass
for ((i =0; i < 20000; i++))
	do
		for file in ./*.mrc  
		do
	        if [ ! -f "lowpass/$file" ]; then

			echo $(relion_image_handler --i $file --o lowpass/$file --angpix 0.88 --lowpass 20)
			echo $file
		fi
		done
	done

exit 0 		
