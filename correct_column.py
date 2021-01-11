#!/usr/bin/python3

import sys
import os

os.system('mkdir done')
save_path = './done/'
def getContent(lines):
    content= []
    for line in lines:
        if len(line.split()) > 3 and line.split()[0] != "#":
            row = []
            for x in line.split():
                row.append(x)
            content.append(row)
    return content
standard_header = [
    '# RELION; version 3.0.8\n',
    "\n",
    'data_\n',
    "\n",
    "loop_ \n",
    "_rlnCoordinateX #1 \n",
    "_rlnCoordinateY #2 \n",
    "_rlnAngleRot #3 \n",
    "_rlnAngleTilt #4 \n",
    "_rlnAnglePsi #5 \n",
    "_rlnMicrographName #6 \n",
    "_rlnAutopickFigureOfMerit #7 \n",
    "_rlnClassNumber #8 \n"
]
for star in sys.argv[1:]:
    f = open(star, "r+")
    lines = f.readlines()
    i = 0
    para = 0
    file_to_write = os.path.join(save_path, star)
    for line in lines:
            if "_rln" in line:
                    para = para + 1
            i=i+1
            if i == 13 :
                break
    if para == 8:
        os.system('scp ' + f.name + ' done/')
        print(f.name, "has ", para, " columns and has been copied to done") 
        f.close
    elif para == 4:
        ## if the star file is not manual picked by either relion or warp
        content = getContent(lines)
        f.close
        ##print(f.name, content)
        fw = open(file_to_write, "w")
        fw.writelines(standard_header)
        for row_w in content:
            fw.write(row_w[0] + " ")
            fw.write(row_w[1] + " ")
            fw.write(" 0 ")
            fw.write(" 0 ")
            fw.write(" 0 ")
            fw.write(row_w[2] + " ")
            fw.write(row_w[3] + " ")
            fw.write(" 0 \n")
        ##print(fw.name, "has ", para, " columns and has been copied to done")
        fw.close
    elif para == 6:
        ## if the star file is manually picked by relion
        content = getContent(lines)
        f.close
        ##print(f.name, content)
        fw = open(file_to_write, "w")
        fw.writelines(standard_header)
        for row_w in content:
            fw.write(row_w[0] + " ")
            fw.write(row_w[1] + " ")
            fw.write(" 0 ")
            fw.write(" 0 ")
            fw.write(row_w[5] + " ")
            fw.write(row_w[2] + " ")
            fw.write(row_w[3] + " ")
            fw.write(row_w[4] + " \n")
        print(fw.name, "has ", para, " columns and has been copied to done")
        fw.close
    elif para == 7:
        content = getContent(lines)
        f.close
        fw = open(file_to_write, "w")
        fw.writelines(standard_header)
        for row_w in content:
            fw.write(row_w[0] + " ")
            fw.write(row_w[1] + " ")
            fw.write(row_w[2] + " ")
            fw.write(row_w[3] + " ")
            fw.write(row_w[4] + " ")
            fw.write(row_w[5] + " ")
            fw.write(row_w[6] + " ")
            fw.write(" 0 \n")
        print(fw.name, "has ", para, " columns and has been copied to done")
        fw.close
        ## if the star file is mananlly picked by warp
print("other star files have 4 columns")
    
