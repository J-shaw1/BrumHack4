package groovy.transactions;

import java.io.File;
import java.util.List;

class Transactions extends ArrayList<Transaction>{
	
	Transactions(File file){
		List transactions = new ArrayList()
		file.eachLine { String line, int lineNumber ->
			
			if(lineNumber == 1) return;
			
			line  = line.replaceAll("\"","")
			String[] parts = line.split(",")
			
			Transaction transaction = new Transaction(parts)
			
			if(["Credit Repayment", "-"].every { it != transaction.tag }) {
				this.add(transaction)
			}
			
		}
	}

}