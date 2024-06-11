package code.with.vanilson;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static code.with.vanilson.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * ContactController
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-11
 */

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    /**
     * Creates a new contact.
     *
     * @param contact the contact to create
     * @return a ResponseEntity containing the created contact
     */
    @PostMapping(value = "/create-contact")
    public ResponseEntity<Contact> createContact(@RequestBody @Valid Contact contact) {
        //return ResponseEntity.ok().body(contactService.createContact(contact));
        return ResponseEntity.created(URI.create("/contacts/userID")).body(contactService.createContact(contact));
    }

    /**
     * Retrieves all contacts with pagination.
     *
     * @param page the page number
     * @param size the size of each page
     * @return a ResponseEntity containing a page of contacts
     */
    @GetMapping
    public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    /**
     * Retrieves a contact by ID.
     *
     * @param id the ID of the contact to retrieve
     * @return a ResponseEntity containing the retrieved contact
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(contactService.getContact(id));
    }

    /**
     * Uploads a photo for a contact.
     *
     * @param id   the ID of the contact
     * @param file the photo file to upload
     * @return a ResponseEntity containing a message about the uploaded photo
     */
    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));
    }

    /**
     * Retrieves a photo by filename.
     *
     * @param filename the filename of the photo
     * @return a byte array representing the photo
     * @throws IOException if there is an error reading the photo file
     */
    @GetMapping(path = "/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

}