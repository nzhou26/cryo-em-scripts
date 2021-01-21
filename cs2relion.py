#!/usr/bin/python3
import sys
def header(lines):
    i = 5
    while i < 50:
        if "_rln" not in lines[i]:
            return i
        i = i + 1 
    
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
## read input file, file1 for cryosparc file, file2 for relion file
f = open(sys.argv[1], "r+")
lines = f.readlines()
f.close()
f2 = open(sys.argv[2], "r+")
lines2 = f2.readlines()
f2.close()

## write output file
out = open(sys.argv[3], "w")
i = 0

##write header from relion file
print(header(lines2))
while i < header(lines2):
    out.write(lines2[i])
    i += 1

##get parameter as dict
para_list = dealStar(lines)

##get content of cs file
content = getContent(lines)

##find out desired image name
desired = []
k = 0
for line in content:
    row = []
    row.append(int(line[dealStar(lines).index("_rlnImageName")].split("@")[0]))
    row.append(line[dealStar(lines).index("_rlnImageName")].split("@")[1].split("/")[2])
    desired.append(row)
    k = k + 1

##match relion with desired
content2 = getContent(lines2)
j = 0

for line in content2:
    row = []
    row.append(int(line[dealStar(lines2).index("_rlnImageName")].split("@")[0]))
    row.append(line[dealStar(lines2).index("_rlnImageName")].split("@")[1].split("/")[3])
    
    if row in desired:
        
        for x in line:
            out.write(" ")
            out.write(x)
            out.write(" ")
        out.write("\n")
        desired.remove(row)
        j += 1
    print("%.0f%%" % (100 * j/k))
out.close()
print("number of your desired particles is ", k)
print("number of matched particles is ", j)
if j != k:
    print("the number did not match, this might be caused by your relion input star file is not complete")


