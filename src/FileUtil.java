import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class FileUtil {

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

    public static boolean copy2(String srcPath, String destPath) {
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
            BufferedOutputStream bout = new BufferedOutputStream(
                    fout,
                    32 * 1024
            );

            byte [] data = new byte[1024 * 8];
            int count;

            while( (count = bin.read(data)) != -1) {
                bout.write(data, 0, count);
            }

            bin.close();
            bout.close();

            return true;
        }
        catch (Exception e) {
        }

        return false;
    }

    public static void byteArrayOutputStreamDemo() {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            bout.write("Java is too good!".getBytes());
            bout.write("\nJava is amazing!".getBytes());
            bout.write("\nJava is awesome!\n".getBytes());

            byte [] data = bout.toByteArray();
            System.out.println(new String(data));

            bout.close();
        }
        catch (IOException e) {

        }
    }

    public static void printStreamDemo() {
        try {
            PrintStream ps = new PrintStream(
                    new FileOutputStream("/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/Demo.txt")
            );

            ps.println("This is line 1\n");
            ps.println(200 + 300);
            ps.println("The numbers are: " + 1000 + " " + 2000);

            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dataOutputStreamDemo() {
        try {
            DataOutputStream dout = new DataOutputStream(
                    new FileOutputStream(
                            "/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/products_db.txt"
                    )
            );

            dout.writeInt(2340);
            dout.writeFloat(4590.44f);
            dout.writeUTF("Some Product");
            dout.writeBoolean(true);

            dout.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void dataInputStreamDemo() {
        try {
            DataInputStream din = new DataInputStream(
                    new FileInputStream(
                            "/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/products_db.txt"
                    )
            );
            int id = din.readInt();
            float price = din.readFloat();
            String title = din.readUTF();
            boolean isAvailable = din.readBoolean();

            System.out.println(id + " " + price + " " + title + " " + isAvailable);


            din.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void objectSerializationDemo() {
        Product p1 = new Product(101, 2345.56f, "Product1", 4.3f);
        Product p2 = new Product(201, 2885.90f, "Product2", 4.8f);

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/products.bin")
            );

            out.writeObject(p1);
            out.writeObject(p2);

            out.close();

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/products.bin")
            );

            Product p11 = (Product) in.readObject();
            Product p12 = (Product) in.readObject();

            System.out.println(p11);
            System.out.println(p12);

            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFromStdin() {

        Scanner scanner = new Scanner(System.in);

        System.err.println(
                scanner.nextInt()
        );
        scanner.nextLine();
        System.err.println(
                scanner.nextLine()
        );

        System.err.println(
                scanner.nextFloat()
        );

    }

    public static void readerWriterDemo() {
        try {

            OutputStreamWriter writer =
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    "/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/dummy.txt"
                            )
                    );

            writer.write("this is a sample string....\n ");
            writer.write("this is a sample string....\n ");
            writer.write("this is a sample string....\n ");
            writer.write("this is a sample string....\n ");
            writer.write("this is a sample string....\n ");

            writer.close();

            FileInputStream fin = new FileInputStream(
                    "/Users/vishal/java/workspaces/2024-06-10/InputStream1/src/dummy.txt"
            );
            InputStreamReader reader = new InputStreamReader(fin);
            char [] data = new char[128];
            int count;

            while( (count = reader.read(data)) != -1) {
                System.out.print(new String(data, 0, count));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}