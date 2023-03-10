import java.util.Arrays;

public class EncryptorTester
{
    public static void main(String[] args) {
        // --------------------------------
        // ---- TEST PART A: fillBlock ----
        // --------------------------------
        System.out.println("---- TESTING PART A ----");
        Encryptor encryptor1 = new Encryptor(2, 3);
       //System.out.println(encryptor1.encryptMessage("bob the builder built bobbers"));
        //System.out.println(encryptor1.decryptMessage(encryptor1.encryptMessage("bob the builder built bobbers")));
        System.out.println(encryptor1.decryptMessage("RAAAAATAAIAAOAA"));
        System.out.println(encryptor1.superEncryptMessage(""));
        String superEncrypted = encryptor1.superEncryptMessage("abcdefg");
        System.out.println(superEncrypted);
        System.out.println(encryptor1.superDecryptMessage(superEncrypted));
    }
    public static void print2DArray(String[][] arr)
    {
        for (String[] row : arr)
        {
            for (String val : row)
            {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static boolean testArr(String[][] expected, String[][] actual)
    {
        if (expected.length != actual.length)
        {
            return false;
        }

        if (expected[0].length != actual[0].length)
        {
            return false;
        }

        for (int row = 0; row < expected.length; row++)
        {
            for (int col = 0; col < expected[0].length; col++)
            {
                String expElement = expected[row][col];
                String actElement = actual[row][col];
                if (!expElement.equals(actElement))
                {
                    return false;
                }
            }
        }
        return true;
    }

}