package groovy

import groovy.transactions.Transaction
import groovy.transactions.Transactions

class Main {
	static void main(String[] args) {
		File file = new File("res/nationwideData/customer688.csv")
		
		Transactions transactions = new Transactions(file)
			
		double total = 0
		
		Random random = new Random()
		
		transactions.each { Transaction transaction ->
//			total += transaction.amount
			println transaction.moveTypes
			total += transaction.calculateAmountEffect(random.nextInt(101))
		}
		println transactions.size()
		println total
	}
	
}