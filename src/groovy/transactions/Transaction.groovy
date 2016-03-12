package groovy.transactions

import java.lang.invoke.SwitchPoint;

import nonGroovy.entitys.GameObject
import nonGroovy.models.ModelGenerator
import nonGroovy.models.Renderable
import nonGroovy.renderer.Colour

class Transaction implements GameObject{
	
	static final int STARTING_X = 600
	static final int Y_UP = 600
	static final int Y_DOWN = 500
	static final int Y_LEFT = 400
	static final int Y_RIGHT = 300
	static final int WIDTH = 20
	static final int HEIGHT = 20
	static final int MOVE_SPEED = 3
	
	String time, description, tag, accountNumber, sortCode
	TransactionEnums transactionType
	double amount

	int x, y
	int width, height
	static Random r = new Random()
	Colour colour = new Colour(r.nextInt(256),r.nextInt(256),r.nextInt(256))
	Renderable model = ModelGenerator.square()
	
	MoveType[] moveTypes

	Map transactionTypeMap = [
		"CCD": TransactionEnums.CreditCardDeposit,
		"CCW": TransactionEnums.CreditCardWithdrawal,
		"CAD": TransactionEnums.CurrentAccountDeposit,
		"CAW": TransactionEnums.CurrentAccountWithdrawal,
		"SAD": TransactionEnums.SavingsAccountDeposit,
		"D/D": TransactionEnums.DirectDebit,
		"BAC": TransactionEnums.BAC
	]

	Transaction(String[] parts){
		time = parts[1]
		description = parts[2]
		amount = parts[3] as double
		transactionType = transactionTypeMap.get(parts[4])
		tag = parts[5]
		accountNumber = parts[6]
		
		moveTypes = MoveDirection.randomSingleMove()
		
		x = STARTING_X
		
		switch(moveTypes[0]) {
			case MoveType.up:
				y = Y_UP
				break;
			case MoveType.down:
				y = Y_DOWN
				break;
			case MoveType.left:
				y = Y_LEFT
				break;
			case MoveType.right:
				y = Y_RIGHT
				break;
		}
		
		this.width = WIDTH
		this.height = HEIGHT
		
	}

	double calculateAmountEffect(int hitAccuracy) {
		if(amount >= 0) {
			return amount * hitAccuracy / 100
		} else if(amount < 0) {
			return amount + ((HitAccuracy.PERFECT - hitAccuracy) * amount)/100
		}
	}

	@Override
	public void update() {
		x -= MOVE_SPEED;
	}
}
