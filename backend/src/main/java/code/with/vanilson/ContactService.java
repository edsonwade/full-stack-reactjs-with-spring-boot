package code.with.vanilson;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static code.with.vanilson.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * ContactService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-11
 */
@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepo;

    /**
     * Retrieves a paginated list of all contacts, sorted by name.
     *
     * @param page the page number to retrieve
     * @param size the size of the page to retrieve
     * @return a paginated list of contacts
     */
    public Page<Contact> getAllContacts(int page, int size) {
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    /**
     * Retrieves a contact by its ID.
     *
     * @param id the ID of the contact
     * @return the contact with the specified ID
     * @throws RuntimeException if the contact is not found
     */

    public Contact getContact(String id) {
        return contactRepo.findContactByContactId(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    /**
     * Creates a new contact.
     *
     * @param contact the contact to create
     * @return the created contact
     */
    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    /**
     * Deletes a contact.
     *
     * @param contact the contact to delete
     */
    public void deleteContact(Contact contact) {
        contactRepo.delete(contact);
    }

    /**
     * Uploads a photo for a specific contact.
     *
     * @param id   the ID of the contact
     * @param file the photo file to upload
     * @return the URL of the uploaded photo
     * @throws RuntimeException if the photo cannot be saved
     */
    public String uploadPhoto(String id, MultipartFile file) {
        log.info("Saving picture for user ID: {}", id);
        Contact contact = getContact(id);
        String photoUrl = photoFunction.apply(id, file);
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;
    }

    /**
     * Function to extract the file extension from a filename.
     */
    private final Function<String, String> fileExtension =
            filename -> Optional.of(filename)
                    .filter(name -> name.contains("."))
                    .map(name -> "." + name.substring(name.lastIndexOf(".") + 1))
                    .orElse(".png");
    /**
     * BiFunction to upload a photo for a contact.
     *
     * @param id the ID of the contact
     * @param image the photo file to upload
     * @return the URL of the uploaded photo
     * @throws RuntimeException if the photo cannot be saved
     */
    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/contacts/image/")
                    .path(filename)
                    .toUriString();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to save image: " + exception.getMessage(), exception);
        }
    };
}