for ((i=0; i<10000; i++)) 
do rsync -avh --progress $1 $2
echo -e  "\n\n==================\nwaiting\n==================\n"
sleep 10
done 
