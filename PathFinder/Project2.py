from node import node #custom node class
import sys

sys.setrecursionlimit(10**4) #increasing the recursion limit
input_file_name = sys.argv[1] #accesing the first command line parameter
graph={}
matrix=[]
output_file_name = sys.argv[2] #accessing the second command line parameter
paths=[]

#This function is used to parse the text file and convert into nodes and then to a 2-D matrix
def parseFile(input_file_name):
    count=1
    with open(input_file_name) as f: #opening the file
        lines = f.readlines()[1:] #reading the file
        for line in lines: 
            row=[]
            line=line.strip()
            for n in line.split(" "): #splitting the line with spaces 
                row.append(node(n,"n"+str(count))) #creating the new label like n1,n2,n3 ... and creating the node object
                count+=1
            matrix.append(row) #appending the row to matrix
    generateGraph() #calling the function
#This function is used to create the graph from the matrix created from the above function
def generateGraph():
     #traversing the matrix
    for i in range(0,len(matrix)):
        for j in range(0,len(matrix)):
            key=matrix[i][j]
            color=matrix[i][j].color #accessing the color of the current node
            if matrix[i][j].direction=="N":
                k=i
                while k-1>=0: #moving up in the matrix 
                    if matrix[k-1][j].color!=color: #checking if colors are opposite
                        if key not in graph.keys():   #checking if the curentnode is in graph or not 
                            # print(color)
                            graph[key]=[matrix[k-1][j]]
                        else:
                                graph[key].append(matrix[k-1][j]) #appending the nodes as values into the list
                    k-=1

            elif matrix[i][j].direction=="S":
                k=i
                while k+1<len(matrix) : #moving down to south in the matrix
                    if matrix[k+1][j].color!=color: #checking if colors are opposite
                        if key not in graph.keys(): #checking if the curentnode is in graph or not 
                            graph[key]=[matrix[k+1][j]]
                        else:
                            graph[key].append(matrix[k+1][j]) #appending the nodes as values into the list
                    k+=1
            elif matrix[i][j].direction=="E":
                k=j
                while k+1<len(matrix) : #moving in east direction
                    if matrix[i][k+1].color!=color:#checking if colors are opposite
                        if key not in graph.keys(): #checking if the curentnode is in graph or not 
                            graph[key]=[matrix[i][k+1]]
                        else:
                            graph[key].append(matrix[i][k+1]) #appending the nodes as values into the list
                    k+=1
            elif matrix[i][j].direction=="W":
                k=j
                while k-1>=0 : #moving in west direction
                    if matrix[i][k-1].color!=color:#checking if colors are opposite
                        if key not in graph.keys():#checking if the curentnode is in graph or not 
                            graph[key]=[matrix[i][k-1]]
                        else:
                            graph[key].append(matrix[i][k-1])#appending the nodes as values into the list
                    k-=1
            elif matrix[i][j].direction=="NE":
                k,l=i,j
                while k-1>=0 and l+1<len(matrix) : #moving in nort east direction
                    if matrix[k-1][l+1].color!=color:#checking if colors are opposite
                        if key not in graph.keys():#checking if the curentnode is in graph or not 
                            graph[key]=[matrix[k-1][l+1]]
                        else:
                            graph[key].append(matrix[k-1][l+1])#appending the nodes as values into the list
                    k-=1
                    l+=1
            elif matrix[i][j].direction=="NW":
                k,l=i,j
                while k-1>=0 and l-1>=0: #moving in north west direction
                    if matrix[k-1][l-1].color!=color:#checking if colors are opposite
                        if key not in graph.keys():#checking if the curentnode is in graph or not 
                                graph[key]=[matrix[k-1][l-1]]
                        else:
                                graph[key].append(matrix[k-1][l-1])#appending the nodes as values into the list
                    k-=1
                    l-=1

            elif matrix[i][j].direction=="SE":
                k,l=i,j
                while k+1<len(matrix) and l+1<len(matrix) : #moving in south east direction
                    if matrix[k+1][l+1].color!=color:#checking if colors are opposite
                        if key not in graph.keys():#checking if the curentnode is in graph or not 
                            graph[key]=[matrix[k+1][l+1]]
                        else:
                                graph[key].append(matrix[k+1][l+1])#appending the nodes as values into the list
                    k+=1
                    l+=1
            elif matrix[i][j].direction=="SW":
                k,l=i,j
                while k+1<len(matrix) and l-1>=0 : #moving in south west direction
                    if matrix[k+1][l-1].color!=color:#checking if colors are opposite
                        if key not in graph.keys():#checking if the curentnode is in graph or not 
                            graph[key]=[matrix[k+1][l-1]]
                        else:
                            graph[key].append(matrix[k+1][l-1])#appending the nodes as values into the list
                    k+=1
                    l-=1
    keys=graph.keys() #getting all the keys in graph
    for i in range(0,len(matrix)):
        for j in range(0,len(matrix)):
            if matrix[i][j] not in keys:#adding empty list as values for the nodes with no edges 
                graph[matrix[i][j]]=[]
    for i,j in graph.items():
        print(str(i.color+"-"+i.direction)+str([n.color+"-"+n.direction for n in j]))   
#This function is used to store the path          
def storePath(p):

    #calling the function to count steps between nodes
    getSteps(p,0,0) #passing path and 0,0 as initial values of matrix like i=0,j=0
#this function is used to write the solution into file
def writeOutput(step):
    final_path=" ".join(step) # merging the elements array into string with spaces

    with open(output_file_name,"w") as file: #opening the file in write mode
        file.write(final_path) #writing into the file
#This function is used to caluclate steps between the nodes
def getSteps(minimumPath,i,j):
    step=[]
    for n in range(0,len(minimumPath)-1):#iterating the path list
        steps=0
        label=minimumPath[n].label # accessing the label of the node
        direction=minimumPath[n].direction #accesing the direction of the node
        if direction=="E":
            while(j<len(matrix)): #moving in the east direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    j+=1
        elif direction=="W":
            while(j>=0):#moving in the west direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    j-=1
        elif direction=="N":
            while(i>=0): #moving in the north direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    i-=1
        elif direction=="S":
            while(i<len(matrix)): #moving in the south direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    i+=1
        elif direction=="NE":
            while(i>=0 and j<len(matrix)): #moving in the north east direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    i-=1
                    j+=1
        elif direction=="NW":
            while(i>=0 and j>=0): #moving in the north west direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    i-=1
                    j-=1
        elif direction=="SE":
            while(i<len(matrix) and j<len(matrix)): #moving in the south east direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    i+=1
                    j+=1
        elif direction=="SW":
            while(i<len(matrix) and j>=0): #moving in the south west direction in the matrix
                if matrix[i][j].label==minimumPath[n+1].label: #check if current node is next node in path
                    break
                else:
                    steps+=1
                    i+=1
                    j-=1
        step.append(str(steps)+direction) #adding the steps to list

    writeOutput(step) #calling the function
#This function traverses the graph and finds the way to bullseye
def getPath(curr,destination,path):
    curr.visited=True #marking the current node as visited
    path.append(curr) #adding the node into path
 

    if curr == destination:
        storePath(path) #sending the path to other function when bullseye is found
    else:
            # If current vertex is not destination Recur for all the vertices adjacent to this vertex
        for adjNode in graph[curr]: #accesing the connected nodes of current nodes
            if not adjNode.visited: #check if the node is visited or not
                getPath(adjNode, destination,  path)   #recursive call     
       
    path.pop() #removing the vertex from path


  

parseFile(input_file_name) #calling the function to parse the text file

getPath(matrix[0][0],matrix[len(matrix)-1][len(matrix)-1],[]) #calling the function to find the path







