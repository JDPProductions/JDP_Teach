#By: Jesus Arechiga, Justin Passanisi, Chris Van Horn

#!/usr/bin/env python
import sys
import argparse
import copy
import pdb

open_list = []
closed_list = []
nodes = []
startnode = None

# Argument parser for added commands in console
# Parser command 1: Input File
#	No default - Requires input file
# Parser command 2: Solution Type
#	Default when no command is set - Manhattan
# EXAMPLE: python nario.py level01 euclidean
parser = argparse.ArgumentParser(description='Nario A*')
parser.add_argument('inputfile', help='a file containing the start state for Nario')
parser.add_argument('heuristic', nargs='?', default='manhattan', help='[manhattan|euclidean|custom] default=manhattan')
args = parser.parse_args()

# Set heuristic variable value to parser command 2
if args.heuristic == 'euclidean':
    heuristic=args.heuristic
elif args.heuristic == 'custom':
    heuristic=args.heuristic
else:
    heuristic='manhattan'

class node:
    def __init__(self,
	            parent=None,
				children=[],
				g_cost=0,
				x=None,
				y=None,
				char='='):
        self.parent = parent
        self.children = children
        self.g_cost = g_cost
        self.x = x
        self.y = y
        self.char = char

    def h_cost(self):
        if heuristic == 'euclidean':
            return (self.x**2 + self.y**2)**.5
        elif heuristic == 'manhattan':
            return self.x + self.y
        elif heuristic == 'custom':
            return len(self.children)

    def f_cost(self):
        return self.h_cost() + self.g_cost

    def __repr__(self):
        return self.char

with open(args.inputfile, 'r') as f:
    # read file into disjointed nodes
    for i, line in enumerate(f):
        line.split()
        noderow = []
        for j, c in enumerate(line.strip()):
            n = node(x=i, y=j, char=c, children=[], g_cost=0)
            noderow.append(n)
            if c == '@':
                startnode = n
        nodes.append(noderow)

    # iterate through nodes and connect children to make a global path
    for i, row in enumerate(nodes):
        for j, node in enumerate(row):
            if node.char != '=':
                # add children left and right
                if nodes[i][j-1].char != '=':
                    node.children.append(nodes[i][j-1])
                    nodes[i][j-1].children.append(node)
                # add children up and down but not top and bottom
                if nodes[i-1][j].char != '=' and node is not nodes[0][j]:
                    node.children.append(nodes[i-1][j])
                    nodes[i-1][j].children.append(node)

# Method: Print original matrix
def printmap(s):
    print
    for row in s:
        line = ''
        for e in row:
            line += str(e)
        print(line)
    return


# Method: prints all steps/coords taken to goal
def tracesteps(node):
    step = []
    while str(node) != '@':
        step.append(node)
        node = node.parent
    print('path: ',[(s.x, s.y) for s in step])
    step2 = step[::-1]
    prev = None
    nodes[startnode.x][startnode.y].char = '.'
    for s in step2:
        if prev != None:
            nodes[prev.x][prev.y].char = '.'
        nodes[s.x][s.y].char = '@'
        prev = s
        # prints each step.
        printmap(nodes)
        print("\n")
    printmap(nodes)

#Function A* - Enumerates over the nodes and contructs steps concerning hueristic
def astar():
    open_list.append(startnode)
    while open_list != []:
        min_f_cost = float('inf')
        min_node_index = None
        for i, node in enumerate(open_list):
            if node.f_cost() < min_f_cost:
                min_f_cost = node.f_cost()
                min_node_index = i
        q = open_list.pop(min_node_index)

        for successor in q.children:
            successor.parent = q
            skip = False
            if (successor.x, successor.y) == (0, 0):
                tracesteps(successor)
                return 'Solution found'
            else:
                successor.g_cost = q.g_cost + 1
            for n in open_list:
                if ((n.x, n.y) == (successor.x, successor.y) and
				    (n.f_cost() <= successor.f_cost())):
                    skip = True
            for n in closed_list:
                if ((n.x, n.y) == (successor.x, successor.y) and
				    (n.f_cost() <= successor.f_cost())):
                    skip = True
            if (skip == False):
                open_list.append(copy.copy(successor))
                open_list[len(open_list)-1].parent = successor.parent
        closed_list.append(copy.copy(q))
    return 'Solution not found'

printmap(nodes)
print(astar())