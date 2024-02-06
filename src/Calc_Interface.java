import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calc_Interface {

    public void main_calc_interface(){

        Scanner in = new Scanner(System.in);
        // read console input line
        String input_equation = in.nextLine();

        //create string to add parts of the equation to it to complete the calculation all in on go
        List<String> equation_list = new ArrayList<String>();

        //create list ot add together current numbers
        StringBuilder current_number = new StringBuilder();


        // run through length of the equation character bt character
        for (int i = 0; i <= input_equation.length()-1; i++){

            // check to see if the character in the current position of i is a digit or not.
            if(Character.isDigit(input_equation.charAt(i))){
                // if character is a digit then we add it to the current list of digits
                current_number.append(input_equation.charAt(i));
            // if "i" is at max length then need to add any other operators or numbers that have been reached
            } else if (i == input_equation.length()-1) {
                if(!current_number.isEmpty()){
                    equation_list.add(current_number.toString());
                } else{
                    equation_list.add(String.valueOf((input_equation.charAt(i))));
                }
            } else{
                // add the current number to the equation liat
                equation_list.add(current_number.toString());

                //since the latest number is now added ot the list of equations, and the newest point of I is no longer
                // pointing to a digit then we need to add the operator to the list of equations.
                equation_list.add(String.valueOf(input_equation.charAt(i)));

                // reset current number to go back to "" as we don't want to keep adding current number
                current_number = new StringBuilder();

                System.out.println(equation_list);
            }
        }

        System.out.println(equation_list);

    }
}
