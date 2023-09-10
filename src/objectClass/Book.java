/**
 * Lớp đối tượng sách.
 * Quản lý các thông tin về sách.
 * Người tạo:  Vũ Anh Tuấn.
 */
package objectClass;

import interFace.IEntity;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

import java.util.Scanner;


public class Book implements IEntity<Book> , Serializable {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    static final String PACE = " ";
    static final double LENGTH_MIN_ID = 0;
    static final double LENGTH_MAX_ID = 4;
    static final double LENGTH_MIN_TITLE = 6;
    static final double LENGTH_MAX_TITLE = 50;
    static  final double YEAR_MIN = 1970;
    //1. Attributes
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    //2. Set,get
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {this.bookId = bookId;};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    //3. Constructor
    public Book() {
    }

    public Book(String bookId, String title, String author, String publisher, int year, String description, int categoryId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    //4. Input, output

    /**
     * Nhập dữ liệu đầu vào cho đối tượng Book.
     * @param scanner
     * @param bookList
     */
    @Override
    public void input(Scanner scanner, List<Book> bookList) {
        this.bookId = validateBookId(scanner,bookList);
        this.title = validateBookTitle(scanner,bookList);
        this.author = validateAuthor(scanner);
        this.publisher = validatePublisher(scanner);
        this.year = validateYear(scanner);
        this.description = validateDescription(scanner);
    }

    /**
     * In đầu ra của đối tượng book
     */
    @Override
    public void output() {
        System.out.printf(MAGENTA+"|\t%-10.10s"+ANSI_RESET+"| \t%-15.15s| \t%-15.15s| \t%-12.15s| \t%-10d|", this.bookId, this.title, this.author, this.publisher, this.year);
    }
    public void outputNameAndAuthor(){
        System.out.printf("|%-20s %-20.17s %-20.20s |\n",PACE,this.getTitle(),this.getAuthor());
    }

    //5. Business methods.

    /**
     * Kiểu tra dữ liệu đầu vào của bookId
     * @param scanner
     * @param bookList
     * @return
     */
    public  String validateBookId(Scanner scanner, List<Book> bookList) {
        do {
            System.out.println(GREEN2+"Nhập Mã sách (Y/C: B***)"+ANSI_RESET);
            String bookIdStr = scanner.nextLine();
            byte isError = 0;
            if (isStrNull(bookIdStr)) {
                System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
                isError++;
            }
            if (!isLength(bookIdStr, LENGTH_MIN_ID, LENGTH_MAX_ID) || !bookIdStr.startsWith("B")) {
                System.out.println(ANSI_RED+"Yêu cầu nhập đúng định dạng."+ANSI_RESET);
                isError++;
            }
            if (isExist(bookIdStr, bookList)) {
                System.out.println(ANSI_RED+"Mã đã tồn tại"+ANSI_RESET);
                isError++;
            }
            if (isError == 0) {
                return  bookIdStr;
            }

        } while (true);
    };

    /**
     * Kiểu tra dữ liệu đầu vào của tiêu đề book
     * @param scanner
     * @param bookList
     * @return
     */
    public String validateBookTitle(Scanner scanner, List<Book> bookList){
        do {
            System.out.println(GREEN2+"Nhập tiêu đề sách (6-50 kí tự)"+ANSI_RESET);
            String bookTitle = scanner.nextLine();
            byte isError = 0;
            if (isStrNull(bookTitle)){
                System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
                isError++;
            }
            if (!isLength(bookTitle,LENGTH_MIN_TITLE,LENGTH_MAX_TITLE)){
                System.out.println(ANSI_RED+"Vui lòng nhập từ 6-50 kí tự."+ANSI_RESET);
                isError++;
            }
            if (isExist(bookTitle,bookList)){
                System.out.println(ANSI_RED+"Tiêu đề đã tồn tại"+ANSI_RESET);
                isError++;
            }
            if (isError == 0){
                return bookTitle;
            }

        }while (true);
    };

    /**
     * Kiểu tra dữ liệu đầu vào Khi nhập tác giả không được
     * để trống.
     * @param scanner
     * @return
     */
    public String validateAuthor(Scanner scanner){
        do {
            System.out.println(GREEN2+"Tên tác giả: "+ANSI_RESET);
            String authorStr = scanner.nextLine();
            byte isError =0;
            if (isStrNull(authorStr)){
                System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
                isError++;
            }
            if (isError==0){
                return  authorStr;
            }
        }while (true);

    }

    /**
     * Kiểu tra dữ liệu đầu vào Khi nhập nhà sản xuất không được
     * bỏ trống
     * @param scanner
     * @return
     */
    public String validatePublisher(Scanner scanner){
        do {
            System.out.println(GREEN2+"Nhà xuất bản: "+ANSI_RESET);
            String authorStr = scanner.nextLine();
            byte isError =0;
            if (isStrNull(authorStr)){
                System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
                isError++;
            }
            if (isError==0){
                return  authorStr;
            }
        }while (true);

    }

    /**
     * Kiểu tra dữ liệu đầu vào của năm xuất bản.
     * Lớn hơn năm 1970 và không được lớn hơn năm hiện tại.
     * @param scanner
     * @return
     */
    public int validateYear(Scanner scanner){
        do {
            byte isError = 0;
            System.out.println(GREEN2+"Năm xuất bản: "+ANSI_RESET);
            try {
                String yearStr = scanner.nextLine();
                if(isStrNull(yearStr)){
                    System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
                    isError ++;
                }
                Year yearCurrent = Year.now();
                int yearInt = Integer.parseInt(yearStr);
                if (yearInt >=YEAR_MIN && yearInt<=yearCurrent.getValue()){
                    return yearInt;
                }else {
                    System.out.printf(ANSI_RED+"Yêu cầu từ năm %.0f đến %s\n"+ANSI_RESET, YEAR_MIN,yearCurrent);
                }

            } catch (NumberFormatException numberFormatException){
                System.out.println(ANSI_RED+"Vui Lòng nhập đúng định dạng năm(VD:2023)"+ANSI_RESET);
            }
            catch (Exception e){
                System.out.println(ANSI_RED+"Lỗi xảy ra trong quá trình validateYear"+ANSI_RESET);
            };
        }while (true);
    };

    /**
     * Kiểu tra dữ liệu đầu của mô tả sách.
     * Không được bỏ trống.
     * @param scanner
     * @return
     */
    public String validateDescription(Scanner scanner){
        do {
            System.out.println(GREEN2+"Nhập mô tả sách: "+ANSI_RESET);
            String description = scanner.nextLine();

            if(!isStrNull(description)){
                return  description;
            }else {
                System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
            }
        }while (true);
    };

    /**
     * Kiểm tra sự tồn tại của đối tượng book.
     * Xem có tồn tại trong List của Book hay không.
     * @param str
     * @param bookList
     * @return
     */
    public  boolean isExist(String str, List<Book> bookList) {
        return bookList.stream()
                .anyMatch(book -> book.getBookId()
                        .equalsIgnoreCase(str));
    };

    /**
     * Kiểm ta độ dài của chuỗi String.
     * Giúp validate giữ liệu đầu vào hợp lệ.
     * @param str
     * @param min
     * @param max
     * @return
     */
    public  boolean isLength(String str, double min, double max) {
        if (min == LENGTH_MIN_ID && max == LENGTH_MAX_ID) {
            return str.length() == LENGTH_MAX_ID ? true : false;
        } else {
            return (str.length() >= min && str.length() <= max) ? true : false;
        }
    };

    /**
     * Kiểm tra rỗng, để nhận biết người dùng có nhập vào dữ liệu hay
     * @param str
     * @return
     */
    public boolean isStrNull(String str) {
        return str==null || str.isEmpty();
    };

}
