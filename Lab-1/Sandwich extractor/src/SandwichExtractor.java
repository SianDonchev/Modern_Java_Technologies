import java.util.Arrays;

public class SandwichExtractor {
    private static int sizeOfSandwichWithoutOlives(String[] ingredients){
        int size = 0;
        for(String ingredient : ingredients){
            if(!ingredient.equals("olives")){
               ++size;
            }
        }
        return size;
    }

    public static String[] removeOlives(String[] ingredients){
        String[] removedOlives = new String[sizeOfSandwichWithoutOlives(ingredients)];
        int i = 0;
        for(String ingredient : ingredients){
            if(!ingredient.equals("olives")){
                removedOlives[i] = ingredient;
                ++i;
            }
        }
        return removedOlives;
    }

    public static String[] extractIngredients(String sandwich) {
        int indexOfFirstBread;
        int indexOfLastBread;
        boolean validSandwich;
        String[] arrayOfIngredients = null;

        indexOfFirstBread = sandwich.indexOf("bread");
        indexOfLastBread = sandwich.lastIndexOf("bread");
        validSandwich = indexOfFirstBread != indexOfLastBread ? true : false;

        if (validSandwich) {
            String[] layers = sandwich.split("bread");
            String ingredients = indexOfFirstBread != 0 ? layers[1] : layers[0];
            arrayOfIngredients = ingredients.split("-");
        } else {
            arrayOfIngredients = new String[1];
            arrayOfIngredients[0] = "";
        }

        arrayOfIngredients = removeOlives(arrayOfIngredients);
        Arrays.sort(arrayOfIngredients);
        return arrayOfIngredients;
    }

    public static void main(String[] args) {

        String[] example1 = extractIngredients("asdbreadham-tomato-mayobreadblabla");
        for (String ingredient : example1) {
            System.out.print(ingredient);
            System.out.print(" ");
        }
        System.out.println();
        String[] example2 = extractIngredients("asdbreadham-olives-tomato-olives-mayobreadblabla");
        for (String ingredient : example2) {
            System.out.print(ingredient);
            System.out.print(" ");
        }
        System.out.println();
        String[] example3 = extractIngredients("asdbreadham");
        for (String ingredient : example3) {
            System.out.print(ingredient);
            System.out.print("");
        }
    }
}
