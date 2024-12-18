# Student Management System

## Giới thiệu

**Student Management System** là một ứng dụng web quản lý sinh viên được phát triển bằng **Spring Boot**, **Maven**, và **SQL**. Hệ thống hỗ trợ các chức năng quản lý dành cho Admin để quản lí sinh viên, học sinh của các trường học

---

## Cách truy cập
### Lưu ý: Khi đăng nhập trên 1 trình duyệt, nếu lỡ may tắt tab web đi mà lại bật ngay tab khác để truy cập link thì sẽ bị kẹt ở trang trắng, cho nên đã truy cập thì không tắt tab web đi, hoặc nếu lỡ tắt đi thì phải tắt trình duyệt mình đang dùng rồi mở lại rồi mới truy cập web. 
- B1: Nhấn link [productive-spider-nhatminh-03161447.koyeb.app](https://productive-spider-nhatminh-03161447.koyeb.app/)
- B2: Chú ý trước khi đăng nhập: Khi đăng nhập,có thể bạn sẽ bị lỗi và được dẫn đến 1 trang trắng như dưới đây (có thể thôi chứ thường thì không lỗi đâu)
  ![image](https://github.com/user-attachments/assets/9f59380f-8cb6-461c-b359-ad198bae907c)
  Lúc này hãy ấn nút quay lại để trở lại trang đăng nhập và đăng nhập lại sẽ vào được web.
- B3: Đăng nhập với <span style="font-size:35px;">**tài khoản: nhatminh, mật khẩu 1234**</span>.
  
  Do kinh phí thấp + đặt máy chủ ở nước ngoài, đường truyền có thể không ổn định có thể gây giạt lag nên khi thực hiện các chức năng (ví dụ như search hay chuyển từ trang này qua trang khác) vui lòng chờ trong giây lát để có kết quả
  
---

## Tính năng

### Tính năng chính:
- Quản lý thông tin sinh viên.
- Quản lý lớp học, khóa học, và giáo viên.
- Chỉnh sửa, lưu trữ thông tin
- Tra cứu thông tin
- Thống kê và báo cáo.
### Chi tiết các chức năng:
#### 1. Dashboard
- Thống kê tổng số:
  - Sinh viên
  - Khóa học
  - Giáo viên
  - Lớp học

---

#### 2. Courses
- **Add Course**: Thêm một khóa học mới.
- **Export to PDF**: Xuất danh sách khóa học ra file PDF.
- **Search Course**: Tìm kiếm khóa học theo Course Name hoặc Course Code hoặc Credits hoặc Major hoặc Teacher.
- **Update**: Cập nhật thông tin khóa học.
- **Delete**: Xóa một khóa học khỏi danh sách.

---

#### 3. Students
- **Add Student**: Thêm một sinh viên mới.
- **Export to PDF**: Xuất danh sách sinh viên ra file PDF.
- **Search Student**: Tìm kiếm sinh viên theo ID hoặc theo First Name , hoặc Last Name hoặc Class  hoặc theo Major.
- **Update**: Cập nhật thông tin sinh viên.
- **Delete**: Xóa một sinh viên khỏi danh sách.
- **Sort**: Sắp xếp sinh viên theo GPA tăng dần.

---
#### 4. Course Schedule
- **Add Course Schedule**: Thêm một lịch khóa học.
- **Export to PDF**: Xuất danh sách lịch khóa học ra file PDF.
- **Search Course Schedule**: Tìm kiếm lịch khóa học theo Course hoặc Classroom hoặc Day of week.
- **Update**: Cập nhật thông tin lịch khóa học.
- **Delete**: Xóa lịch khóa học.
- **Sort**: Sắp xếp theo Classroom tăng dần.

---

#### 5. Enrollment
- **Add Enrollment**: Thêm một sự đăng ký mới.
- **Export to PDF**: Xuất danh sách đăng ký ra file PDF.
- **Search Enrollment**: Tìm kiếm sự đăng ký theo Student ID  , hoặc theo Student Name, hoặc theo Course Name
- **Update**: Cập nhật thông tin đăng ký.
- **Delete**: Xóa một đăng ký.

---

#### 6. Major
- **Add Major**: Thêm một chuyên ngành mới.
- **Export to PDF**: Xuất danh sách chuyên ngành ra file PDF.
- **Search Major**: Tìm kiếm chuyên ngành theo Major Name hoặc theo Description hoặc theo Department
- **Update**: Cập nhật thông tin chuyên ngành.
- **Delete**: Xóa một chuyên ngành.

---
#### 7. Scholarship
- **Add Scholarship**: Thêm một học bổng mới.
- **Export to PDF**: Xuất danh sách học bổng ra file PDF.
- **Search Scholarship**: Tìm kiếm học bổng theo Scholarship Name hoặc theo Amount hoặc theo Student.
- **Update**: Cập nhật thông tin học bổng.
- **Delete**: Xóa một học bổng.
- **Sort**: Sắp xếp danh sách học bổng theo Amount tăng dần.

---

#### 8. Department
- **Add Department**: Thêm một phòng ban mới.
- **Export to PDF**: Xuất danh sách phòng ban ra file PDF.
- **Search Department**: Tìm kiếm phòng ban theo Department Name.
- **Update**: Cập nhật thông tin phòng ban.
- **Delete**: Xóa một phòng ban.

---

#### 9. Teachers
- **Add Teacher**: Thêm một giáo viên mới.
- **Export to PDF**: Xuất danh sách giáo viên ra file PDF.
- **Search Teacher**: Tìm kiếm giáo viên theo First Name hoặc theo Last Name hoặc theo Emai hoặc theo Phone Number hoặc theo Department  .
- **Update**: Cập nhật thông tin giáo viên.
- **Delete**: Xóa một giáo viên.

---
#### 10. Classes
- **Add Classes**: Thêm một lớp học mới.
- **Export to PDF**: Xuất danh sách lớp học ra file PDF.
- **Search Classes**: Tìm kiếm lớp học theo Class Name hoặc theo Class Description.
- **Update**: Cập nhật thông tin lớp học.
- **Delete**: Xóa một lớp học.

---

#### 11. Logout
- **Log Out**: Nút log out được đặt phía góc phải màn hình, giúp người dùng đăng xuất khỏi tài khoản.

---

## Công nghệ sử dụng
- **Backend**: Spring Boot 3.3.4, Java 21.
- **Frontend**: Java, HTML, CSS, JavaScript, Thymeleaf.
- **Cơ sở dữ liệu**: PostgreSQL hoặc MySQL
- **Quản lý dự án**: Maven 3.9.9.

## Yêu cầu hệ thống
- **Java**: Phiên bản 17 trở lên.
- **Maven**: Phiên bản 3.9.9 trở lên.
- **MySQL**: Phiên bản 8.0 hoặc cao hơn.

## Cách chạy dự án

1. **Clone dự án**:
   ```bash
   git clone https://github.com/your-username/student-management-system.git
## Cấu hình cơ sở dữ liệu

1. **Tạo cơ sở dữ liệu MySQL**:
   - Mở MySQL CLI hoặc công cụ quản lý như MySQL Workbench và tạo cơ sở dữ liệu mới:
     ```sql
     CREATE DATABASE web;
     ```

2. **Cập nhật thông tin kết nối**:
   - Mở file `src/main/resources/application.properties` và cập nhật thông tin kết nối cơ sở dữ liệu phù hợp:
     ```properties
     # Cấu hình kết nối cơ sở dữ liệu
     spring.datasource.url=jdbc:mysql://localhost:3306/web
     spring.datasource.username=your_mysql_username
     spring.datasource.password=your_mysql_password

     # Hibernate cấu hình tự động cập nhật schema
     spring.jpa.hibernate.ddl-auto=update

     # Tùy chọn logging
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     ```

3. **Kiểm tra kết nối**:
   - Đảm bảo MySQL đang chạy và thông tin kết nối được điền chính xác. 
   - Nếu cần, bạn có thể kiểm tra bằng cách sử dụng các công cụ MySQL hoặc thử kết nối qua dòng lệnh:
     ```bash
     mysql -u your_mysql_username -p
     ```

4. **Tạo bảng tự động**:
   - Khi chạy ứng dụng lần đầu, Hibernate sẽ tự động tạo các bảng dựa trên các entity được định nghĩa trong dự án.

---

## Cách chạy dự án

1. **Chạy ứng dụng bằng Maven**:
   - Mở terminal và chạy lệnh:
     ```bash
     mvn spring-boot:run
     ```

2. **Chạy ứng dụng bằng JAR file**:
   - Đầu tiên, build ứng dụng:
     ```bash
     mvn clean install
     ```
   - Sau đó chạy file JAR:
     ```bash
     java -jar target/student-management-system-0.0.1-SNAPSHOT.jar
     ```

3. **Truy cập ứng dụng**:
   - Mở trình duyệt và truy cập: [http://localhost:8080](http://localhost:8080).

---
## Database
![image](https://github.com/user-attachments/assets/fa4e1680-5d34-4042-b4f3-d12c239f124c)




**Cảm ơn bạn đã sử dụng Student Management System!**
