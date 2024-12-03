# Student Management System

## Giới thiệu

**Student Management System** là một ứng dụng web quản lý sinh viên được phát triển bằng **Spring Boot**, **Maven**, và **SQL**. Hệ thống hỗ trợ các chức năng quản lý dành cho Admin để quản lí sinh viên, học sinh của các trường học

## Tính năng

### Tính năng chính:
- Quản lý thông tin sinh viên.
- Quản lý lớp học, khóa học, và giáo viên.
- Chỉnh sửa, lưu trữ thông tin
- Tra cứu thông tin
- Thống kê và báo cáo.

## Công nghệ sử dụng
- **Backend**: Spring Boot 3.3.4, Java 21.
- **Frontend**: HTML, CSS, JavaScript, Thymeleaf.
- **Cơ sở dữ liệu**: MySQL.
- **Quản lý dự án**: Maven 3.9.9.

## Yêu cầu hệ thống
- **Java**: Phiên bản 21 trở lên.
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



**Cảm ơn bạn đã sử dụng Student Management System!**
