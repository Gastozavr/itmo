import java.util.Random;

class Main {
    public static void main(String args[]) {
        Random rnd = new Random();
        long[] c = new long[8];
        float[] x = new float[16];
        double[][] y = new double[8][16];

        int end_value = 20; //массив заполняется числами до 20

        for (int i = 0; i < 8; i++) {
            c[i] = end_value - i * 2;

        }
        for (int i = 0; i < 16; i++) {
            x[i] = rnd.nextFloat(-15, 4);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 16; j++) {

                if (c[i] == 20) {
                    y[i][j] = Math.tan(Math.sin(x[j] / (x[j] - 1)));
                }

                if (c[i] == 10 | c[i] == 12 | c[i] == 14 | c[i] == 18) {
                    y[i][j] = Math.pow(Math.tan(Math.pow(x[j] / (2 + x[j]), 2)) *
                            (3 - Math.pow(Math.log(Math.abs(x[j])),
                                    Math.pow(2 * x[j], x[j]) / (Math.log(Math.abs(x[j])) + 1))), 2);
                } else {
                    y[i][j] = Math.pow(Math.E, (Math.atan(Math.cos(x[j]))) / 3);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.printf("%8.3f", y[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}