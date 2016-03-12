package groovy.transactions

import nonGroovy.entitys.GameObject
import nonGroovy.models.Renderable
import nonGroovy.renderer.Colour

class Transaction implements GameObject{
	
	static final int StartingX = 600
	static final int moveSpeed = 3
	
	String time, description, tag, accountNumber, sortCode
	TransactionEnums transactionType
	double amount

	int x, y
	int width, height
	Colour colour
	Renderable model;
	
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
		
		x = startingX
		
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
		x -= moveSpeed;
	}
}
