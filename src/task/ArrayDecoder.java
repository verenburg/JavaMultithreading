package task;

import java.util.ArrayList;
import java.util.List;

public class ArrayDecoder {
    public static int[] decodeStringIntoArray(String archivedString){
        List<Integer> listOfDecodedNumbers = new ArrayList<>();

        String archivedStringWithAddedPlus = addPlus(archivedString);
        String[] code = archivedStringWithAddedPlus.split(":");

        listOfDecodedNumbers = decodeSequencePart(code[0]);
        listOfDecodedNumbers.addAll(decodeDuplicatePart (code[1]));

        return listToArray (listOfDecodedNumbers);
    }

        private static String addPlus(String str) {
            String result = "";
            String[] phrases = str.split("\\*");
            for (String phrase : phrases) {
                String words[] = phrase.split("\\+");
                char[] chars = words[0].toCharArray();
                for (char c : chars) {
                    result += "+" + c;
                }
                for (int i = 1; i < words.length; i++) {
                    result += "+" + words[i];
                }
            }
            result = result.substring(1);
            return result;
        }

        private static List<Integer> decodeSequencePart(String encryptedSequence){
            List<Integer>  result =new ArrayList<>();
            int number = 0;
            String[] SequenceOfNumbers = encryptedSequence.split("\\+");
            String[] sequenceElement = new String[2];
            for (String s : SequenceOfNumbers) {
                sequenceElement = s.split("-");
                number += Integer.parseInt(sequenceElement[0]);
                result.add(number);
                if (sequenceElement.length > 1) {
                    for (int j = 0; j < Integer.parseInt(sequenceElement[1]); j++){
                        number++;
                        result.add(number);
                    }
                }
            }

            return result;
        }

        private static List<Integer> decodeDuplicatePart(String encryptedDuplicate){
            List<Integer> result = new ArrayList<>();
            String[] duplicates = encryptedDuplicate.split("\\+");
            int duplicate = 0;
            String[] duplicatePair = new String[2];
            for (String duplicateCode: duplicates) {
                duplicatePair = duplicateCode.split("-");
                duplicate += Integer.parseInt(duplicatePair[0]);
                result.add(duplicate);
                for (int j = 0; j < Integer.parseInt(duplicatePair[1])-1; j++) {
                    result.add(duplicate);
                }
            }
            return result;
        }

        private static int[] listToArray (List<Integer> list) {
            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }
}
