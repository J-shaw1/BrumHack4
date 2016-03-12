package groovy.transactions;

enum MoveType {
	up, down, left, right
}

class MoveDirection {
	
	static Random r = new Random()
	static List allDirections = [MoveType.up, MoveType.down, MoveType.left, MoveType.right]
	
	static MoveType[] randomSingleMove(){
		return randomNumberOfMoves(1)
	}
	
	static MoveType[] randomDoubleMove(){
		return randomNumberOfMoves(2)
	}
	
	static MoveType[] randomTripleMove(){
		return randomNumberOfMoves(3)
	}
	
	static MoveType[] randomQuadMove(){
		return allDirections
	}
	
	static MoveType[] randomNumberOfMoves(int numberOfMoves){ 
		Collections.shuffle(allDirections, r)
		return allDirections.take(numberOfMoves)
	}
	
}
