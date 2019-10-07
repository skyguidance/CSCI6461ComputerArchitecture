package test;

import java.util.ArrayList;

/*
reading 20 number input from user
 */
public class Test_Program_1 {
    private String userinput;// example "123,4,4,494,73"
    ArrayList<String> user20number = new ArrayList<>(20);

    public Test_Program_1(String userinput) {
        this.userinput = userinput;
        String[] parseString=userinput.split(",");
        for (int i = 0; i < parseString.length; i++) {
            add(parseString[i]);
        }
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            System.out.println("Please input valid number from 0 to 65536");
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
        System.out.println("the colest number is "+user20number.get(colesti));
        return user20number.get(colesti);

    }
}


