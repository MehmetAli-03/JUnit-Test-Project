import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class UserValidator {

    /*
     * İsim ve Soyisim kontrolü yapar.
     * Kurallar: Boş olamaz, null olamaz, sadece alfabetik karakterler içerebilir.
     */
    public boolean isNameValid(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        // Regex (Düzenli İfade) kullanımı: Sadece Türkçe ve İngilizce harflere izin ver.
        return name.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$"); 
    }

    /**
     * E-posta adresi kontrolü yapar.
     * Kurallar: Boş olamaz, içerisinde mutlaka '@' ve '.' karakterlerini barındırmalıdır.
     */
    public boolean isEmailValid(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return email.contains("@") && email.contains(".");
    }

    /**
     * Şifre güvenliği kontrolü yapar.
     * Kurallar: Null veya sadece boşluk olamaz, minimum 8 karakter uzunluğunda olmalıdır.
     */
    public boolean isPasswordValid(String password) {
        if (password == null || password.trim().isEmpty()) return false;
        return password.length() >= 8;
    }

    /**
     * Girilen iki şifrenin birbiriyle eşleşip eşleşmediğini kontrol eder.
     * Kurallar: Değerler null olamaz ve birebir aynı olmalıdır (Büyük/küçük harf duyarlı).
     */
    public boolean doPasswordsMatch(String password, String confirmPassword) {
        if (password == null || confirmPassword == null) return false;
        return password.equals(confirmPassword);
    }

    /**
     * Doğum tarihi format ve mantık kontrolü yapar.
     * Kurallar: Format "dd/MM/yyyy" olmalı ve tarih mutlaka bugünden (geçmiş) önce olmalıdır.
     */
    public boolean isBirthDateValid(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return false;
        try {
            // Tarih formatını belirle
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dateStr, formatter);
            // Girilen tarih şu anki tarihten (now) önce mi diye kontrol et
            return birthDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            // Eğer string parse edilemezse (örneğin "32/13/2020" gibi mantıksız bir tarihse) false dön
            return false; 
        }
    }
}