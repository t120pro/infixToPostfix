
public class InfixToPostfix {

	private static String[] myStack = new String[20];
	private static int myStackPointer = 1;

	public static boolean myStackPush(String push) { //this is the push method
		if (myStackPointer == myStack.length - 1) {
			return false;
		}
		myStack[myStackPointer] = push;
		myStackPointer++;
		return true;
	}

	public static String myStackPop() { //this is the pop method
		myStackPointer--;
		String popped = myStack[myStackPointer];
		return popped;
	}

	public static int priority(String operator) { //this method priotitizs the operators in the stack
		if (operator == null) {
			return 0;
		} else if (operator.equals("+") || operator.equals("-")) {
			return 1;
		} else if (operator.equals("*") || operator.equals("/")) {
			return 2;
		} else if (operator.equals("^")) {
			return 3;
		}
		return 0;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a mathimatical equation.");
		String inputEq = scanner.nextLine();
		String outputEq = "";

		for (int i = 0; i < inputEq.length(); i++) { // operator
			String topInput = "" + inputEq.charAt(i);
			if (topInput.equals("+") || topInput.equals("-") || topInput.equals("*") || topInput.equals("/")
					|| topInput.equals("^")) {
				while (priority(topInput) <= priority(myStack[myStackPointer - 1])) {
					outputEq += (myStackPop());
				}
				myStackPush(topInput);
			}
			
			else if (topInput.equals("(")) {  //front parentheses
				myStackPush(topInput);
			}
			
			else if (topInput.equals(")")) { //back parentheses
				boolean cont = true;
				while (cont == true) {
					if (myStack[myStackPointer - 1].equals("(")) {
						myStackPop();
						cont = false;
					} else {
						outputEq += (myStackPop());
					}
				}
				
			} else { // number or letter or space
				outputEq += topInput;
			}
		}
		for (int j = myStackPointer; j > 1; j--) {
			outputEq += (myStackPop());
		}
		System.out.println(outputEq);
	}
}
