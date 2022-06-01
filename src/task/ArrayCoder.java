package task;

import java.util.Arrays;

public class ArrayCoder {
    private int[] arr;

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    ArrayCoder(int[] arr){
        setArr(arr);
    }

    public String arrayArchivingToString() {
        String result = "";

        result += this.searchAndEncodeForSequenceOfNumbers();
        result +=":" + searchAndEncodeForDouble();
        result = delExcessPlus(result);

        return result;
    }

        private String searchAndEncodeForSequenceOfNumbers() {
            String result = "";
            Arrays.sort(arr);

            int previousItem = 0;
            int count = 0;
            int delta = 0;

            for (int i = 0; i < arr.length; i++) {
                delta = arr[i] - previousItem;
                if (delta == 1) {
                    previousItem++;
                    count++;
                    if (i == (arr.length - 1)) {
                        result += recordSequenceElement(count, delta, i);
                        previousItem = arr[i];
                    }
                } else if (delta != 0){
                    result += recordSequenceElement(count, delta, i);
                    previousItem = arr[i];
                    count = 0;
                }
            }
            return result.substring(1);
        }

            private String recordSequenceElement(int count, int delta, int i) {
                String result = "";

                while (count != 0) {
                    result += "-" + count;
                    count = 0;
                }
                result += "+" + delta;
                return result;
            }

        private String searchAndEncodeForDouble(){
            String result ="";
            int countOfDoubleNombers = 0;
            int previousDouble = 0;

            for (int j = 1; j < arr.length; j++) {
                if (arr[j] == arr[j-1]) {
                    countOfDoubleNombers++;
                } else {
                    if (countOfDoubleNombers > 0) {
                        result += writeDouble(countOfDoubleNombers, previousDouble, j);
                        previousDouble = arr[j-1];
                        countOfDoubleNombers = 0;
                    }
                }
            }
            if (countOfDoubleNombers > 0) {
                result += writeDouble(countOfDoubleNombers, previousDouble, arr.length - 1);
            }
            return result.substring(1);
        }

            private String writeDouble(int countOfDoubleNumbers, int previousDouble, int j){
                String result = "";
                if (countOfDoubleNumbers > 0) {
                    result = "+" + (arr[j - 1] - previousDouble) + "-" + countOfDoubleNumbers;
                }
                return result;
            }

        private String delExcessPlus(String str) {
            String[] lineDelPlus = str.split("\\+");
            String result = "";
            String buffer = "";
            for (String s : lineDelPlus) {
                if (s.length() == 1) {
                    buffer += s;
                } else {
                    if (buffer.length() > 0) {
                        result += "*" + buffer;
                        buffer = "";
                        result += "+" + s;
                    } else {
                        result += "+" + s;
                    }
                }
            }
            return result;
        }
}
