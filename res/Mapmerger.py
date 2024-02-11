# 1. remove first 6 lines
# 2. put residing lines in an array (4x)
# 3. Loop through array and write in file
# size per file = 6000*8750

file_top_left = open("DOM_1227_21.asc")
file_top_right = open("DOM_1227_22.asc")
file_bottom_left = open("DOM_1227_23.asc")
file_bottom_right = open("DOM_1227_24.asc")

height = 6000
width = 8750

heigthPoints = [[None for i in range(width*2)] for j in range(height*2)]

# y is the y coordinate in the file
# l is the string of the line (y coord)
for y,l in enumerate(file_top_left): 
    for x, dataPoint in enumerate(l.strip().split(sep=" ")):
        heigthPoints[y][x] = float(dataPoint)

for y,l in enumerate(file_top_right): 
    for x, dataPoint in enumerate(l.strip().split(sep=" ")):
        heigthPoints[y][x+width] = float(dataPoint)

for y,l in enumerate(file_bottom_left): 
    for x, dataPoint in enumerate(l.strip().split(sep=" ")):
        heigthPoints[y+height][x] = float(dataPoint)

for y,l in enumerate(file_bottom_right): 
    for x, dataPoint in enumerate(l.strip().split(sep=" ")):
        heigthPoints[y+height][x+width] = float(dataPoint)

file_top_left.close()
file_top_right.close()
file_bottom_left.close()
file_bottom_right.close() 

outputFile = open("NiesenHeights.asc", "w")
for y in range(0,height*2, 10):
    for x in range(0, width*2, 10):
        outputFile.write(str(heigthPoints[y][x]))
        if(x < width*2-10):
            outputFile.write(" ")
    outputFile.write("\n")

outputFile.close()   