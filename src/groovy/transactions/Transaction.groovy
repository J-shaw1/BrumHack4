package groovy.transactions

class Transaction {
	String time, description, tag, accountNumber, sortCode
	TransactionEnums transactionType
	double amount
	
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
	}
	
}
