class Board:
    def __init__(self):
        self.board = ["...", "...", "..."]
    def add(self, player_piece, x, y):
        if player_piece != "x" and player_piece != "o":
            raise RuntimeError("Piece must be x or o")
        row = ""
        for i in range(3):
            if i == y:
                row += player_piece
            else:
                row += self.board[x][i]
        self.board[x] = row
    def draw(self):
        for i in range(3):
            print(*self.board[i])

class SubBoard (Board):
	def movePiece(self, x1, y1, x2, y2):
		initialData = self.board[x1][y1]
		initialRow = [None]*3
		finalRow = [None]*3
		
		for j in range(3):
			if x1 != x2:
				print("HERE")
				if y1 == j:
					initialRow[j] = "."
				else:
					initialRow[j] = self.board[x1][j]
				if y2 == j:
					finalRow[j] = initialData
				else:
					finalRow[j] = self.board[x2][j]
			else:
				if y1 == j:
					finalRow[j] = "."
				elif y2 == j:
					finalRow[j] = initialData
				else:
					finalRow[j] = self.board[x2][j]
		self.board[x1] = initialRow
		self.board[x2] = finalRow
				
b = SubBoard()
print("\n")
b.add("x", 1, 1)
b.draw()
print("\n")
b.add("o", 0, 1)
b.draw()        
print("\n")                                                              
b.movePiece(1, 1, 1, 2)
print("\n")
b.draw()
#print("\n")
#b.movePiece(0, 1, 2, 2)
#b.draw()