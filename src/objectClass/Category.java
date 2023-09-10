/**
 * Lớp đối tượng thể loại sách.
 * Quản lý các thông tin về các thể loại sách.
 * Người tạo:  Vũ Anh Tuấn.
 */
package objectClass;
import interFace.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity<Category>, Serializable {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[35m";
    private static final String GREEN2 = "\u001B[36m";

    //1 Attributes.
    private int categoryId;
    private String categoryName;
    private boolean categoryStatus;

    //2 Get, set

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    //3. Constructor
    public Category() {
    }

    public Category(int categoryId, String categoryName, boolean categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    //4. input, output.

    /**
     * Nhập dữ liệu đầu vào cho thể loại sách
     * @param scanner
     * @param arrayList
     */
    @Override
    public void input(Scanner scanner, List<Category> arrayList) {
        this.categoryId = validateCategoryId(scanner,arrayList);
        this.categoryName = validateCategoryName(scanner,arrayList);
        this.categoryStatus = validateCategoryStatus(scanner);
    }

    /**
     * Đầu ra output cho đối tượng Category
     */
    @Override
    public void output() {
        String statusStr = this.categoryStatus?GREEN+"Hoạt động"+ANSI_RESET:ANSI_RED+"Không hoạt động"+ANSI_RESET;
        System.out.printf(GREEN+"|\t%04d \t\t|"+ANSI_RESET+"\t%-20.15s| \t%-25s\t|\n",this.categoryId,this.categoryName,statusStr);
    }
    //5. Business methods

    /**
     * Kiểm tra dữ liệu đầu vào id của Thể loại sách
     * @param scanner
     * @param arrayList
     * @return
     */
    public int validateCategoryId(Scanner scanner, List<Category> arrayList) {
        boolean isExit = false;
        do {
            System.out.println(GREEN2+"Nhập mã thể loại sách: "+ANSI_RESET);
            String categoryIdStr = scanner.nextLine();
            if (!isStrNull(categoryIdStr)) {
                try {
                    int categoryId = Integer.parseInt(categoryIdStr);
                    boolean isCategoryId = arrayList.stream().anyMatch(category -> category.getCategoryId() == categoryId);
                    if (isCategoryId) {
                        System.out.println(ANSI_RED+"Mã đã tồn tại"+ANSI_RESET);
                    } else {
                        isExit = true;
                        return categoryId;
                    }
                } catch (NumberFormatException numberFormatException) {
                    System.out.println(ANSI_RED+"Mã có định dạng là số nguyên. Vui lòng nhập lại "+ANSI_RESET);
                } catch (Exception e) {
                    System.out.println("Lỗi xảy ra trong quá trình Nhập mã thể loại");
                }

            } else {
                System.out.println(ANSI_RED+"Vui lòng không để trống."+ANSI_RESET);
            }
        } while (!isExit);
        return -1;
    };

    /**
     * Kiểm tra dữ liệu đầu vào tên của Thể loại sách
     * @param scanner
     * @param arrayList
     * @return
     */
    public String validateCategoryName(Scanner scanner, List<Category> arrayList) {
        boolean isExit = false;
        do {
            System.out.println(GREEN2+"Nhập tên thể loại:"+ANSI_RESET);
            String categoryNameStr = scanner.nextLine();
            if (!isStrNull(categoryNameStr)) {
                try {
                    byte isError = 0;
                    boolean isCategoryId = arrayList.stream().anyMatch(category -> category.getCategoryName().equalsIgnoreCase(categoryNameStr));
                    if (isCategoryId) {
                        System.out.println(ANSI_RED+"Tên đã tồn tại"+ANSI_RESET);
                        isError ++;
                    }
                    if (isStrNull(categoryNameStr)){
                        System.out.println(ANSI_RED+"Không được để trống."+ANSI_RESET);
                        isError ++;
                    }
                    if (categoryNameStr.length()<6 || categoryNameStr.length()>30){
                        System.out.println(ANSI_RED+"Vui lòng nhập từ 6-30 kí tự."+ANSI_RESET);
                        isError ++;
                    }
                    if (isError == 0){
                        isExit = true;
                        return categoryNameStr;
                    }
                } catch (NullPointerException nullPointerException) {
                    System.out.println(ANSI_RED+"Chưa có dữ liệu. Vui lòng thêm dữ liệu"+ ANSI_RESET);
                } catch (Exception e) {
                    System.out.println("Lỗi xảy ra trong quá trình Nhập tên thể loại");
                }
            } else {
                System.out.println(ANSI_RED+"Vui lòng không để trống."+ANSI_RESET);
            }
        } while (!isExit);
        return null;
    };

    /**
     * Kiểm tra dữ liệu đầu vào Trạng thái hoạt động của Thể loại sách
     * @param scanner
     * @return
     */
    public boolean validateCategoryStatus(Scanner scanner) {
        System.out.println(GREEN2+"Nhập trạng thái thể loại sách (true, false)"+ANSI_RESET);
        do {
            String str = scanner.nextLine();
            if (!isStrNull(str)) {
                if (str.trim().equalsIgnoreCase("true") || str.trim().equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(str);
                } else {
                    System.out.println(ANSI_RED+"Vui lòng nhập kí tự: true hoặc false"+ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED+"Vui Lòng Không Để Trống"+ANSI_RESET);
            }
        } while (true);
    };

    /**
     * Kiểm tra dữ liệu người dùng nhập vào có null hay
     * @param str
     * @return
     */
    public boolean isStrNull(String str) {
        return str==null || str.isEmpty();
    };



}
