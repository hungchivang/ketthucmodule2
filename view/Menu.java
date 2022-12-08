package view;

import controller.ManageStudent;
import java.io.Serializable;


public class Menu implements Serializable {

    public void menuStudent() {

        ManageStudent manageStudent = new ManageStudent();


        int choice;
        while (true) {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách sinh viên");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng :");
            choice = manageStudent.validateInt();

            switch (choice) {
                case 1:
                    manageStudent.show();
                    break;
                case 2:
                    manageStudent.add();
                    break;
                case 3:
                    manageStudent.edit();
                    break;
                case 4:
                    manageStudent.delete();
                    break;
                case 5:
                    int choice1 = -1;
                    while (choice1 != 3) {
                        System.out.println("----Sắp xếp sinh viên theo điểm trung bình----");
                        System.out.println("Chọn chức năng theo số (để tiếp tục)");
                        System.out.println("1. Sắp xếp điểm truung bình tăng dần");
                        System.out.println("2. Sắp xếp điểm truung bình giảm dần");
                        System.out.println("3. Thoát");
                        choice1 = manageStudent.validateInt();
                        switch (choice1) {
                            case 1:
                                manageStudent.compareByAverageUp();
                                break;
                            case 2:
                                manageStudent.compareByAverageDown();
                                break;
                            case 3:
                                break;
                        }
                    }
                    break;
                case 6:
                    manageStudent.readFile();
                    break;
                case 7:
                    manageStudent.WriteToFile();
                    break;
                case 8:
                   System.exit(0);
            }
        }
    }

}
