package groovy.transactions

class Transaction {
	String time, description, type, tag, accountNumber, sortCode
	double amount
	
	Transaction(String[] parts){
		println parts
		time = parts[1]
		description = parts[2]
		amount = parts[3] as double
		type = parts[4]
		tag = parts[5]
		accountNumber = parts[6]
	}
	
}
