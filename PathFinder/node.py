class node:
    visited=False
    def __init__(self,list,label):
        if list.split("-")[0]!="O":
            self.color,self.direction=list.split("-")
            self.label=label
        else:
            self.label,self.color,self.direction="Bulls Eye","none","end"
    