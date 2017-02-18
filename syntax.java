/*
ALGORITHM : Infix to Prefix


STEP 1 : Read the given infix expression into string called infix.

STEP 2 : Reverse the infix string and read one character at a time and perform the following operations :

If the read character is an operand, then add the operand to the prefix string.
If the read character is not an operand, then check
If the stack is not empty and precedence of the top of the 
stack operator is higher than the read operator,
then pop the operator from stack and add this 
operator to the prefix string. 
Else push the operator onto the stack.

STEP 3 : Repeat STEP 2 till all characters are processed from the input string.

STEP 4 : If stack is not empty, then pop the operator from stack and add this operator to the prefix string.

STEP 5 : Repeat STEP 4 till all the operators are popped from the stack.

STEP 6 : Reverse the prefix string and display the result of the given infix expression or the resultant prefix expression stored in a string called prefix from this algorithm.

*/

import java.util.Stack;

public class InfixToPrefix {

static Stack inputStack;

static String output = "";

public static void main(String[] args) {
String input = "1+2*4/5-7+3/6";
int len = input.length();
char[] charr = new char[len];
for (int i = 0; i < len; i++) {
charr[i] = input.charAt(i);
}

String reverseInput = reverse(input);

System.out.println(infixToPrefix(reverseInput));

}

public static String infixToPrefix(String input) {

inputStack = new Stack();

for (int i = 0; i < input.length(); i++) {
char current = input.charAt(i);
if (current == '+' || current == '-') {
isOperator(current, 1);
} else if (current == '*' || current == '/') {
isOperator(current, 2);
} else {
output += current;
}
}
while (!inputStack.isEmpty()) {
char top = (Character) inputStack.pop();
output += top;
}
output = reverse(output);
return output;
}

public static void isOperator(char c, int prec) {
while (!inputStack.isEmpty()) {
char top = (Character) inputStack.pop();
int topPrec = 0;
if (top == '+' || top == '-') {
topPrec = 1;
} else {
topPrec = 2;
}

if (topPrec >= prec) {
output += top;
} else {
inputStack.push(top);
break;
}

}
inputStack.push(c);

}

public static String reverse(String input) {
int len = input.length();
String reverse = "";
char[] charr = new char[len];
for (int i = 0; i < len; i++) {
charr[i] = input.charAt(i);
}
for (int i = 0; i < len / 2; i++) {
char temp = charr[i];
charr[i] = charr[len - i - 1];
charr[len - i - 1] = temp;
}
for (int j = 0; j < len; j++) {
reverse += charr[j];
}
return reverse;
}
} 

