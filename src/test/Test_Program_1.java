package test;

import java.util.ArrayList;

/*
reading 20 number input from user
 */
public class Test_Program_1 {
    String userinput;// example "1234449473/n"
    ArrayList<String> user20number = new ArrayList<>(20);

    public Test_Program_1(String userinput) {
        this.userinput = userinput;

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void add(String input) {
        if (isInteger(input)) {
            user20number.add(input);
        }
    }

    public String findCloseNumber(String input) {
        int intInput = Integer.valueOf(input);
        int abs = Integer.MAX_VALUE;
        ;
        int currentabs = 0;
        int colesti = -1;

        for (int i = 0; i < user20number.size(); i++) {
            currentabs = Math.abs(Integer.valueOf(user20number.get(i)) - intInput);
            if (currentabs < abs) {
                abs = currentabs;
                colesti = i;
            }


        }
        return user20number.get(colesti);
    }
}


