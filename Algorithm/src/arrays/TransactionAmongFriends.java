package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//panda paid 2000 to maid
//sameer paid 4000 on grocery
//akshat paid 1500 as internet bill
//pari paid 1800 on electricity
public class TransactionAmongFriends {

	public static void main(String[] args) {
		List<MoneyPaid> payments = new ArrayList<>();
		payments.add(new MoneyPaid("panda", 2000));
		payments.add(new MoneyPaid("sameer", 4000));
		payments.add(new MoneyPaid("akshat", 1500));
		payments.add(new MoneyPaid("pari", 2500));
		List<MoneyToPay> transfers =transferMoney(payments);
		System.out.println(transfers);
	}
	
	private static List<MoneyToPay> transferMoney(List<MoneyPaid> payments){
		Map<String ,Integer> personToAmountPaid = new HashMap<>();
		int totalPaid = 0;
		for(MoneyPaid payment:payments) {
			totalPaid += payment.getAmount();
			if(personToAmountPaid.get(payment.getBy()) == null) {
				personToAmountPaid.put(payment.getBy(),payment.getAmount());
			}
			else {
				personToAmountPaid.put(payment.getBy(),personToAmountPaid.get(payment.getBy()) + payment.getAmount());
			}
		}
		int everyOnesShare = totalPaid / personToAmountPaid.keySet().size();

		
		List<String> people = personToAmountPaid.keySet().stream().collect(Collectors.toList());
		
		//positive mean the person would get and negative means the person will give
		Map<String ,Integer> personToAmountToGet = new HashMap<>();
		for(String person:people) {
			personToAmountToGet.put(person, personToAmountPaid.get(person) - everyOnesShare);
		}
		
		System.out.println(personToAmountToGet);
		List<Entry<String,Integer>> sortedByAmountToGet = personToAmountToGet.entrySet().stream().collect(Collectors.toList());
		Collections.sort(sortedByAmountToGet,(entryForPersonOne,entryForPersonTwo) -> entryForPersonOne.getValue().compareTo(entryForPersonTwo.getValue()));
		int personWhoWillPayIndex = 0;
		int personWhoWillGetIndex = sortedByAmountToGet.size() -1;
		List<MoneyToPay> pendingTransfers = new ArrayList<>();
		while(personWhoWillPayIndex < personWhoWillGetIndex) {
			
			String personWhoWillPay = sortedByAmountToGet.get(personWhoWillPayIndex).getKey();
			int amountToPay = sortedByAmountToGet.get(personWhoWillPayIndex).getValue();
			if(amountToPay == 0) {
				++personWhoWillPayIndex;
				continue;
			}
			String personWhoWillGet = sortedByAmountToGet.get(personWhoWillGetIndex).getKey();
			int expectingAmount = sortedByAmountToGet.get(personWhoWillGetIndex).getValue();
			if(expectingAmount == 0) {
				--personWhoWillGetIndex;
				continue;
			}
			
			if(amountToPay < 0 && expectingAmount > 0) {
				if(Math.abs(amountToPay) <= expectingAmount) {
					sortedByAmountToGet.get(personWhoWillPayIndex).setValue(0);
					sortedByAmountToGet.get(personWhoWillGetIndex).setValue(expectingAmount - Math.abs(amountToPay));
					System.out.println(String.format("%d transferred from %s to %s", Math.abs(amountToPay),personWhoWillPay,personWhoWillGet));
					pendingTransfers.add(new MoneyToPay(personWhoWillPay, personWhoWillGet, Math.abs(amountToPay)));
					++personWhoWillPayIndex;
				}
				else {
					sortedByAmountToGet.get(personWhoWillPayIndex).setValue(-(Math.abs(amountToPay) - expectingAmount));
					sortedByAmountToGet.get(personWhoWillGetIndex).setValue(0);
					System.out.println(String.format("%d transferred from %s to %s", Math.abs(amountToPay) - expectingAmount,personWhoWillPay,personWhoWillGet));
					pendingTransfers.add(new MoneyToPay(personWhoWillPay, personWhoWillGet, Math.abs(amountToPay) - expectingAmount));
					--personWhoWillGetIndex;
				}
			}
		}
		System.out.println(sortedByAmountToGet);
		return pendingTransfers;
		
	}
	
	
}

class MoneyPaid{
	private String by;
	private int amount;
	
	public MoneyPaid(String by, int amount) {
		super();
		this.by = by;
		this.amount = amount;
	}

	public String getBy() {
		return by;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "MoneyPaid [by=" + by + ", amount=" + amount + "]";
	}
	
	
	
}

class MoneyToPay{
	private String from,to;
	private int amount;
	
	public MoneyToPay(String from, String to, int amount) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "MoneyToPay [from=" + from + ", to=" + to + ", amount=" + amount + "]";
	}
	
	
}