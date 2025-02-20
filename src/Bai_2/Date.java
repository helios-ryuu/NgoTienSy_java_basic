package Bai_2;

import java.util.Scanner;

public class Date {
    private int day, month, year;

    // Mảng số ngày trong từng tháng (không tính năm nhuận)
    private static final int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    // Constructor: Nhập và kiểm tra ngày hợp lệ
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Ngày không hợp lệ!");
            this.day = this.month = this.year = -1; // Đánh dấu ngày không hợp lệ
        }
    }

    // Kiểm tra năm nhuận
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Kiểm tra ngày hợp lệ
    private static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1) return false;
        int maxDays = daysInMonth[month - 1];

        // Nếu là tháng 2 và năm nhuận thì có 29 ngày
        if (month == 2 && isLeapYear(year)) {
            maxDays = 29;
        }

        return day <= maxDays;
    }

    // Xuất ngày theo định dạng "yyyy-MM-dd" hoặc "MM-dd-yyyy"
    public void hienThiNgay(String format) {
        if (day == -1) {
            System.out.println("Không có ngày hợp lệ để hiển thị!");
            return;
        }

        String formattedDate = format
                .replace("yyyy", String.format("%04d", year))
                .replace("MM", String.format("%02d", month))
                .replace("dd", String.format("%02d", day));

        System.out.println("Ngày theo định dạng " + format + ": " + formattedDate);
    }

    // Phương thức cộng số ngày nhập từ bàn phím
    public void congNgay(int soNgay) {
        if (day == -1) {
            System.out.println("Không thể cộng ngày do ngày không hợp lệ!");
            return;
        }

        while (soNgay > 0) {
            int maxDays = daysInMonth[month - 1];

            // Xử lý tháng 2 của năm nhuận
            if (month == 2 && isLeapYear(year)) {
                maxDays = 29;
            }

            if (day + soNgay > maxDays) {
                soNgay -= (maxDays - day + 1);
                day = 1;
                month++;

                if (month > 12) {
                    month = 1;
                    year++;
                }
            } else {
                day += soNgay;
                soNgay = 0;
            }
        }

        System.out.println("Ngày sau khi cộng: " + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập ngày, tháng, năm từ bàn phím
        System.out.print("Nhập ngày: ");
        int day = scanner.nextInt();
        System.out.print("Nhập tháng: ");
        int month = scanner.nextInt();
        System.out.print("Nhập năm: ");
        int year = scanner.nextInt();

        Date myDate = new Date(day, month, year);

        // Hiển thị ngày với các định dạng
        myDate.hienThiNgay("yyyy-MM-dd");
        myDate.hienThiNgay("MM-dd-yyyy");

        // Nhập số ngày cần cộng
        System.out.print("Nhập số ngày muốn cộng: ");
        int soNgay = scanner.nextInt();
        myDate.congNgay(soNgay);

        scanner.close();
    }
}