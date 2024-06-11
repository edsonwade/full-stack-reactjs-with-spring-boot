package code.with.vanilson;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * ContactRepository
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-11
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findContactByContactId(String id);
}
