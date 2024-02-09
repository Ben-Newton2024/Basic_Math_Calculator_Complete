import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Calc_Interface {

    public void main_calc_interface() {

        Basic_Math_Func Basic_Math_Functions = new Basic_Math_Func();

        Scanner in = new Scanner(System.in);
        // read console input line
        String input_equation = in.nextLine();

        // check the syntax of equation given to check that it doesn't end in a + or a * e.g 4+2+ or 9/()89

        // TODO need to check for any unknown variables, like for algebra, any a,b,c etc.
        int bracket_counter = getBracket_counter(input_equation);

        String s = String.valueOf(input_equation.charAt(input_equation.length()-1));
        if (s.equals("+") ||
                s.equals("-") ||
                s.equals("/") ||
                s.equals("*")) {
            // if the end of the equation is an operator, throw error equation cannot be computed
            System.out.println("This equation cannot be computed. 0");
        } else // if bracket count is not even then we have problem.
            if (!((bracket_counter%2) == 0)) {
                System.out.println("This equation cannot be computed. 1");
            } else {
                //brackets doo match as well, check for correct number of each
                // need to check the amount of open brackets matches closed brackets.
                int open_brackets = 0;
                int closed_brackets = 0;
                for(int j = 0; j<input_equation.length(); j++){
                    if (String.valueOf(input_equation.charAt(j)).equals("(")){
                        open_brackets++;
                    } else if (String.valueOf(input_equation.charAt(j)).equals(")")) {
                        closed_brackets ++;
                    }
                }
                System.out.println("Number of open: "+ open_brackets + "   Numnber of closed:" + closed_brackets);
                if (open_brackets != closed_brackets){
                    // if number not even then brackets do not match and error.
                    System.out.println("Error, this equation cannot be computed brackets dont match");
                }else {
                    // if no problem with equation, compute and complete it.
                    compute_equation(input_equation, Basic_Math_Functions);
                }
        }
    }

    private static int getBracket_counter(String input_equation) {
        int bracket_counter = 0;
        for (int i = 0; i <= input_equation.length()-1; i++) {
            //if there are brackets, count for even number of them to check correct amount.
            //this does not however check to see if there are correct type of each when divided by 2
            //this only shows there's enough in the system.
            // however this is pretty pointless anyway atm as this program cannot use brackets yet.
            if (String.valueOf(input_equation.charAt(i)).equals("(") || String.valueOf(input_equation.charAt(i)).equals(")"))
                bracket_counter++;

        }
        return bracket_counter;
    }

    private void compute_equation(String input_equation, Basic_Math_Func Basic_Math_Functions){

        // sort the string into separate components of operators and digits
        List<String> equation_list = equation_sorting(input_equation);

        // now complete the math onto this list above

        // loop tracking number
        int loop_tracker = 0;

        for (int i = 0; i <= equation_list.size() - 1; i++) {
            loop_tracker ++;

            try {
                // tries to convert item in position "i" to an integer, if it cannot then it must be an operator, as such
                // the catch it caught and pushed through to the needed code to complete the maths equation
                Integer.parseInt(equation_list.get(i));

                // if current item in list position can be converted to a digit, nothing is really needed to happen, as
                // such rest of code happens in catch.
                // it is probably better to ensure all code happens in the try, rather than the catch, however -
                // im working getting it to work before fixing syntax and "perfect" coding currently.

                // could cal on system garbage collector to clean up on digit be referencing it as null, but no point as
                // it is already well optimised and will probably catch it later on anyway
            } catch (NumberFormatException ex) {
                // if the item in position "i" is found to not be a digit when made into an integer, then it must be an
                // operator.
                // if it is an operator then we can take the digits from either side of it and use the operator to do
                // what math needs to be done on it then

                if (Objects.equals(equation_list.get(i), "+")) {
                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(i - 1));
                    int b = Integer.parseInt(equation_list.get(i + 1));
                    int c = Basic_Math_Functions.basic_int_addition(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(i, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(i - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(i);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    i = 0;
                } else if (Objects.equals(equation_list.get(i), "-")) {
                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(i - 1));
                    int b = Integer.parseInt(equation_list.get(i + 1));
                    int c = Basic_Math_Functions.basic_int_subtraction(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(i, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(i - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(i);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    i = 0;
                } else if (Objects.equals(equation_list.get(i), "*")) {
                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(i - 1));
                    int b = Integer.parseInt(equation_list.get(i + 1));
                    int c = Basic_Math_Functions.basic_int_multiplication(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(i, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(i - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(i);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    i = 0;
                }else if (Objects.equals(equation_list.get(i), "/")) {
                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(i - 1));
                    int b = Integer.parseInt(equation_list.get(i + 1));
                    int c = Basic_Math_Functions.basic_int_division(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(i, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(i - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(i);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    i = 0;
                }
            }
            System.out.println(equation_list + " Loop: #" + loop_tracker);
        }
        System.out.println(equation_list + " Answer");
    }

    private List<String> equation_sorting(String input_equation){
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
        // at the end of this method there should just be a list with all digits and operators append to it in the
        // correct order as needed to complete basic maths

        return equation_list;
    }
}
