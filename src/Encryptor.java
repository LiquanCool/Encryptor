public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int counter =1;
        for (int j = 0;j<letterBlock.length;j++)
        {
            for (int k = 0;k<letterBlock[0].length;k++)
            {
                if (counter<=str.length())
                {
                    letterBlock[j][k] = str.substring(counter-1,counter);
                    counter++;
                }
                else
                {
                    letterBlock[j][k] = "A";
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String encrypted = "";
        for (int i = 0;i<letterBlock[0].length;i++)
        {
            for (int j = 0;j<letterBlock.length;j++)
            {
                encrypted+=letterBlock[j][i];
            }
        }
        return encrypted;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        String encrypted = "";
        int last = 0;
        for (int i = 0;i<message.length()-(numCols*numRows);i+=(numRows*numCols))
        {
            fillBlock(message.substring(i,i+(numCols*numRows)));
            encrypted+=encryptBlock();
            last = i+(numRows*numCols);
        }
        fillBlock(message.substring(last));
        encrypted+=encryptBlock();
        return encrypted;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String unencrypted = "";
        int last = 0;
        for (int i = 0;i<encryptedMessage.length()-(numCols*numRows);i+=(numRows*numCols))
        {
            deFillBlock(encryptedMessage.substring(i,i+(numCols*numRows)));
            for (int r = 0;r<letterBlock.length;r++)
            {
                for (int c = 0;c<letterBlock[0].length;c++)
                {
                    unencrypted+=letterBlock[r][c];
                }
            }
            last = i+(numRows*numCols);
        }
        deFillBlock(encryptedMessage.substring(last));
        for (int r = 0;r<letterBlock.length;r++)
        {
            for (int c = 0;c<letterBlock[0].length;c++)
            {
                    unencrypted+=letterBlock[r][c];

            }
        }
        for (int i = unencrypted.length()-1;i>=0;i--)
        {
            if (unencrypted.substring(i,i+1).equals("A"))
            {
                unencrypted=unencrypted.substring(0,i);
            }
            else {
                break;
            }
        }
        return unencrypted;
    }
    public void deFillBlock(String str)
    {
        int counter =1;
        for (int c = 0;c<letterBlock[0].length;c++)
        {
            for (int r = 0;r<letterBlock.length;r++)
            {
                if (counter<=str.length())
                {
                    letterBlock[r][c] = str.substring(counter-1,counter);
                    counter++;
                }
                else
                {
                    letterBlock[r][c] = "A";
                }
            }
        }
    }
    public String superEncryptMessage(String message)
    {
        String encrypted = "";
        int last = 0;
        for (int i = 0;i<message.length()-(numCols*numRows);i+=(numRows*numCols))
        {
            superFillBlock(message.substring(i,i+(numCols*numRows)));
            encrypted+=encryptBlock();
            last = i+(numRows*numCols);
        }
        superFillBlock(message.substring(last));
        encrypted+=encryptBlock();
        return encrypted;
    }
    public String superDecryptMessage(String encryptedMessage)
    {
        String unencrypted = "";
        int last = 0;
        boolean first =true;
        for (int i = 0;i<encryptedMessage.length()-(numCols*numRows);i+=(numRows*numCols))
        {
            superDeFillBlock(encryptedMessage.substring(i,i+(numCols*numRows)), first);
            if (first)
            {
                first =false;
            }
            else
            {
                first =true;
            }
            for (int r = 0;r<letterBlock.length;r++)
            {
                for (int c = 0;c<letterBlock[0].length;c++)
                {
                    if (!(letterBlock[r][c].equals("A")))
                    {
                        unencrypted+=letterBlock[r][c].substring(0,1);
                    }
                }
            }
            last = i+(numRows*numCols);
        }
        superDeFillBlock(encryptedMessage.substring(last),first);
        for (int r = 0;r<letterBlock.length;r++)
        {
            for (int c = 0;c<letterBlock[0].length;c++)
            {
                unencrypted+=letterBlock[r][c].substring(0,1);
            }
        }
        for (int i = unencrypted.length()-1;i>=0;i--)
        {
            if (unencrypted.substring(i,i+1).equals("A"))
            {
                unencrypted=unencrypted.substring(0,i);
            }
            else {
                break;
            }
        }
        return unencrypted;
    }
    public void superFillBlock(String str)
    {
        int counter =1;
        for (int j = 0;j<letterBlock.length;j++)
        {
            for (int k = 0;k<letterBlock[0].length;k++)
            {
                if (counter<=str.length())
                {
                    int randomIndex = (int)(Math.random()*str.length());
                    letterBlock[j][k] = str.substring(counter-1,counter)+str.substring(randomIndex,randomIndex+1);
                    counter++;
                }
                else
                {
                    letterBlock[j][k] = "A";
                }
            }
        }
    }
    public void superDeFillBlock(String str, boolean first)
    {
        int counter =0;
        if (first) {
            for (int c = 0; c < letterBlock[0].length; c += 1) {
                for (int r = 0; r < letterBlock.length; r += 1) {
                    if (counter < str.length()) {
                        letterBlock[r][c] = str.substring(counter, counter + 1);
                        counter += 2;
                    } else {
                        letterBlock[r][c] = "A";
                    }
                }
            }
            first = false;
        }
        else
        {
            for (int c = numCols/2; c < letterBlock[0].length; c += 1) {
                for (int r = numRows/2; r < letterBlock.length; r += 1) {
                    if (counter < str.length()) {
                        letterBlock[r][c] = str.substring(counter, counter + 1);
                        counter += 2;
                    } else {
                        letterBlock[r][c] = "A";
                    }
                }
            }
            first = true;
        }
        for (int r =0;r<letterBlock.length;r++)
        {
            for (int c =0;c<letterBlock[0].length;c++)
            {
                System.out.print(letterBlock[r][c]);
            }
            System.out.println(" ");
        }
    }
}