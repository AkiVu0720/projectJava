package dataFile;

import objectClass.Category;

import java.io.*;
import java.util.List;

public interface IDataCategory {
    default  void writeDataToFile(List<Category>categoryList) {
        File file = new File("Category.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(categoryList);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Lỗi runtime");
        } catch (IOException e) {
            System.err.println("Lỗi IO");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.err.println("Lỗi runtime");
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Lỗi IO");
                }
            }
        }

    };

    default List<Category> readDataCategory() {
        File file = new File("Category.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Category> categoryList = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            categoryList = (List<Category>) ois.readObject();
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
            return categoryList;
        }
    }

    default List<Category> readDataFromFile() {
        File file = new File("Category.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Category> categoryList = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            if (ois.readObject() != null) {
                categoryList = (List<Category>) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Không tồn tại file");
        } catch (IOException e) {
            System.err.println("Lỗi IO");
        } catch (ClassNotFoundException e) {
            System.err.println("Lớp không tồn tại");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Lỗi runtime");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Lỗi IO");
                }
            }
        }
        return  categoryList;
    }

}
