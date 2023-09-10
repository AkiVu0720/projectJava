package dataFile;

import objectClass.Book;

import java.io.*;
import java.util.List;

public  interface IDataBook  {
    default  void writeDataBook(List<Book> bookArrayList) {
        //1 Khoi tao doi tuong file
        File file = new File("BookList.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bookArrayList);
            oos.flush();
        } catch (FileNotFoundException ex1) {
            System.err.println("Loi ghi du lieu vao  file");
        } catch (IOException ex2) {
            System.err.println("Loi khong ton tai file");
        } catch (Exception e) {
            System.err.println("Loi xay ra trong qua trinh ghi du lieu");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex1) {
                System.err.println("Loi dong cac stream");
            } catch (Exception e) {
                System.err.println("Loi say ra trong qua trinh dong Stream");
            }
        }
    };


    default List<Book> readData() {
        File file = new File("BookList.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Book> bookList = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            bookList = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex1) {
            System.out.println("Loi khong ton tai file khi doc");
        } catch (IOException ex2) {
            System.out.println("Loi khi doc file");
        } catch (Exception ex3) {
            System.out.println("Loi trong qua tring doc file");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
                System.out.println("Loi xay ra khi dong strem");
            } catch (Exception ex) {
                System.out.println("Loi xay ra trong qua trinh dong cac Stream");
            }
            return bookList;
        }
    }

}
