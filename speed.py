#!/usr/bin/python3
import ntpath
import sys
from datetime import datetime
f = open(sys.argv[1], "r")
lines = f.readlines()
f.close
number = []
time = []
date = []
i = 0
csv = []
list_of_time = []
speed_table =[]
out = open("speedof" + sys.argv[1], 'a')
for line in lines:
    filename = ntpath.basename(line)
    m = len(filename.split("_"))
    number.append(filename.split("_")[m-3])
    date.append(filename.split("_")[m-2])
    time.append(filename.split("_")[m-1][:8])
    #print(store)
    month = date[i][:3]
    if month == "Feb":
        month = 2
    elif month == "Mar":
        month = 3
    day = int(date[i][4:])
    hour = int(time[i].split(".")[0])
    mins = int(time[i].split(".")[1])
    sec = int(time[i].split(".")[2])
    ##print(month,day,hour,mins,sec)
    dt = datetime(2021, month, day, hour, mins, sec)
    ##print(dt.time())
    list_of_time.append(dt)
    ##print(i, list_of_time[i])
    if i != 0 and int(number[i]) - int(number[i-1]) == 1:
        elapse = list_of_time[i] - list_of_time[i -1]
        #elapse = datetime.combine(list_of_time[i].min, end) - datetime.combine(list_of_time[i-1], start)
        speed_table.append(elapse.seconds)
        if elapse.seconds < 60:
            out.write(str(elapse.seconds))
            out.write('\n')
    i += 1
#print(speed_table)