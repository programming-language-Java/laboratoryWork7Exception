import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
//        work1();
        work2();
    }

    public static void work1() {
//        double x = 2.0; // отрицательное значение
//        double x = 3.0; // деление на ноль
        double x = 5.0; // ок

        double expressionUnderSqrt = Math.pow(x, 2) - 9;
        negativeSquareRootException(expressionUnderSqrt);
        double denominatorZ1 = Math.pow(x, 2) - 2 * x * 3 + Math.sqrt(expressionUnderSqrt);
        divisionByZeroException(denominatorZ1);
        double Z1 = (Math.pow(x, 2) + 2 * x + (x + 1) * Math.sqrt(expressionUnderSqrt)) / denominatorZ1;
        System.out.println("Z1 = " + Z1);

        double denominatorZ2 = (x - 3);
        divisionByZeroException(denominatorZ2);
        double division = (x + 3) / denominatorZ2;
        negativeSquareRootException(division);
        double Z2 = Math.sqrt(division);
        System.out.println("Z2 = " + Z2);
    }

    public static void negativeSquareRootException(double value) {
        if (value < 0) {
            throw new ArithmeticException("Отрицательное значение в кв. корне");
        }
    }

    public static void divisionByZeroException(double value) {
        if (value == 0) {
            throw new ArithmeticException("Делить на ноль нельзя");
        }
    }

    public static void work2() {
        Scanner scanner = new Scanner(System.in);

        // Ввод размера массива
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        StringBuilder strArray = new StringBuilder();
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
            strArray.append(array[i]).append(" ");
        }
        System.out.println("Массив: " + strArray);

        noNegativeNumbersException(array);
        array = insertBeforeNegative(array);

        System.out.println("Новый массив:");
        for (int val : array) {
            System.out.print(val + " ");
        }
    }

    public static void noNegativeNumbersException(int[] array) {
        for (int i : array) {
            if (i < 0) return;
        }
        throw new RuntimeException("В данном массиве нет отрицательных чисел");
    }

    public static int[] insertBeforeNegative(int[] array) {
        int countNegativeNumbers = countNegativeNumbers(array);
        int arraySize = array.length + countNegativeNumbers;
        int[] newArray = new int[arraySize];
        int newIndex = 0;
        Scanner scanner = new Scanner(System.in);
        int quantityInputMismatchException = 0;
        for (int i : array) {
            if (i < 0) {
                try {
                    System.out.print("Введите число: ");
                    int newElement = scanner.nextInt();
                    newArray[newIndex++] = newElement;
                } catch (InputMismatchException e) {
                    System.out.println("Вы ввели строку, а не число");
                    scanner.nextLine();
                    quantityInputMismatchException++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Переполнение массива");
                }
            }
            newArray[newIndex++] = i;
        }
        if (quantityInputMismatchException > 0) {
            return resizeArray(arraySize, quantityInputMismatchException, newArray);
        }
        return newArray;
    }


    public static int countNegativeNumbers(int[] array) {
        int quantityNegativeNumbers = 0;
        for (int i : array) {
            if (i < 0) {
                quantityNegativeNumbers++;
            }
        }
        return quantityNegativeNumbers;
    }

    public static int[] resizeArray(int arraySize, int quantityInputMismatchException, int[] newArray) {
        int newArraySize = arraySize - quantityInputMismatchException;
        int[] arr = new int[newArraySize];
        System.arraycopy(newArray, 0, arr, 0, newArraySize);
        return arr;
    }
}