package groovy.transactions;

enum MoveType {
	one, two, three, four
}

class MoveDirection {
	
	static Random r = new Random()
	static List allDirections = [MoveType.one, MoveType.two, MoveType.three, MoveType.four]
	
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
