#!/usr/bin/env python
import re
import copy

# position on row or column
p = {'A':0,'B':1,'C':2,'D':3,'c':4,'b':5,'a':6}

# direction of move
d = {'N':6,'S':0,'E':0,'W':6}

#max depth for cutoff
max_depth = 3

# count number of times utility(s) is called to see if pruning works
# cu=0

# list comprehension to create empty board
initialboard = [['E' for i in range(len(p))] for j in range(len(p))]

#Class that represents the current state, or set of values, on the board
class state(object):
    def __init__(self,
                 lastmove = 'None',
                 board = initialboard,
                 turn = 'MIN'):
        self.lastmove = lastmove
        self.board = board
        self.turn = turn
        self.parent = None
        self.v = None

    # return the computed utility(s) of state
    def __repr__(self):
        return repr(self.v)

    # method that prints the board at the current state
    def printboard(self):
        b = self.board
        pos = ['A','B','C','D','c','b','a']
        div = ' +-+-+-+-+-+-+-+'
        hlabel = '    '
        for i in pos:
            hlabel += i + ' '
        print str('N').rjust(10, ' ')
        print hlabel
        print ' ', div
        for i in range(len(b)):
            line = '  ' + pos[i]
            for j in b[i]:
                line += '|' + j
            line += '|' + pos[i]
            print line
            if i==2:
                print 'W', div, ' E'
            else:
                print ' ', div
        print hlabel
        print str('S').rjust(10, ' ')

    #method to switch turns
    def nextplayer(self):
        if self.turn == 'MIN':
            return 'MAX'
        else:
            return 'MIN'

# method for when an action is taken
def actions(s):
    validmoves = dict(p)
    a = s.lastmove[1]
    validmoves.pop(a, None)
    if a.isupper():
        validmoves.pop(a.lower(), None)
    else:
        validmoves.pop(a.upper(), None)
    q = list(i+j for i in d for j in validmoves)
    return q

# method to display result of action
def result(s,a):
    b = copy.deepcopy(s.board)
#    the following does the same as deepcopy
#    b = list([list(e) for e in s.board])
    if s.turn == 'MIN':
        piece = 'X'
    else:
        piece = 'O'
    if a[0] == 'E':
        b[p[a[1]]] = b[p[a[1]]][1:7] + [piece]
    elif a[0] == 'W':
        b[p[a[1]]] = [piece] + b[p[a[1]]][0:6]
    elif a[0] == 'N':
        for i in range(len(b)-1, 0, -1):
            b[i][p[a[1]]] = b[i-1][p[a[1]]]
        b[0][p[a[1]]] = piece
    elif a[0] == 'S':
        for i in range(len(b)-1):
            b[i][p[a[1]]] = b[i+1][p[a[1]]]
        b[len(b)-1][p[a[1]]] = piece
    newstate = state(a, b, s.nextplayer())
    newstate.v = utility(newstate)
    newstate.parent = s
#    global cu
#    cu += 1
    return newstate

# method for when winner is declared.
def terminaltest(s):
    u = s.v
    if u == 1:
        print 'Player 0 wins'
        quit()
    elif u == -1:
        print 'You win'
        quit()
    else:
        return False

def cutofftest(state, depth):
    u = state.v
    if depth >= max_depth or u == 1 or u == -1:
        return True
    return False

def utility(s):
    #this utility function creates strings of
    #columns, rows and diagonals before using
    #regular expressions to find patterns of
    #X's and O's before applying a multiplier
    #
    #ugly but works
    #should be redone with numpy
    u = 0
    x = 0
    o = 0
    l = []
    # append horz rows to list
    for r in s.board:
        st = ''
        for e in r:
            st += e
        l.append([st])
    # append vert rows
    for j in range(len(s.board)):
        st = ''.join([i[j] for i in s.board])
        l.append([st])
    # append diagonal rows
    for i in range(len(s.board)-1, -1, -1):
        st1 = ''
        st2 = ''
        st3 = ''
        st4 = ''
        k = 0
        for j in range(len(s.board)-i):
            st1 += s.board[i+k][j]
            st2 += s.board[j][i+k]
            st3 += s.board[i+k][-j-1]
            st4 += s.board[j][-i-1-k]
            k += 1
        l.append([st1])
        l.append([st3])
        if i != 0:
            l.append([st2])
            l.append([st4])
    # regex to count patterns of X's and O's
    r = re.findall('X{1}|O{1}', str([i for i in l]))
    x += r.count('X')/3
    o += r.count('O')/3
    r = re.findall('X{2}|O{2}', str([i for i in l]))
    x += r.count('XX')
    o += r.count('OO')
    r = re.findall('X{3}|O{3}', str([i for i in l]))
    x += 3 * r.count('XXX')
    o += 3 * r.count('OOO')
    r = re.findall('X{4}|O{4}', str([i for i in l]))
    if r.count('XXXX'):
        u = -1
        return u
    elif r.count('OOOO'):
        u = 1
        return u
    u = (o-x)/100.0
    return u

# Method for max
def argmax(seq, fn):
    best = seq[0]; best_score = fn(best)
    for x in seq:
        x_score = fn(x)
        if x_score > best_score:
            best, best_score = x, x_score
    return  best

# Method for alpha
def alphabetasearch(state):
    # list of tuples ([actions], [state]) sent to lambda function
    tup = [(i,j) for i, j in zip(actions(state), [result(state,a) for a in actions(state)])]
    ac, st = argmax(tup, lambda ((a,s)): minvalue(s, -float('inf'), float('inf'), 0))
    return ac

# Method for choosing max
def maxvalue(state, alpha, beta, depth):
    if cutofftest(state, depth):
        return state.v
    v = -float('inf')
    for a in actions(state):
        v = max(v, minvalue(result(state, a), alpha, beta, depth+1))
        if v >= beta:
            return v
        alpha = max(alpha, v)
    return v

#method for min
def minvalue(state, alpha, beta, depth):
    if cutofftest(state, depth):
        return state.v
    v = float('inf')
    for a in actions(state):
        v = min(v, maxvalue(result(state,a), alpha, beta, depth+1))
        if v <= alpha:
            return v
        beta = min(beta, v)
    return v

#main method for program
def main():
    s = state()
    inpt = raw_input('Would you like to go first (y/n)?')
    print 'You have not moved yet'
    print 'Player 0 has not moved yet'
    if inpt.upper() == 'N':
        s.turn = 'MAX'
    s.printboard()
    while True:
        if s.turn == 'MIN':
            try:
                a = raw_input('Enter next move: ')
                if a in actions(s) and len(a) < 3:
                    s = result(s, a)
                else:
                    print 'invalid move: try again'
            #white space exception
            except IndexError as e:
                print 'invalid move: try again'
            print 'You played', a

        elif(s.turn == 'MAX'):
            action = alphabetasearch(s)
            s = result(s, action)
            print 'Player 0 moves', action
        s.printboard()
        terminaltest(s)

if __name__ == '__main__':main()
