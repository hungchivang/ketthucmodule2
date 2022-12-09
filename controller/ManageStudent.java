package controller;

import ReadAndWrite.ReadAndWrite;
import compare.CompareByAveragePoint;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ManageStudent {

    Scanner scanner = new Scanner(System.in);
    List<Student> students = new ArrayList<>();

    ReadAndWrite readAndWrite = new ReadAndWrite();

    public void show() {
        System.out.println("-----------------------------------------------Danh sách học sinh-----------------------------------------------");
        System.out.printf("| %-15s| %-25s| %-15s| %-15s| %-15s| %-15s", "Mã học sinh", "Tên", "Tuổi", "Giới tính", "Địa chỉ", "Điểm trung bình");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("| %-15s| %-25s| %-15s| %-15s| %-15s| %-15s", student.getMaSV(), student.getName(), student.getAge(), student.getGender(), student.getAddress(), student.getAveragePoint());
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
    }

    public void add() {
        students.add(createStudent());
    }

    public Student createStudent() {
        System.out.println("Nhập mã sinh viên");
        String maSV = scanner.nextLine();
        System.out.println("Nhập tên");
        String name = scanner.nextLine();
        System.out.println("Nhập tuổi");
        int age = validateAge();
        System.out.println("Nhập giới tính");
        String gender = validateGender();
        System.out.println("Nhập địa chỉ");
        String address = scanner.nextLine();
        System.out.println("Nhập số điểm trung bình");
        double averagePoint = validateDouble();

        return new Student(maSV, name, age, gender, address, averagePoint);
    }

    public void edit() {
        int index = checkMaSV();
        if (index >= 0) {
            while (true) {
                System.out.println("Thông tin muốn sửa :");
                System.out.println("1. Mã sinh viên\n" +
                        "2. Họ Tên\n" +
                        "3. Tuổi\n" +
                        "4. Giới tính\n" +
                        "5. Địa chỉ\n" +
                        "6. Điểm trung bình\n" +
                        "7. OK");
                System.out.println("Chon chức năng");
                int choice = validateInt();
                switch (choice) {
                    case 1:
                        System.out.println("Sửa mã sinh viên");
                        String maSV = scanner.nextLine();
                        students.set(index, new Student(maSV, students.get(index).getName(), students.get(index).getAge(), students.get(index).getGender(), students.get(index).getAddress(), students.get(index).getAveragePoint()));
                        break;
                    case 3:
                        System.out.println("Sửa tuổi sinh viên");
                        int age = validateInt();
                        students.set(index, new Student(students.get(index).getMaSV(), students.get(index).getName(), age, students.get(index).getGender(), students.get(index).getAddress(), students.get(index).getAveragePoint()));
                        break;
                    case 2:
                        System.out.println("Sửa họ tên");
                        String name = scanner.nextLine();
                        students.set(index, new Student(students.get(index).getMaSV(), name, students.get(index).getAge(), students.get(index).getGender(), students.get(index).getAddress(), students.get(index).getAveragePoint()));
                        break;
                    case 4:
                        System.out.println("Sửa giới tính");
                        String gender = validateGender();
                        students.set(index, new Student(students.get(index).getMaSV(), students.get(index).getName(), students.get(index).getAge(), gender, students.get(index).getAddress(), students.get(index).getAveragePoint()));
                        break;
                    case 5:
                        System.out.println("Sửa địa chỉ");
                        String address = scanner.nextLine();
                        students.set(index, new Student(students.get(index).getMaSV(), students.get(index).getName(), students.get(index).getAge(), students.get(index).getGender(), address, students.get(index).getAveragePoint()));
                        break;
                    case 6:
                        System.out.println("Sửa điểm trung bình");
                        double averagePoint = validateDouble();
                        students.set(index, new Student(students.get(index).getMaSV(), students.get(index).getName(), students.get(index).getAge(), students.get(index).getGender(), students.get(index).getAddress(), averagePoint));
                        break;
                    case 7:
                        return;
                }
                System.out.println("Đã sửa thông tin sinh viên");
            }
        } else {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên");
        }
    }

    public int checkMaSV() {
        System.out.println("Nhập mã sinh viên :");
        String maSV = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMaSV().equals(maSV)) return i;
        }
        return -1;
    }

    public void delete() {
        int index = checkMaSV();
        if (index >= 0) {
            System.out.println("Xác nhận muốn xoá thông tin sinh viên");
            System.out.println("Y : Đồng ý");
            System.out.println("kí tự bất kì : thoát");
            String character = scanner.nextLine();
            if (character.equals("Y")) {
                students.remove(index);
                System.out.println("Đã xóa");
            }
        } else {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên");
        }
    }

    public void compareByAverageUp() {
        students.sort(new CompareByAveragePoint());
        for (Student student : students) {
            System.out.println(student.toString());
        }
        show();
    }

    public void compareByAverageDown() {
        students.sort(new CompareByAveragePoint().reversed());
        for (Student student : students) {
            System.out.println(student.toString());
        }
        show();
    }


    public void readFile() {
        System.out.println("Đọc từ File sẽ mất dữ liệu hiện tại, bạn có muốn tiếp tục? (yes:y hoặc kí tự bất kì): ");
        String choice = scanner.nextLine();
        if (choice.equals("y")) {
            students = readAndWrite.read();
            System.out.println("Đọc file OK");
        }
    }


    public void WriteToFile() {
        System.out.println("Ghi vào File sẽ mất dữ liệu đang lưu, bạn có muốn tiếp tục? (yes:y hoặc kí tự bất kì): ");
        String choice = scanner.nextLine();
        if (choice.equals("y")) {
            readAndWrite.write((ArrayList<Student>) students);
            System.out.println("Ghi file OK");
        }
    }

    public int validateInt() {
        try {
            int number = parseInt(scanner.nextLine());
            if (number >= 0) return number;
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Phải nhập số nguyên ( >= 0)");
            return validateInt();
        }
    }

    public double validateDouble() {
        try {
            double number = Double.parseDouble(scanner.nextLine());
            if (number >= 0 && number <= 10) return (int) number;
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Phai nhap so ( >= 0)");
            return validateDouble();
        }
    }

    public int validateAge() {
        try {
            int age = Integer.parseInt(scanner.nextLine());
            if (age >= 18 && age <= 65) return age;
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Phải nhập độ tuổi từ ( 18 -> 65 )");
            return validateAge();
        }
    }

    public String validateGender() {
        try {
            String gender = scanner.nextLine();
            if (Objects.equals(gender, "nam") || Objects.equals(gender, "nu")) return gender;
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Phải nhập : nam hoặc nu");
            return validateGender();
        }
    }
}
