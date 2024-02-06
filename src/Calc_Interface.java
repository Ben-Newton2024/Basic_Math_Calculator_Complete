import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Calc_Interface {

    public void main_calc_interface(){

        Basic_Math_Func Basic_Math_Functions = new Basic_Math_Func();

        Scanner in = new Scanner(System.in);
        // read console input line
        String input_equation = in.nextLine();


        // sort the string into separate components of operators and digits
        List<String> equation_list = equation_sorting(input_equation);

        // now complete the math onto this list above

        for (int i = 0; i <= equation_list.size()-1; i++){





            if( !Character.isDigit(Integer.parseInt(equation_list.get(i)))){
                // if the item in position "i" is found to not be a digit when made into a integer, then it must be an
                // operator.
                // if it is an operator then we can take the digits from either side of it and use the operator to do
                // what math needs to be done on it then

                if (Objects.equals(equation_list.get(i), "+")){
                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(i-1));
                    int b = Integer.parseInt(equation_list.get(i+1));
                    int c = Basic_Math_Functions.basic_int_addition(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(i, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(i-1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(i);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    i = 0;
                }
            }


        }
    }

    public List<String> equation_sorting(String input_equation){
        // need to check to see if the inputted equation has the correct syntax, e.g, not end in a + or -, make sure there
        // are enough ( to match the amount of )


        // create string to add parts of the equation to it to complete the calculation all in on go
        List<String> equation_list = new ArrayList<>();

        // create list ot add together current numbers
        StringBuilder current_number = new StringBuilder();


        // run through length of the equation character bt character
        for (int i = 0; i < input_equation.length(); i++){
            if(Character.isDigit(input_equation.charAt(i))){
                // if character is a digit then we add it to the current list of digits
                current_number.append(input_equation.charAt(i));
            } else{
                // add the current number to the equation liat
                equation_list.add(current_number.toString());

                // since the latest number is now added ot the list of equations, and the newest point of I is no longer
                // pointing to a digit then we need to add the operator to the list of equations.
                equation_list.add(String.valueOf(input_equation.charAt(i)));

                // reset current number to go back to "" as we don't want to keep adding current number
                current_number = new StringBuilder();

            }
        }
        // end of list has been achieved as such add any remain digits or operators.
        System.out.println("End of string has been found");
        if (!current_number.isEmpty()) {
            equation_list.add(current_number.toString());
        } else {
            equation_list.add(String.valueOf((input_equation.charAt(input_equation.length()-1))));
        }
        System.out.println(equation_list);
        // at the end of this method there should just be a list with all digits and operators appened to it in the
        // correct order as needed to complete basic maths

        return equation_list;
    }
}
