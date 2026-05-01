import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserValidatorTest {

    UserValidator validator;

    // --- SETUP VE TEARDOWN METOTLARI ---

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
    }

    @AfterEach
    void tearDown() {
       
        validator = null;
    }
    // 1. HAPPY PATH (GEÇERLİ DURUMLAR)
    // Sistemin beklenen doğru verilerle sorunsuz çalıştığını test eder.
    @Test
    void test1_ValidNameAndSurname() {
        // Sadece harflerden oluşan geçerli bir ismin (Ahmet) sistem tarafından TRUE dönerek kabul edilmesi beklenir.
        assertTrue(validator.isNameValid("Ahmet"), "Geçerli isim kabul edilmeli");
    }

    // 2. SINIR DEĞER ANALİZİ (BOUNDARY VALUE ANALYSIS - BVA)
    // Şifre kuralı: Şifre en az 8 karakter olmalıdır.
    // Sınır değerleri etrafındaki (7, 8, 9 karakter) uç durumlar test edilir.
    @Test
    void test2_PasswordExact8Chars() { 
        // Geçerli Alt Sınır: Tam olarak 8 karakter girildiğinde sistemin bunu kabul etmesi (TRUE) beklenir.
        assertTrue(validator.isPasswordValid("12345678"));
    }

    @Test
    void test3_Password9Chars() { 
        // Geçerli Sınırın İçi: 8 karakterden uzun (9 karakterli) bir şifrenin kabul edilmesi (TRUE) beklenir.
        assertTrue(validator.isPasswordValid("123456789"));
    }

    @Test
    void test4_Password7Chars() { 
        // Geçersiz Sınır: 8 karakterden kısa (7 karakterli) şifrenin güvenlik kuralına takılıp reddedilmesi (FALSE) beklenir.
        assertFalse(validator.isPasswordValid("1234567"));
    }

    // 3. EŞDEĞERLİK SINIFLARI (EQUIVALENCE PARTITIONING - EP)
    // Geçersiz formatlara sahip verilerin gruplandırılarak test edildiği bölümdür.
    
    @Test
    void test5_EmailNoAtSymbol() { 
        // Geçersiz E-posta Sınıfı: İçinde '@' sembolü olmayan e-postaların reddedilmesi (FALSE) beklenir.
        assertFalse(validator.isEmailValid("ahmetgmail.com"));
    }

    @Test
    void test6_EmailNoDomain() { 
        // Geçersiz E-posta Sınıfı: Uzantısı (nokta işareti) olmayan e-postaların reddedilmesi (FALSE) beklenir.
        assertFalse(validator.isEmailValid("ahmet@com")); 
    }

    @Test
    void test7_PasswordsDoNotMatch() { 
        // Uyuşmayan Şifreler Sınıfı: Girilen şifre ve şifre tekrarı birbirinden farklıysa sistemin reddetmesi (FALSE) beklenir.
        assertFalse(validator.doPasswordsMatch("Sifre123", "Sifre321"));
    }

    @Test
    void test8_BirthDateInFuture() { 
        // Geçersiz Tarih Sınıfı: Doğum tarihi gelecekteki bir gün (2050 yılı) olamayacağı için reddedilmesi (FALSE) beklenir.
        assertFalse(validator.isBirthDateValid("01/01/2050"));
    }

    @Test
    void test9_BirthDateWrongFormat() { 
        // Yanlış Format Sınıfı: İstenilen DD/MM/YYYY formatı dışında girilen tarihlerin reddedilmesi (FALSE) beklenir.
        assertFalse(validator.isBirthDateValid("2000-12-31"));
    }

    @Test
    void test10_NameContainsNumbers() { 
        // Geçersiz İsim Sınıfı: İsim alanına rakam veya özel karakter girildiğinde sistemin reddetmesi (FALSE) beklenir.
        assertFalse(validator.isNameValid("Ahmet123!"));
    }

    // 4. BOŞ (EMPTY/NULL) ALAN KONTROLLERİ
    // Kullanıcının formdaki alanları boş bırakma veya boşluklarla doldurma ihtimaline karşı yapılan testlerdir.
    @Test
    void test11_NameIsEmpty() {
        // İsim alanı tamamen boş ("") bırakıldığında sistemin hata vermesi (FALSE) beklenir.
        assertFalse(validator.isNameValid(""));
    }

    @Test
    void test12_SurnameIsNull() {
        // Soyisim değeri sisteme hiç gönderilmezse (null), sistemin çökmemesi ve veriyi reddetmesi (FALSE) beklenir.
        assertFalse(validator.isNameValid(null));
    }

    @Test
    void test13_EmailIsOnlySpaces() {
        // Kullanıcı  sadece boşluk tuşuna bassa bile sistemin bunu geçersiz e-posta sayması (FALSE) beklenir.
        assertFalse(validator.isEmailValid("   "));
    }

    @Test
    void test14_PasswordIsEmpty() {
        // Şifre alanı boş bırakıldığında işlemin reddedilmesi (FALSE) beklenir.
        assertFalse(validator.isPasswordValid(""));
    }

    @Test
    void test15_ConfirmPasswordIsNull() {
        // Şifre doğrulama kısmı sisteme gönderilmezse (null), şifrelerin eşleşmediği varsayılıp reddedilmesi (FALSE) beklenir.
        assertFalse(validator.doPasswordsMatch("Sifre123", null));
    }
}