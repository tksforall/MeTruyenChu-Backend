package com.example.mtc.service;

import com.example.mtc.dto.RegisterRequest;
import com.example.mtc.entity.User;
import com.example.mtc.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    // ======================================================
    // ĐĂNG KÝ
    // ======================================================

    public User register(RegisterRequest request) {

        User user = new User();

        user.setUserName(
                request.getUserName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPasswordHash(
                request.getPassword()
        );

        user.setAvatarUrl(
                request.getAvatarUrl()
        );


        // --------------------------------------------------
        // Kiểm tra email đã tồn tại
        // --------------------------------------------------

        if (repository.existsByEmail(user.getEmail())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email đã được đăng ký"
            );
        }


        // --------------------------------------------------
        // Kiểm tra username đã tồn tại
        // --------------------------------------------------

        if (repository.existsByUserName(user.getUserName())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Tên người dùng đã tồn tại"
            );
        }


        // --------------------------------------------------
        // Giá trị mặc định
        // --------------------------------------------------

        user.setRecommendationCount(0L);

        user.setStatus("ACTIVE");

        user.setAuthProvider("LOCAL");

        user.setEmailVerified(0);


        // --------------------------------------------------
        // Thời gian
        // --------------------------------------------------

        LocalDateTime now = LocalDateTime.now();

        user.setCreatedAt(now);

        user.setUpdatedAt(now);


        // --------------------------------------------------
        // Mã hóa password bằng BCrypt
        // --------------------------------------------------

        user.setPasswordHash(
                passwordEncoder.encode(
                        user.getPasswordHash()
                )
        );


        // --------------------------------------------------
        // Lưu database
        // --------------------------------------------------

        return repository.save(user);
    }


    // ======================================================
    // ĐĂNG NHẬP
    // ======================================================

    public User login(
            String email,
            String password
    ) {

        User user = repository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Email không tồn tại"
                        )
                );


        // --------------------------------------------------
        // Kiểm tra tài khoản
        // --------------------------------------------------

        if (!"ACTIVE".equals(user.getStatus())) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Tài khoản đã bị khóa"
            );
        }


        // --------------------------------------------------
        // Kiểm tra password bằng BCrypt
        // --------------------------------------------------

        if (!passwordEncoder.matches(
                password,
                user.getPasswordHash()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Mật khẩu không chính xác"
            );
        }


        return user;
    }


    // ======================================================
    // LẤY USER THEO ID
    // ======================================================

    public User getById(Long userId) {

        return repository
                .findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Không tìm thấy người dùng"
                        )
                );
    }


    // ======================================================
    // CẬP NHẬT THÔNG TIN TÀI KHOẢN
    // ======================================================

    public User updateProfile(
            Long userId,
            String phone,
            String gender
    ) {

        User user = repository
                .findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Không tìm thấy người dùng"
                        )
                );


        // --------------------------------------------------
        // SỐ ĐIỆN THOẠI
        // --------------------------------------------------

        if (phone != null) {

            phone = phone.trim();

            if (phone.isEmpty()) {
                user.setPhone(null);
            } else {
                user.setPhone(phone);
            }
        }


        // --------------------------------------------------
        // GIỚI TÍNH
        // --------------------------------------------------

        if (gender != null) {

            if (!gender.equals("MALE")
                    && !gender.equals("FEMALE")
                    && !gender.equals("SECRET")) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Giới tính không hợp lệ"
                );
            }

            user.setGender(gender);
        }


        // --------------------------------------------------
        // CẬP NHẬT THỜI GIAN
        // --------------------------------------------------

        user.setUpdatedAt(
                LocalDateTime.now()
        );


        return repository.save(user);
    }


    // ======================================================
    // ĐỔI MẬT KHẨU
    // ======================================================

    public void changePassword(
            Long userId,
            String currentPassword,
            String newPassword,
            String confirmPassword
    ) {

        User user = repository
                .findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Không tìm thấy người dùng"
                        )
                );


        // --------------------------------------------------
        // 1. Kiểm tra mật khẩu hiện tại
        // --------------------------------------------------

        if (!passwordEncoder.matches(
                currentPassword,
                user.getPasswordHash()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Mật khẩu hiện tại không chính xác"
            );
        }


        // --------------------------------------------------
        // 2. Mật khẩu mới không được giống mật khẩu hiện tại
        // --------------------------------------------------

        if (passwordEncoder.matches(
                newPassword,
                user.getPasswordHash()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mật khẩu mới phải khác mật khẩu hiện tại"
            );
        }


        // --------------------------------------------------
        // 3. Kiểm tra xác nhận mật khẩu
        // --------------------------------------------------

        if (confirmPassword == null
                || !newPassword.equals(confirmPassword)) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mật khẩu xác nhận không khớp"
            );
        }


        // --------------------------------------------------
        // 4. Mã hóa mật khẩu mới
        // --------------------------------------------------

        user.setPasswordHash(
                passwordEncoder.encode(newPassword)
        );


        // --------------------------------------------------
        // 5. Cập nhật thời gian
        // --------------------------------------------------

        user.setUpdatedAt(
                LocalDateTime.now()
        );


        // --------------------------------------------------
        // 6. Lưu database
        // --------------------------------------------------

        repository.save(user);
    }


    // ======================================================
    // ĐỔI EMAIL
    // ======================================================

    public void changeEmail(
            Long userId,
            String currentPassword,
            String newEmail
    ) {

        System.out.println("========== CHANGE EMAIL SERVICE ==========");

        User user = repository
                .findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Không tìm thấy người dùng"
                        )
                );

        System.out.println("USER FOUND");
        System.out.println("Database email = [" + user.getEmail() + "]");


        // ==================================================
        // 1. KIỂM TRA PASSWORD
        // ==================================================

        System.out.println("CHECK PASSWORD");

        boolean passwordCorrect =
                passwordEncoder.matches(
                        currentPassword,
                        user.getPasswordHash()
                );

        System.out.println(
                "PASSWORD CORRECT = " + passwordCorrect
        );

        if (!passwordCorrect) {

            System.out.println(
                    "PASSWORD FAILED"
            );

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Mật khẩu hoặc email không chính xác"
            );
        }


        System.out.println("PASSWORD OK");


        // ==================================================
        // 2. KIỂM TRA EMAIL RỖNG
        // ==================================================

        System.out.println(
                "CHECK EMAIL EMPTY"
        );

        if (newEmail == null || newEmail.trim().isEmpty()) {

            System.out.println(
                    "EMAIL EMPTY"
            );

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mật khẩu hoặc email không chính xác"
            );
        }

        newEmail = newEmail.trim();

        System.out.println(
                "EMAIL AFTER TRIM = [" + newEmail + "]"
        );


        // ==================================================
        // 3. KIỂM TRA EMAIL FORMAT
        // ==================================================

        System.out.println(
                "CHECK EMAIL FORMAT"
        );

        boolean emailFormatCorrect =
                newEmail.matches(
                        "^[A-Za-z0-9._%+-]+@gmail\\.com$"
                );

        System.out.println(
                "EMAIL FORMAT CORRECT = "
                        + emailFormatCorrect
        );

        if (!emailFormatCorrect) {

            System.out.println(
                    "EMAIL FORMAT FAILED"
            );

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mật khẩu hoặc email không chính xác"
            );
        }


        System.out.println(
                "EMAIL FORMAT OK"
        );


        // ==================================================
        // 4. EMAIL TRÙNG EMAIL HIỆN TẠI
        // ==================================================

        System.out.println(
                "CHECK CURRENT EMAIL"
        );

        if (newEmail.equalsIgnoreCase(
                user.getEmail()
        )) {

            System.out.println(
                    "EMAIL SAME AS CURRENT EMAIL"
            );

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email đã được sử dụng"
            );
        }


        // ==================================================
        // 5. EMAIL TRÙNG USER KHÁC
        // ==================================================

        System.out.println(
                "CHECK EMAIL EXISTS"
        );

        if (repository.existsByEmail(newEmail)) {

            System.out.println(
                    "EMAIL ALREADY EXISTS"
            );

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email đã được sử dụng"
            );
        }


        // ==================================================
        // 6. ĐỔI EMAIL
        // ==================================================

        System.out.println(
                "CHANGE EMAIL"
        );

        user.setEmail(newEmail);

        user.setUpdatedAt(
                LocalDateTime.now()
        );

        repository.save(user);

        System.out.println(
                "CHANGE EMAIL SUCCESS"
        );
    }

}