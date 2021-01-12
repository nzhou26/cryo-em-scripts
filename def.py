lst = open ("lst", "r")
names = lst.readlines()
lst.close()

for name in names:
	a_file = open (name.strip("\n"), "r")
	lines = a_file.readlines()
	a_file.close()
	
	new_file = open("a_" + name.strip("\n"), "w")
	for line in lines:
		if len(line.split( )) != 4:
			new_file.write(line)
		elif float(line.split( )[0]) < 3310 or float(line.split( )[0]) > 3545:
			new_file.write(line)
	new_file.close()
			
