import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    
    private ContactService contactService;
    
    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }
    
    @Test
    public void testAddContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        Assertions.assertEquals(contact, contactService.getContact("1234567890"));
    }
    
    @Test
    public void testAddDuplicateContact() {
        Contact contact1 = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1234567890", "Jane", "Doe", "0987654321", "456 Oak St");
        
        contactService.addContact(contact1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
    }
    
    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.deleteContact("1234567890");
        Assertions.assertNull(contactService.getContact("1234567890"));
    }
    
    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        
        contactService.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Oak St");
        
        Contact updated = contactService.getContact("1234567890");
        Assertions.assertEquals("Jane", updated.getFirstName());
        Assertions.assertEquals("Smith", updated.getLastName());
        Assertions.assertEquals("0987654321", updated.getPhone());
        Assertions.assertEquals("456 Oak St", updated.getAddress());
    }
    
    @Test
    public void testUpdateNonexistentContact() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("nonexistent", "Jane", "Smith", "0987654321", "456 Oak St");
        });
    }
}