import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class FileUtilCopy {

    public static void readFromFile1(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            long startTime = System.currentTimeMillis();
            int ch;
            while( (ch = fin.read()) != -1) {
                System.out.print((char)ch);
            }
            System.out.println();
            long timeNeeded = System.currentTimeMillis() - startTime;
            System.out.println("Time needed: " + timeNeeded);

            fin.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFromFile2(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            long startTime = System.currentTimeMillis();
            int count;
            byte [] data = new byte[1024 * 8];

            while( (count = fin.read(data)) != -1) {
                //System.out.print((char)ch);
            }
            System.out.println();
            long timeNeeded = System.currentTimeMillis() - startTime;
            System.out.println("Time needed: " + timeNeeded);

            fin.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void byteArrayInputStreamDemo() {
        byte [] src = "This is a sample string".getBytes();
        //ByteArrayInputStream bin = new ByteArrayInputStream(src);
        ByteArrayInputStream bin = new ByteArrayInputStream(src, 4, 10);

        int ch;
        try {
            while( (ch = bin.read()) != -1) {
                System.out.print((char)ch);
            }

            bin.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bufferedInputStream() {
        try {
            FileInputStream fin = new FileInputStream("/Users/vishal/projects/Old Connecting Matters/sanskriti_school_db.sql");
            BufferedInputStream bin = new BufferedInputStream(fin);

            byte [] data = new byte[1024 * 8];
            int count;
            long startTime = System.currentTimeMillis();
            while( (count = bin.read(data)) != -1) {

            }
            bin.close();

            System.out.println("time: " + (System.currentTimeMillis() - startTime));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void pushbackInputStreamDemo() {
        try {
            PushbackInputStream pin =
                    new PushbackInputStream(
                            new ByteArrayInputStream(
                                    "BitCode Students are Best! Just kidding!".getBytes()
                            ),
                            108
                    );

            int ch;
            ch = pin.read();
            System.out.print((char)ch);
            ch = pin.read();
            System.out.print((char)ch);
            ch = pin.read();
            System.out.print((char)ch);

            pin.unread(ch);

            ch = pin.read();
            System.out.print((char)ch);

            pin.unread("Lab".getBytes());

            while( (ch = pin.read()) != -1) {
                System.out.print( (char)ch);
            }

            pin.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sequenceInputStreamDemo() {

        try {
            FileInputStream fin = new FileInputStream("/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/FileUtil.java");
            ByteArrayInputStream bin = new ByteArrayInputStream("This is data from another stream!".getBytes());
            ByteArrayInputStream bin1 = new ByteArrayInputStream("******This is data from another stream!*****".getBytes());

            InputStream [] inputStreams = {fin, bin, bin1};
             SequenceInputStream sin = new SequenceInputStream(
                     Collections.enumeration(
                             Arrays.asList(inputStreams)
                     )
             );

            //SequenceInputStream sin = new SequenceInputStream(fin, bin);
            int ch;

            while( (ch = sin.read()) != -1) {
                System.out.print((char) ch);
            }
            sin.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void readFromStream(InputStream in) {
    }

    //File input stream and output stream
    public static boolean copy1(String srcPath, String destPath) {
        try {
            File srcFile = new File(srcPath);
            if (!srcFile.exists() || srcFile.isDirectory()) {
                return false;
            }

            File destFile = new File(destPath);
            if (destFile.exists() && destFile.isDirectory()) {
                return false;
            }

            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            FileInputStream fin = new FileInputStream(srcFile);
            BufferedInputStream bin = new BufferedInputStream(fin);

            FileOutputStream fout = new FileOutputStream(destFile);

            byte [] data = new byte[1024 * 8];
            int count;

            while( (count = bin.read(data)) != -1) {
                fout.write(data, 0, count);
            }

            bin.close();
            fout.close();

            return true;
        }
        catch (Exception e) {
        }

        return false;
    }

}