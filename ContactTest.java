import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactTest {
    
    @Test
    public void testValidContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        Assertions.assertEquals("1234567890", contact.getContactId());
        Assertions.assertEquals("John", contact.getFirstName());
        Assertions.assertEquals("Doe", contact.getLastName());
        Assertions.assertEquals("1234567890", contact.getPhone());
        Assertions.assertEquals("123 Main St", contact.getAddress());
    }
    
    @Test
    public void testNullContactId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    public void testLongContactId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    public void testInvalidPhone() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "123", "123 Main St");
        });
    }
    
    @Test
    public void testNonDigitPhone() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "123abc4567", "123 Main St");
        });
    }
    
    @Test
    public void testLongAddress() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", 
                       "123 Main St, Apt 456, City, State, ZIP 12345");
        });
    }
}