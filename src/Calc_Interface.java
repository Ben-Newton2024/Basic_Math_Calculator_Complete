import java.util.*;

public class Calc_Interface {

    private final Basic_Math_Func Basic_Math_Functions = new Basic_Math_Func();
    private boolean is_brackets = false;



    /*
                LOGIC FOR INPUT AND ERROR CHECKING

                           Below.

     */
    public void main_calc_interface() {


        Scanner in = new Scanner(System.in);
        // read console input line
        String input_equation = in.nextLine();

        // check the syntax of equation given to check that it doesn't end in a + or a * e.g 4+2+ or 9/()89

        // check for any unknown variables, like for algebra, any a,b,c etc.
        int bracket_counter = getBracket_counter(input_equation);
        // if bracket counter > 0 then there are brackets to change boolean
        // is_brackets to true for later use in calculator
        if (bracket_counter > 0) {
            is_brackets = true;
        }

        String s = String.valueOf(input_equation.charAt(input_equation.length() - 1));
        if (s.equals("+") ||
                s.equals("-") ||
                s.equals("/") ||
                s.equals("*")) {
            // if the end of the equation is an operator, throw error equation cannot be computed
            System.out.println("This equation cannot be computed. 1");
        }

        String str_char_not_used = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,<>?|@';:{[}]#~_&$£!";

        for (int j = 0; j < input_equation.length(); j++) {
            for (int i = 0; i < str_char_not_used.length(); i++) {
                //loops through both strings comparing each character @ i in the list together.
                if (String.valueOf(input_equation.charAt(j)).equals(String.valueOf(str_char_not_used.charAt(i)))) {
                    System.out.println("This equation cannot be computed 0");
                    //need to exit loops
                    i = input_equation.length();
                    j = input_equation.length();

                }
            }
            if (!((bracket_counter % 2) == 0)) {
                System.out.println("This equation cannot be computed. 2");
                // send to max value of J to exit loop.
                j = input_equation.length();
            } else {
                //brackets doo match as well, check for correct number of each
                // need to check the amount of open brackets matches closed brackets.

                int open_brackets = 0;
                int closed_brackets = 0;
                for (int k = 0; k < input_equation.length(); k++) {
                    if (String.valueOf(input_equation.charAt(k)).equals("(")) {
                        open_brackets++;
                    } else if (String.valueOf(input_equation.charAt(k)).equals(")")) {
                        closed_brackets++;
                    }
                }
                if (open_brackets != closed_brackets) {
                    // if number not even then brackets do not match and error.
                    System.out.println("This equation cannot be computed. 3");
                    // max loop int to end loops.
                    j = input_equation.length();

                } else {
                    // if no problem with equation, compute and complete it.

                    //need to ensure that it previous for loops end as the conditions have been met
                    j = input_equation.length();
                    if (is_brackets) {
                        compute_bracket_equation(input_equation, open_brackets, closed_brackets);
                    } else {
                        compute_equation(input_equation);
                    }
                }
            }

        }
    }

    /*
                LOGIC FOR FOLLOWING BIDMAS WITH GIVEN LIST OF EQUATIONS

                                    Below.
     */

    private List<String> compute_equation(String input_equation) {

        // sort the string into separate components of operators and digits
        List<String> equation_list = equation_sorting(input_equation);

        /*
         this equation list, could have brackets sent to it and be a smaller part of the given equation,
         but still follow bidmas anyway.
         now complete the math onto this list above

         Follow BIDMAS

         This however cannot be the most efficient version of this, as this assumes there is going to be
         one of each operator within the calculation
         as such it will go through the list looping multiple times, till the equation is sorted
         some checks could be added in the future, showing what operators are involved with the equation,
         to only need to loop through the list the minimum amount of times. rather doing the max
         amount of times per equation which will waste 'time' and space.
         This could be done via if statements and boolean values that loops the entire equation once,
         that changes values from false to true if an operator is detected.
         as such later here, the ifs can catch if an only the '+' operator is detected as such skip any unneeded loops
         from the indices and decision etc., to only need to loop the once at the addition.
        */


        equation_list = calc_indices(equation_list);
        equation_list = calc_division_and_multiplication(equation_list);
        equation_list = calc_addition_and_subtraction(equation_list);
        System.out.println(equation_list);
        return equation_list;
    }


    /*
                LOGIC FOR CALCULATIONS

                        Below.
     */

    private List<String> calc_indices(List<String> equation_list) {
        for (int x = 0; x <= equation_list.size() - 1; x++) {
            try {
                // tries to convert item in position "i" to an integer,
                // if it cannot then it must be an operator, as such
                // the catch it caught and pushed through to the needed code to complete the maths equation
                Integer.parseInt(equation_list.get(1));

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
                if (Objects.equals(equation_list.get(x), "^")) {
                    // If there is this, we need to ensure this is done before anything else,
                    // even moving from left ot right.
                    // if brackets include this and extra than this needs to be done first.
                    // while looping through the list it goes left to right, so if an indices is here, it won't be don't
                    // till other calculations are done before from left to right.
                    // Could loop through the given equation till it is found, while using the is_indices boolean check
                    // to activate the code, else it would be inefficient to constantly loop back through it
                    // every time this method as called.

                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(x - 1));
                    int b = Integer.parseInt(equation_list.get(x + 1));
                    int c = Basic_Math_Functions.basic_indices(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(x, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(x - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(x);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    x = 0;
                }
            }
        }
        return equation_list;
    }




    private List<String> calc_division_and_multiplication(List<String> equation_list) {
        for (int x = 0; x <= equation_list.size() - 1; x++) {
            try {
                // tries to convert item in position "i" to an integer,
                // if it cannot then it must be an operator, as such
                // the catch it caught and pushed through to the needed code to complete the maths equation
                Integer.parseInt(equation_list.get(1));

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
                if (Objects.equals(equation_list.get(x), "/")) {
                    // If there is this, we need to ensure this is done before anything else,
                    // even moving from left ot right.
                    // if brackets include this and extra than this needs to be done first.
                    // while looping through the list it goes left to right, so if an indices is here, it won't be don't
                    // till other calculations are done before from left to right.
                    // Could loop through the given equation till it is found, while using the is_indices boolean check
                    // to activate the code, else it would be inefficient to constantly loop back through it
                    // every time this method as called.

                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(x - 1));
                    int b = Integer.parseInt(equation_list.get(x + 1));
                    int c = Basic_Math_Functions.basic_int_division(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(x, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(x - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(x);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    x = 0;
                } else if (Objects.equals(equation_list.get(x), "*")) {
                    // If there is this, we need to ensure this is done before anything else,
                    // even moving from left ot right.
                    // if brackets include this and extra than this needs to be done first.
                    // while looping through the list it goes left to right, so if an indices is here, it won't be don't
                    // till other calculations are done before from left to right.
                    // Could loop through the given equation till it is found, while using the is_indices boolean check
                    // to activate the code, else it would be inefficient to constantly loop back through it
                    // every time this method as called.

                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(x - 1));
                    int b = Integer.parseInt(equation_list.get(x + 1));
                    int c = Basic_Math_Functions.basic_int_multiplication(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(x, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(x - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(x);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    x = 0;
                }
            }
        }
        return equation_list;
    }

    private List<String> calc_addition_and_subtraction(List<String> equation_list) {
        for (int x = 0; x <= equation_list.size() - 1; x++) {
            try {
                // tries to convert item in position "i" to an integer,
                // if it cannot then it must be an operator, as such
                // the catch it caught and pushed through to the needed code to complete the maths equation
                Integer.parseInt(equation_list.get(1));

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
                if (Objects.equals(equation_list.get(x), "+")) {
                    // If there is this, we need to ensure this is done before anything else,
                    // even moving from left ot right.
                    // if brackets include this and extra than this needs to be done first.
                    // while looping through the list it goes left to right, so if an indices is here, it won't be don't
                    // till other calculations are done before from left to right.
                    // Could loop through the given equation till it is found, while using the is_indices boolean check
                    // to activate the code, else it would be inefficient to constantly loop back through it
                    // every time this method as called.

                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(x - 1));
                    int b = Integer.parseInt(equation_list.get(x + 1));
                    int c = Basic_Math_Functions.basic_int_addition(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(x, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(x - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(x);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    x = 0;
                } else if (Objects.equals(equation_list.get(x), "-")) {
                    // If there is this, we need to ensure this is done before anything else,
                    // even moving from left ot right.
                    // if brackets include this and extra than this needs to be done first.
                    // while looping through the list it goes left to right, so if an indices is here, it won't be don't
                    // till other calculations are done before from left to right.
                    // Could loop through the given equation till it is found, while using the is_indices boolean check
                    // to activate the code, else it would be inefficient to constantly loop back through it
                    // every time this method as called.

                    // add the digits from either side of the equations list together.
                    int a = Integer.parseInt(equation_list.get(x - 1));
                    int b = Integer.parseInt(equation_list.get(x + 1));
                    int c = Basic_Math_Functions.basic_int_subtraction(a, b);

                    // answer c is found and can be added to the list, this can be done by removing the current operator
                    // and the two digits on either side of it, shortening the list.

                    // replacing the operator with the answer
                    equation_list.set(x, String.valueOf(c));

                    // now removing the digits on either side of that "operator" now answer digit
                    equation_list.remove(x - 1);
                    //removed position before i, therefore list is now 1 position smaller
                    equation_list.remove(x);

                    //reset "i" to 0 as we have edited the list and need to ensure that it goes back through the entire
                    // list correctly with no blank positions or reset positions that duplicate.
                    x = 0;
                }
            }
        }
        return equation_list;
    }



    /*
            LOGIC FOR BRACKETS

                Below.
     */

    private static int getBracket_counter(String input_equation) {
        int bracket_counter = 0;
        for (int i = 0; i <= input_equation.length() - 1; i++) {
            //if there are brackets, count for even number of them to check correct amount.
            //this does not however check to see if there are correct type of each when divided by 2
            //this only shows there's enough in the system.
            // however this is pretty pointless anyway atm as this program cannot use brackets yet.
            if (String.valueOf(input_equation.charAt(i)).equals("(")
                    || String.valueOf(input_equation.charAt(i)).equals(")"))
                bracket_counter++;
        }
        return bracket_counter;
    }

    private void compute_bracket_equation(String input_equation,
                                          int open_brackets, int closed_brackets) {
        /*
         because there are brackets, we need to ensure that whatever is in the brackets is computer first.
         we want to loop from the innermost brackets first and work outward, but also work from left to right
         as such the equation

         2 + ( 2 - 3 ) + ( 3 - ( 1 * 4 ) )
         2 + ( -1 ) ...
         1 + ( 3 - ( 1 * 4 ) )
         1 + ( 3 - ( 4 ) )
         1 + ( 3 - 4 )
         1 + ( -1 )
         0

         find the first open and closed parenthesis, and do the computation in them
         the computation, could be just sent of to the basic compute_equation.
         replace, all section in the list with the output from above statement.
         repeat entire section again.
         keep repeating, till all brackets are finished
         include check to keep checking if there are any brackets left
         if answer is still an equation then pass to basic compute equation
         else output answer
        */

        int found_open_brackets = 0;
        int found_closed_brackets = 0;


        for (int i = 0; i < input_equation.length(); i++) {
            // loops through both strings comparing each character @ i in the list together.
            if (String.valueOf(input_equation.charAt(i)).equals("(")) {
                // found open parenthesis.
                // now need to find closed.
                // new loop from I, to end of parenthesis.
                // if looping from same position an '(' is found, it will continue looping saying it has been found
                // as such add 1 to position 'i'
                for (int j = i+1; j < input_equation.length(); j++) {
                    // loops through both strings comparing each character @ i in the list together.
                    if (String.valueOf(input_equation.charAt(j)).equals("(")) {
                        // if another open parenthesis is found before the last is closed,
                        // then we need to compute the inside parenthesis.
                        j = input_equation.length() + 1;
                    } else if (String.valueOf(input_equation.charAt(j)).equals(")")) {
                        //once found both open and closed, we can increase counter for them
                        found_open_brackets ++;
                        found_closed_brackets ++;

                        // if open and close brackets are found then compute them.
                        // we need to loop from the 'i' to the 'j' appending what is inside the () to a new string
                        // check to see if the brackets have anything inside of them
                        if (i-j >= 0){
                            // if difference between numbers within brackets is >0 as im taking away
                            // the bigger number than there must be something in the brackets
                            // if that isn't the case then we do this.
                            System.out.println("This Equation cannot be computed. 4");
                            // reset loop back to make another pass since the () have not been
                            // removed as nothing was in them
                            i=input_equation.length();
                            j=input_equation.length();
                        } else{
                            // else there are numbers inside the brackets as such can compute.
                            StringBuilder bracket_equation = getStringBuilder(input_equation, i, j);
                            List<String> return_list = compute_equation(bracket_equation.toString());
                            // once answer given back, we can remove the brackets.
                            return_list.removeLast();
                            return_list.removeFirst();
                            // as we only passed the () equation it is safe to remove first and last position of the list.
                            // we can now convert to a string again, and pass back into the position we took it out of.
                            // then we need to restart the loop to check for more ().
                            StringBuilder answer_string = getAnswer_string(return_list);
                            // now we have list converted to string, we can split the original equation.

                            // we know that the () start and end on 'i' and 'j', so we need to replace
                            // all string characters with "".
                            input_equation = input_equation.substring(0, i) + answer_string +
                                    input_equation.substring(j+1);

                            //need to reset string
                            //reset 'i' and 'j' back to 0 to go through the loop again, till there are no longer any ()
                            if (found_closed_brackets == closed_brackets && found_open_brackets == open_brackets){
                                // all brackets done, then if anything else not computed can be computed
                                compute_equation(input_equation);

                            }
                            else {
                                // if the bracket count isn't equal then there are more brackets in the equation.
                                i = 0;
                                j = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    /*
            LOGIC FOR STRING MANIPULATIONS

                        Below.
     */

    private static StringBuilder getStringBuilder(String input_equation, int i, int j) {
        StringBuilder bracket_equation = new StringBuilder();

        for (int k = i; k <= j; k++) {
            bracket_equation.append(input_equation.charAt(k));
            // once small bracket equation found we can compute this
            // and when all is appended to the string after the complete loop
        }
        return bracket_equation;
    }

    private static StringBuilder getAnswer_string(List<String> return_list) {
        StringBuilder answer_string = new StringBuilder();

        for (int p = 0; p <= return_list.size()-1; p++){
            answer_string.append(return_list.get(p));
        }
        return answer_string;
    }


    /*
                LOGIC FOR MOVING STRING TO LIST

                            Below.
     */

    private List<String> equation_sorting(String input_equation){
        // need to check to see if the inputted equation has the correct syntax, e.g, not end in a + or -,
        // make sure there are enough ( to match the amount of )


        // create string to add parts of the equation to it to complete the calculation all in on go
        List<String> equation_list = new ArrayList<>();

        // create string to add strings together to complete a number when adding to new list above.
        StringBuilder current_number = new StringBuilder();


        // run through length of the equation character bt character
        for (int i = 0; i < input_equation.length(); i++){
            if(Character.isDigit(input_equation.charAt(i))){
                // if character is a digit then we add it to the current list of digits
                current_number.append(input_equation.charAt(i));
            } else{
                // add the current number to the equation liat
                if(!current_number.isEmpty()) {
                    equation_list.add(current_number.toString());
                }

                // since the latest number is now added ot the list of equations, and the newest point of I is no longer
                // pointing to a digit then we need to add the operator to the list of equations.
                equation_list.add(String.valueOf(input_equation.charAt(i)));

                // reset current number to go back to "" as we don't want to keep adding current number
                current_number = new StringBuilder();

            }
        }
        // end of list has been achieved as such add any remain digits or operators.
        if (!current_number.isEmpty()) {
            equation_list.add(current_number.toString());
        }
        // at the end of this method there should just be a list with all digits and operators append to it in the
        // correct order as needed to complete basic maths

        return equation_list;
    }
}
