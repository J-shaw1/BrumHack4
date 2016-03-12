package groovy.transactions

import java.lang.invoke.SwitchPoint;

import nonGroovy.entitys.GameObject
import nonGroovy.models.ModelGenerator
import nonGroovy.models.Renderable
import nonGroovy.renderer.Colour

class Transaction implements GameObject{
		
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
	
	Map yMap = [
		(MoveType.up): TransactionConstants.Y_UP,
		(MoveType.down): TransactionConstants.Y_DOWN,
		(MoveType.left): TransactionConstants.Y_LEFT,
		(MoveType.right): TransactionConstants.Y_RIGHT
	]

	Transaction(String[] parts){
		time = parts[1]
		description = parts[2]
		amount = parts[3] as double
		transactionType = transactionTypeMap.get(parts[4])
		tag = parts[5]
		accountNumber = parts[6]
		
		moveTypes = MoveDirection.randomSingleMove()
		
		x = TransactionConstants.STARTING_X
		
		y = yMap.get(moveTypes[0])
		
		this.width = TransactionConstants.WIDTH
		this.height = TransactionConstants.HEIGHT
		
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
		x -= TransactionConstants.MOVE_SPEED
	}
}
