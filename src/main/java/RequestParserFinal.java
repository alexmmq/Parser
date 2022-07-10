import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/*
Request parser
*/

    public class RequestParserFinal  {
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String url = reader.readLine();
            char q = '?';
            char a = '&';
            String e = "=";
            char[] arrayOfChars = url.toCharArray();
            ArrayList<Character> arrayListOfParameters = new ArrayList<>();

            // sorting out the parameters, creating an Arraylist of characters, excluding '?'
            for(int i = 0; i<arrayOfChars.length; i++){
                if(arrayOfChars[i] == q){
                    for(int j = i+1; j<arrayOfChars.length; j++){
                        arrayListOfParameters.add(arrayOfChars[j]);
                    }
                }
            }

            //sorting out delimiters '&', getting Arraylist of separate parameters
            ArrayList<String> listOfStrings = new ArrayList<>();
            StringBuffer stringBuffer = new StringBuffer();
            for(int k = 0; k<arrayListOfParameters.size(); k++){
                if(!arrayListOfParameters.get(k).equals(a)){
                    stringBuffer.append(arrayListOfParameters.get(k));
                }
                else{
                    listOfStrings.add(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                }
                if(k == arrayListOfParameters.size()-1){
                    listOfStrings.add(stringBuffer.toString());
                }
            }

            //sorting out delimiters '=', getting a LinkedHashMap of parameters and values
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            for(int l = 0; l<listOfStrings.size(); l++){
                if(listOfStrings.get(l).contains(e)){
                    String[] strings = listOfStrings.get(l).split(e);
                    map.put(strings[0], strings[1]);
                }
                else{
                    map.put(listOfStrings.get(l), null);
                }
            }

            //checking for the parameter obj, printing parameters first
            String objValue = null;
            for(Map.Entry<String, String> entry:map.entrySet()){
                if(entry.getKey().contains("obj")){
                    objValue = entry.getValue();
                }
                System.out.print(entry.getKey() + " ");
            }

            //checking if objValue is a digit
            double valueDouble;
            try{
                valueDouble = Double.parseDouble(objValue);
                System.out.println();
                alert(valueDouble);
            }
            catch(Exception exception){
                exception.getStackTrace();
                if(objValue!=null) {
                    System.out.println();
                    alert(objValue);
                }
            }
            //objValue is a String

        }

        public static void alert(double value) {
            System.out.println("double: " + value);
        }

        public static void alert(String value) {
            System.out.println("String: " + value);
        }
    }
