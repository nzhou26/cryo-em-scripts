#!/usr/bin/python3
import sys
import os
def header(lines):
    i = 0
    lst = []
    while i < 50:
        if "_rln" in lines[i]:
            lst.append(i)
        i = i + 1
    return lst[len(lst) - 1]
    
def dealStar(lines):
    i = 0
    lst = []
    while i < 50:
        if "_rln" in lines[i]:
            line = lines[i].split(" ")
            lst.append(line[0])
        i += 1
    return lst

def getContent(lines):
    para_list =  dealStar(lines)
    content = []
    for line in lines:
        if len(line.split()) == len(para_list):
            row = []
            for x in line.split():
                row.append(x)
            content.append(row)
    return content
def writeHeader(fileName):
    out= open(fileName, "w")
    out.write("\n")
    out.write("data_")
    out.write("\n")
    out.write("\n")
    out.write("loop_")
    out.write("\n")
    out.write("_rlnCoordinateX #1")
    out.write("\n")
    out.write("_rlnCoordinateY #2")
    out.write("\n")
    out.write("_rlnMicrographName #3")
    out.write("\n")
    out.write("_rlnAutopickFigureOfMerit #4")
    out.write("\n")
    out.write("_rlnClassNumber #5")
    out.write("\n")
    out.write("_rlnAnglePsi #6")
    out.write("\n")
    out.close
if len(sys.argv) != 2:
    print("usage: ./clean_pyck.py particles.star")
    exit()
f = open(sys.argv[1], "r")
lines = f.readlines()
f.close
os.mkdir("cleaned")
content = getContent(lines)
old_mrc_name = []
i =0
for line in content:
    mrc_name = line[dealStar(lines).index("_rlnMicrographName")]
    if not os.path.isfile("cleaned/"+mrc_name+".star"):
        writeHeader("cleaned/"+mrc_name+".star")
    out = open("cleaned/"+mrc_name+".star", "a")
    out.write(" ")
    out.write(line[dealStar(lines).index("_rlnCoordinateX")])
    out.write(" ")
    out.write(line[dealStar(lines).index("_rlnCoordinateY")])
    out.write(" ")
    out.write(mrc_name)
    out.write(" ")
    out.write("0")
    out.write(" ")
    out.write("0")
    out.write(" ")
    out.write("0")
    out.write("\n")
    out.close
    print("generating coordinates file for ", mrc_name)
    i += 1
    print("progress:")
    print("%.0f%%" % (100 * i/len(content)))
print("Done! Coordinates file stored in cleaned.")
