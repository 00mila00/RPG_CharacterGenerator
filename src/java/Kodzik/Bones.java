package Kodzik;

import java.util.Random;

public abstract class Bones {
    private Bones() {}

    public static String gererate(int number, int top) {
        if (number > 100 || top > 100 || number < 1 || top < 2) {
            return "To Ci się nie przyda";
        } else {
            Random random = new Random();
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            int temp = 1 + random.nextInt(top);
            builder.append(temp);
            int sum = temp;
            for (int i = 0; i < number - 1; i++) {
                temp = 1 + random.nextInt(top);
                sum += temp;
                builder.append(" + ");
                builder.append(temp);
            }
            builder.append("} = ");
            builder.append(sum);
            return builder.toString();
        }
    }

    public static int[] fromString(String p1k5) {
        String[] split = p1k5.split("k");
        int[] exit = new int[2];
        exit[0] = Integer.parseInt(split[0]);
        exit[1] = Integer.parseInt(split[1]);
        return exit;
    }
}
