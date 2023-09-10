/**
 * Class khởi chạy chương trình.
 * Người tạo: VŨ ANH TUẤN
 *
 */
package run;
import objectClass.Book;
import objectClass.Category;


import java.io.*;
import java.util.*;

public class Lybrary {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_UNDERLINED = "\u001B[33m";
    public static final String BACKGROUND_CYAN = "\u001B[45m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[32m";
    //\u001B[7m
    static List<Category> categoryList = new ArrayList<>();
    static  List<Book> bookList = new ArrayList<>();
    static final String PACE = " ";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                menuData();
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        runMenu(scanner,"category1.txt","book2.txt");
                        break;
                    case 2:
                        runMenu(scanner,"category.txt","book.txt");
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(ANSI_RED+"Vui long chon 1 hoac 2. Nhan 0 de thoat"+ANSI_RESET);
                        break;
                }
            }catch (NumberFormatException numberFormatException){
                System.out.println(ANSI_RED+"Vui long chon 1 hoac 2. Nhan 0 de thoat"+ANSI_RESET);
            }catch (Exception e){
                System.out.println("Lựa chọn của bạn chưa hợp lệ.");
            }

        }while (true);

    }
    public static void menuLibrary(){
        System.out.println("\n");
        System.out.println(GREEN+"************************* QUẢN LÝ THƯ VIỆN ************************"+ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t(1)\t Quản lý Thể loại");
        System.out.println("\t\t\t\t\t(2)\t Quản lý Sách");
        System.out.println("\t\t\t\t\t(0)\t Thoát");
        System.out.println("******************************************************************");

    }
    public static void menuData(){
        System.out.println("\n");
        System.out.println(GREEN+"********************** CHỌN NGUỒN DỮ LIỆU ************************"+ANSI_RESET);
        System.out.println("\t\t\t\t\t(1)\t Trống dữ liệu");
        System.out.println("\t\t\t\t\t(2)\t Có dữ liệu ");
        System.out.println("\t\t\t\t\t(0)\t Thoát");
        System.out.println("******************************************************************");

    }

    public static void runMenu(Scanner scanner, String catarogyData, String bookData){
        readDataCategory(catarogyData);
        readDataBook(bookData);
        boolean isExit = false;
        do {
            menuLibrary();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        runMenuCategory(catarogyData);
                        break;
                    case 2:
                        runMenuBook(bookData);
                        break;
                    case 0:
                        isExit = true;
                        break;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(ANSI_RED+"Bạn vui lòng chọn chức năng tương ứng để quản lý");
                System.out.println("            ----------------------"+ANSI_RESET);
            }
            catch (Exception e){
                System.out.println("Lỗi trong quá trình chạy menu quản lý thư viện");
            }
        }while (!isExit);

    };
    // Quản lý Category.
    public static void menuCategory(){
        System.out.println("\n");
        System.out.println(GREEN+"************************ QUẢN LÝ THỂ LOẠI ************************"+ANSI_RESET);
        System.out.printf("%-26s %s\n","","~~~~~~~~~~~~~");
        System.out.printf("%-5s %s\n","","(1)\t Thêm mới thể loại ");
        System.out.printf("%-5s %s\n","","(2)\t Hiển thị danh sách theo tên A–Z");
        System.out.printf("%-5s %s\n","","(3)\t Thống kê thể loại và số sách có trong mỗi thể loại");
        System.out.printf("%-5s %s\n","","(4)\t Cập nhật thể loại");
        System.out.printf("%-5s %s\n","","(5)\t Xóa thể loại");
        System.out.printf("%-5s %s\n","","(0)\t Quay lại");
        System.out.println("*******************************************************************");
    }

    /**
     * Hàm khởi chạy Category
     */
    public static void runMenuCategory(String categoryData){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        do {
            menuCategory();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        addList(scanner);
                        writeDataToFile(categoryData);
                        break;
                    case 2:
                        readDataCategory(categoryData);
                        showListA_Z();
                        break;
                    case 3:
                        numberCatagoryAndNumberBook();
                        break;
                    case 4:
                        updateCategory(scanner);
                        writeDataToFile(categoryData);
                        break;
                    case 5:
                        deleteCategory(scanner);
                        writeDataToFile(categoryData);
                        break;
                    case 0:
                        writeDataToFile(categoryData);
                        isExit = true;
                        break;
                    default:
                        System.out.println( ANSI_RED+"Yêu cầu của bạn không tồn tại"+ANSI_RESET);
                        break;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(ANSI_RED+"Bạn vui lòng chọn chức năng tương ứng để quản lí");
                System.out.println("            ----------------------"+ANSI_RESET);
            } catch (Exception e){
                System.out.println("Lỗi trong quá trình chọn menu Category");
            }

        }while (!isExit);
    }

    /**
     * Thêm category vào List.
     * @param scanner
     */
    public static void addList(Scanner scanner){
        Category category = new Category();
        category.input(scanner,categoryList);
        categoryList.add(category);
        System.out.println(YELLOW_UNDERLINED+"Đã thêm mới thành công!"+ANSI_RESET);
    }

    /**
     * In ra các thể loại sách từ A-Z theo tên thể loại
     */
    public static void showListA_Z(){
        if (categoryList.size()!=0){
            System.out.printf(BACKGROUND_CYAN+"|\t%-10s |\t%-20s|\t%-20s|"+ANSI_RESET+"\n","Mã ID","Tên thể loại", "Trạng thái");
            System.out.println("================================================================");
            categoryList.stream()
                    .sorted(Comparator.comparing(Category::getCategoryName))
                    .forEach(category1 ->{
                        category1.output();});

            System.out.println("================================================================");
        }else {
            System.out.println(ANSI_RED+"Vui lòng thêm mới"+ANSI_RESET);
        }
    };
    public static void showListID_A_Z(){
        if (categoryList.size()!=0){

            System.out.printf(BACKGROUND_CYAN+"|\t%-10s |\t%-20s|\t%-20s|"+ANSI_RESET+"\n","Mã ID","Tên thể loại", "Trạng thái");
            System.out.println("================================================================");
            categoryList.stream()
                    .sorted(Comparator.comparing(Category::getCategoryId))
                    .forEach(category1 ->{
                        category1.output();});

            System.out.println("================================================================");
        }else {
            System.out.println(ANSI_RED+"Vui lòng thêm mới"+ANSI_RESET);
        }
    };

    /**
     * In ra dnh sách category chỉ có mã id
     * và tên.
     * Sắp xếp theo Id.
     */
    /**
     * Thống kê thể loại và số lương sách theo mỗi thể loại.
     */
    public static void numberCatagoryAndNumberBook(){
        long quantity = categoryList.stream().count();
        System.out.println("Có  " + quantity + " loại");
        byte error = 0;
        if (quantity==0) {
            System.out.println(ANSI_RED+"Chưa có Thể Loại sách nào. Vui lòng thêm Thể loại sách."+ANSI_RESET);
            error++;
        }
        if (error==0){
            System.out.printf(BACKGROUND_CYAN+"|\t%-7s |\t%-20s |\t%-9s |\n"+ANSI_RESET,"Mã ID","Tên thể loại", "S.lượng");
            for (Category category1 : categoryList) {
                int number = 0;
                for (Book book: bookList) {
                    if (book.getCategoryId()==category1.getCategoryId()){
                        number++;
                    }
                }
                System.out.println("---------------------------------------------------");
                System.out.printf("|\t%04d \t|\t%-20s |\t\t%-5d |\n",
                        category1.getCategoryId(),category1.getCategoryName(),number);
            }
            System.out.println("===================================================");

        }
        }

    /**
     * Cập nhật dữ liệu cho category.
     * @param scanner
     */
    public static void updateCategory(Scanner scanner){
        if (categoryList.size()!=0){
            showListID_A_Z();
            System.out.println(GREEN2+"Nhập id cần cập nhập: "+ANSI_RESET);
            try {
                int categoryId = Integer.parseInt(scanner.nextLine());
                if (isExistCategory(categoryId)){
                    categoryList.stream()
                            .filter(category1 -> category1.getCategoryId()==categoryId)
                            .forEach(category1 ->{
                                System.out.printf(BACKGROUND_CYAN+"|\t%-10s |\t%-20s|\t%-20s|"+ANSI_RESET+"\n","Mã ID","Tên thể loại", "Trạng thái");
                                System.out.println("================================================================");
                                category1.output();
                                System.out.println(GREEN+"================================================================"+ANSI_RESET);
                                boolean isExit = false;
                                do {
                                    System.out.println("\n");
                                    System.out.println(YELLOW_UNDERLINED+"============= ỨNG DỤNG CẬP NHẬT ============="+ANSI_RESET);
                                    System.out.println("\t\t\t(1) Cập nhật tên ");
                                    System.out.println("\t\t\t(2) Cập nhật trạng thái ");
                                    System.out.println("\t\t\t(0) Thoát ");
                                    int choice = Integer.parseInt(scanner.nextLine());
                                    switch (choice){
                                        case 1:
                                            category1.setCategoryName(category1
                                                    .validateCategoryName(scanner,categoryList));
                                            System.out.println(YELLOW_UNDERLINED+"Đã cập nhật thành công!"+ANSI_RESET);
                                            break;
                                        case 2:
                                            category1.setCategoryStatus(category1.validateCategoryStatus(scanner));
                                            System.out.printf(BACKGROUND_CYAN+"|\t%-10s |\t%-20s|\t%-20s|"+ANSI_RESET+"\n","Mã ID","Tên thể loại", "Trạng thái");
                                            System.out.println("================================================================");
                                            category1.output();
                                            System.out.println(YELLOW_UNDERLINED+"Đã cập nhật thành công!"+ANSI_RESET);
                                            break;
                                        case 0:
                                            isExit = true;
                                            break;
                                        default:
                                            System.out.println(ANSI_RED+"Vui lòng chon 1-2. Chọn 0 để thoát"+ANSI_RESET);
                                            break;
                                    }

                                }while (!isExit);
                            });
                }else {
                    System.out.println(ANSI_RED+"Mã bạn nhập không tồn tại"+ANSI_RESET);
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(ANSI_RED+"Vui lòng chon 1-2. Chọn 0 để thoát"+ANSI_RESET);
            } catch (Exception e){
                System.out.println("Loi trong qua trinh cap nhap listCategory");
            }
        }else {
            System.out.println(ANSI_RED+"Chưa có thể loại sách nào"+ANSI_RESET);
        }
    }

    /**
     * Xóa 1 đối tượng category theo id
     * @param scanner
     */
    public static void deleteCategory(Scanner scanner){
       if (categoryList.size()!=0){
           showListID_A_Z();
           System.out.println(GREEN2+"Nhập mã id cần xóa."+ANSI_RESET);
           try {
               int deleteId = Integer.parseInt(scanner.nextLine());
               boolean isOnly = isExistCategory(deleteId);
               if (isOnly){
                   int count = 0;
                   for (Book book:bookList) {
                       if (book.getCategoryId()==deleteId){
                           count++;
                       }
                   }
                   if (count!=0){
                       System.out.println(ANSI_RED+"Thể loại này đang tồn tại sách. Không thể xóa."+ANSI_RESET);
                   }else {
                       boolean isDelete = categoryList.removeIf(category -> category.getCategoryId()==deleteId);
                       if (isDelete){
                           System.out.println(YELLOW_UNDERLINED+"Đã xóa thành công."+ANSI_RESET);
                       }else {
                           System.out.println(ANSI_RED+"Chưa xóa được."+ANSI_RESET);
                       }
                   }
               }else {
                   System.out.println(ANSI_RED+"Mã id không tồn tại."+ANSI_RESET);
               }
           } catch (NumberFormatException numberFormatException){
               System.out.println(ANSI_RED+"Vui lòng chọn để tiếp "+ANSI_RESET);
           }
           catch (Exception e){
               System.out.println("Loi trong qua trinh cap nhap listCategory");
           }
       }else {
           System.out.println(ANSI_RED+"Chưa có thể loại sách nào"+ANSI_RESET);
       }
    };

    public static boolean isExistCategory(int categoryId){
        return categoryList.stream().anyMatch(category -> category.getCategoryId()==categoryId);
    };

    // Quản lý Book
    public static void menuBook(){
        System.out.println("\n");
        System.out.println(GREEN+"************************** QUẢN LÝ SÁCH **************************"+ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t~~~~~~~~~~");
        System.out.printf("%-20s %s\n","","(1)\t Thêm mới sách ");
        System.out.printf("%-20s %s\n","","(2)\t Cập nhật thông tin sách");
        System.out.printf("%-20s %s\n","","(3)\t Xóa sách ");
        System.out.printf("%-20s %s\n","","(4)\t Tìm kiếm sách ");
        System.out.printf("%-20s %s\n","","(5)\t Hiển thị danh sách sách ");
        System.out.printf("%-20s %s\n","","(0)\t Quay lại");
        System.out.println("*******************************************************************");
    }
    public static void menuUpdate(){
        System.out.println("\n");
        System.out.println(GREEN+"*************************ỨNG DỤNG CẬP NHẬT***************************"+ANSI_RESET);
        System.out.printf("%-10s %-25s %s\n","","(1)\t Tiêu đề","(4)\t Năm xuất bản ");
        System.out.printf("%-10s %-25s %s\n","","(2)\t Tác giả","(5)\t Mô tả sách ");
        System.out.printf("%-10s %-25s %s\n","","(3)\t Nhà xuất bản","(6)\t Thể loại");
        System.out.printf("%-10s %-25s\n","","(0)\t Thoát ");
        System.out.println("*******************************************************************");
    }

    /**
     * Phương thức khởi chạy quản lí Book
     */
    public static void runMenuBook( String bookData){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        do {
            menuBook();
            try {
                byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice){
                    case 1:
                        addBook(scanner);
                        writeDataBookToFile(bookData);
                        showlistBookId(bookList);
                        break;
                    case 2:
                        updateBook(scanner);
                        writeDataBookToFile(bookData);
                        break;
                    case 3:
                        deleteBook(scanner);
                        writeDataBookToFile(bookData);
                        break;
                    case 4:
                        if (searchBook(scanner)){
                            messDone();
                        }else {
                            System.out.println(ANSI_RED+"Không tìm thấy yêu cầu của bạn."+ANSI_RESET);
                        }
                        break;
                    case 5:
                        showBookInfoByCategory();
                        break;
                    case 0:
                        isExit = true;
                        writeDataBookToFile(bookData);
                        break;
                    default:
                        System.out.println(ANSI_RED+"Yêu cầu của bạn không tồn tại"+ANSI_RESET);
                        break;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println(ANSI_RED+"Bạn vui lòng chọn chức năng tương ứng để quản lí");
                System.out.println("            ----------------------"+ANSI_RESET);
            } catch (Exception e){
                System.out.println("Lỗi trong quá trình chọn menu Book");
            }

        }while (!isExit);
    }

    /**
     * In danh sách các thể loại sách.
     * Lấy ra  categoryId trong đối tượng Book.
     * kiểm tra có trùng với với mã Id của Id Category hay không
     * @param scanner
     * @return
     */
    public static int getCategoryIdOfBook(Scanner scanner){
            System.out.println(GREEN2+"Chọn mã thể loại : "+ANSI_RESET);
            int categoryIdOfBook = Integer.parseInt(scanner.nextLine());
            return  isExistCategory(categoryIdOfBook)?categoryIdOfBook:-1;
    }

    /**
     * Thêm 1 đối tượng vào listbook
     * @param scanner
     */
    public static void addBook (Scanner scanner){
        showListID_A_Z();
        Book book = new Book();
       if (categoryList.size()!=0){
           do {
               try {
                   int categoryId = getCategoryIdOfBook(scanner);
                   if ( categoryId!= -1){
                       book.input(scanner, bookList);
                       book.setCategoryId(categoryId);
                       bookList.add(book);
                       messDone();
                       break;
                   }else {
                       System.out.println(ANSI_RED+"Ma ban chon khong ton tai. Vui long chon lai."+ANSI_RESET);
                   }
               } catch (NumberFormatException numberFormatException){
                   System.out.println(ANSI_RED+"Vui lòng chọn mã để tiếp tục"+ANSI_RESET);
               } catch (Exception e){
                   System.out.println("Lỗi khi thêm mới book");
               }

           }while (true);
       }else {
           book.input(scanner,bookList);
           book.setCategoryId(0);
           bookList.add(book);
           messDone();
       }
    }

    /**
     * In ra danh sách trong ListBook
     * @param bookList
     */
    public static void showlistBookId(List<Book>bookList){

        System.out.printf(BACKGROUND_CYAN+"|\t%-10s| \t%-15s| \t%-15s| \t%-10s| \t%-10s |\t%-9s|"+ANSI_RESET+"\n",
                "Book Id","Tiêu đề","Tác Giả","Nhà XB","Năm XB","Thể loại" );
        System.out.print("===================================================");
        System.out.println("===================================================");
      bookList.forEach(book ->{
      book.output();
              categoryList.forEach(category -> {
                  if (book.getCategoryId()==category.getCategoryId()){
                      System.out.printf("\t%-12s %s\n", category.getCategoryName(),"|");
                  }
              });
      });

    }

    /**
     * Cật nhật đối tượng Book theo Id.
     * @param scanner
     */
    public static void updateBook(Scanner scanner){
        if (bookList.size()!=0){
            showlistBookId(bookList);
            if (searchBook(scanner)){
                System.out.println(GREEN2+"Nhập Id:"+ANSI_RESET);
                String bookIdUpdate = scanner.nextLine();
                if (isExistBook(bookIdUpdate)){
                    bookList.stream()
                            .filter(book -> book.getBookId()
                                    .equalsIgnoreCase(bookIdUpdate))
                            .forEach(book -> {
                                boolean isExit = false;
                                try {
                                    do {
                                        System.out.printf(BACKGROUND_CYAN+"|\t%-10s| \t%-15s| \t%-15s| \t%-10s| \t%-10s |\t%-9s|"+ANSI_RESET+"\n",
                                                "Book Id","Tiêu đề","Tác Giả","Nhà XB","Năm XB","Thể loại" );
                                        System.out.print("=================================================");
                                        System.out.println("======================================================");
                                        book.output();
                                        categoryList.forEach(category -> {
                                            if (book.getCategoryId()==category.getCategoryId()){
                                                System.out.printf("\t%-20s\n", category.getCategoryName());
                                            }
                                        });
                                        menuUpdate();
                                        byte choice = Byte.parseByte(scanner.nextLine());
                                        switch (choice){
                                            case 1:
                                                book.setTitle(book.validateBookTitle(scanner,bookList));
                                                messDone();
                                                break;
                                            case 2:
                                                book.setAuthor(book.validateAuthor(scanner));
                                                messDone();
                                                break;
                                            case 3:
                                                book.setPublisher(book.validatePublisher(scanner));
                                                messDone();
                                                break;
                                            case 4:
                                                book.setYear(book.validateYear(scanner));
                                                messDone();
                                                break;
                                            case 5:
                                                book.setDescription(book.validateDescription(scanner));
                                                break;
                                            case 6:
                                                if (categoryList.size()!=0){
                                                    do {
                                                        showListID_A_Z();
                                                        int categoryId =getCategoryIdOfBook(scanner);
                                                        if (categoryId != -1){
                                                            book.setCategoryId(categoryId);
                                                            messDone();
                                                            break;
                                                        }else {
                                                            System.out.println(ANSI_RED+"Mã bạn chọn không tồn tại."+ANSI_RESET);
                                                        }

                                                    }while (true);
                                                }else {
                                                    System.out.println(ANSI_RED+"Chưa có thể loại nào được tạo."+ANSI_RESET);
                                                }

                                                break;
                                            case 0:
                                                isExit = true;
                                                break;
                                            default:
                                                System.out.println(ANSI_RED+"Yêu cầu bạn chọn không có!"+ANSI_RESET);
                                                break;
                                        }
                                    }while (!isExit);
                                } catch (NumberFormatException numberFormatException){
                                    System.out.println(ANSI_RED+"Vui lòng nhập số nguyên!"+ANSI_RESET);
                                }
                                catch (Exception e){
                                    System.out.println(ANSI_RED+"Lỗi Update Book"+ANSI_RESET);
                                }
                            });
                }else {
                    System.out.println(ANSI_RED+"Mã id không tồn tại."+ANSI_RESET);
                }
            }else {
                System.out.println(ANSI_RED+"Không tìm thấy yêu cầu của bạn."+ANSI_RESET);
            }

        }else {
            System.out.println(ANSI_RED+"Chua co du lieu. Vui long them moi"+ANSI_RESET);
        }


    }

    /**
     * Xóa 1 đối tượng Book khỏi danh sách
     * @param scanner
     */
    public static void deleteBook(Scanner scanner){
        if (bookList.size()!=0){
            showlistBookId(bookList);
            System.out.println(GREEN2+"Nhập Mã cần xóa"+ANSI_RESET);
            String bookIdDelete = scanner.nextLine() ;
            if (isExistBook(bookIdDelete)){
                for ( Book book :bookList) {
                    if ( book.getBookId().equalsIgnoreCase(bookIdDelete)){
                        bookList.remove(book);
                        System.out.println(YELLOW_UNDERLINED+"Đã Xóa xong"+ANSI_RESET);
                    }
                }
            }else {
                System.out.println(ANSI_RED+"Mã không tồn tại"+ANSI_RESET);
            }
        }else {
            System.out.println(ANSI_RED+"Chua co du lieu. Vui long them moi"+ANSI_RESET);
        }

    }

    /**
     * Tìm đối tượng book theo dữ liệu người dùng nhập vào.
     * Đấy là cách làm cũ. em vừa nghĩ vừa code. nên hơi dài.
     * @param scanner
     */
    public static boolean searchBook(Scanner scanner){
        boolean isBook = false;
        if (bookList.size()>0){
            System.out.println(GREEN2+"Nhập từ khóa tìm kiếm"+ANSI_RESET);
            String searchName = scanner.nextLine();
            Set<Book> listSetBook = new HashSet<>();
            byte count = 0;
            for (Book book: bookList) {
                if (book.getTitle().toLowerCase().contains(searchName.toLowerCase())){
                    listSetBook.add(book);
                    count++;
                }
                if (book.getAuthor().toLowerCase().contains(searchName.toLowerCase())){
                    listSetBook.add(book);
                    count++;
                }
                if (book.getPublisher().toLowerCase().contains(searchName.toLowerCase())){
                    listSetBook.add(book);
                    count++;
                }
            }
            if (count==0){
                isBook =  false;
            }else {
                List<Book> coverListBook = new ArrayList<>(listSetBook);
                showlistBookId(coverListBook);
                isBook =  true;
            }
        }else {
            System.out.println(ANSI_RED+"Hiện tại chưa có cuốn sách nào"+ANSI_RESET);
        }
        return isBook;
    }

    /**
     * Tìm đối tượng book theo dữ liệu người dùng nhập vào.
     * Đây là cách làm của Thầy Quang. em có làm theo để cải thiện code.
     * @param scanner
     */
    public static void searchBook2(Scanner scanner){
        System.out.println(GREEN+"Nhập từ khóa tìm kiếm"+ANSI_RESET);
        String searchName = scanner.nextLine();
        bookList.stream().filter(book -> book.getTitle().contains(searchName)||
                        book.getAuthor().contains(searchName)||
                        book.getPublisher().contains(searchName))
                .forEach(book ->{

                            System.out.printf(BACKGROUND_CYAN
                                            +"|\t%-10s| \t%-15s| \t%-15s| \t%-15s| \t%-10s |"
                                            +ANSI_RESET+"\n",
                                    "Book Id","Tiêu đề","Tác Giả","Nhà XB","Năm XB");
                            System.out.print("=============================================");
                            System.out.println("===============================================");
                            book.output();
                            System.out.print("=============================================");
                            System.out.println("===============================================");
                        });
    }

    /**
     * Hiển thị danh sách Sách thep nhóm thể Loại.
     */
    public static void showBookInfoByCategory(){
        if (categoryList.size()!=0){
            System.out.printf(BACKGROUND_CYAN+"|%-20s %-20s %-20s|"+ANSI_RESET+"\n",PACE,"Tiêu đề","Tác Giả ");
            System.out.println("|---------------------------------------------------------------|");
            for (Category category :categoryList) {
                System.out.printf("* %s\n", category.getCategoryName().toUpperCase());
                for (Book book :bookList) {
                    if (book.getCategoryId()==category.getCategoryId()){
                        book.outputNameAndAuthor();
                    }
                }
                System.out.println("|===============================================================|");
            }
        }else {
            System.out.println(ANSI_RED+"Hiện tại chưa có dữ liệu"+ANSI_RESET);
            if (bookList.size()!=0){
                System.out.println("|===============================================================|");
                System.out.printf(BACKGROUND_CYAN+"|%-20s %-20s %-20s|"+ANSI_RESET+"\n",PACE,"Tiêu đề","Tác Giả ");
                System.out.println("|---------------------------------------------------------------|");
                bookList.forEach(book ->
                    book.outputNameAndAuthor()
                );
            }
        }

    }
    public static void messDone(){
        System.out.println(YELLOW_UNDERLINED+"Thành công!"+ANSI_RESET);

    }

    /**
     * Kiểm tra dữ kiệu đầu vào khi Cập nhập book theo Id.
     * Cách này em tự nghĩ ra. Nhưng không phù hợp cho lắm.
     * Để lần sau e cải thiện lại.
     * @param bookList
     * @return
     */
    public static String validateBookId(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nhập Id:");
            String bookIdUpdate = scanner.nextLine();
            byte isError = 0;
            if (isStrNull(bookIdUpdate)) {
                System.out.println("Không được để trống.");
                isError++;
            }
            if (!isExistBook(bookIdUpdate)) {
                System.out.println("Mã Không tồn tại");
                isError++;
            }
            if (isError == 0) {
                return bookIdUpdate;
            }

        } while (true);
    };

    /**
     * Kiểm tra null và rỗng của chuỗi người dùng nhập vào
     * @param str
     * @return
     */
    public static boolean isStrNull(String str) {
        return str==null || str.isEmpty();
    };

    /**
     * Kiểm tra Sự tồn tại của đối tượng Book trong listBook
     * @param strId
     * @return
     */
    public static boolean isExistBook(String strId) {
        return bookList.stream()
                .anyMatch(book -> book.getBookId()
                        .equalsIgnoreCase(strId));
    };
    // Đọc ghi file

    /**
     * Ghi dữ liệu đối tượng Book vào file
     */
    public static void writeDataBookToFile(String str) {
        File file = new File(str);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Lỗi runtime");
        } catch (IOException e) {
            System.err.println("Lỗi IO");
        } catch (Exception e){
            System.err.println("Lỗi trong qúa trình ghi file");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                };
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.err.println("Lỗi runtime");
            } catch (Exception e){
                System.err.println("Lỗi Trong quá trình đóng file");
            }
        }
    };

    /**
     * Đọc dữ liệu đối tượng Book từ file
     */
    public static void readDataBook(String str) {
        File file = new File(str);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            bookList = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex1) {
            System.out.println(ANSI_RED+"Hiện tại chưa có Thể loại nào. Vui lòng thêm mới"+ANSI_RESET);
        } catch (IOException ex2) {
            System.out.println(ANSI_RED+"Hiện chưa có dữ liệu "+ANSI_RESET);
        } catch (Exception ex3) {
            System.out.println("Loi trong qua tring doc file book");
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
        }
    };

// input data file

    /**
     * Ghi dữ liệu đối tượng Category vào file
     */
    public static void writeDataToFile(String str) {
        File file = new File(str);
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
        } catch (Exception e){
            System.err.println("Lỗi trong qúa trình ghi file");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                };
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.err.println("Lỗi runtime");
            } catch (Exception e){
                System.err.println("Lỗi Trong quá trình đóng file");
            }
        }
    };

    /**
     * Đọc dữ liệu đối tượng từ file
     */
    public static void readDataCategory(String str) {
        File file = new File(str);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            categoryList = (List<Category>) ois.readObject();
        } catch (FileNotFoundException ex1) {
            System.out.println(ANSI_RED+"Category đang trống."+ANSI_RESET);
        } catch (IOException ex2) {
            System.out.println(ANSI_RED+"Hiện chưa có dữ liệu Category."+ANSI_RESET);
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
        }
    };
}
