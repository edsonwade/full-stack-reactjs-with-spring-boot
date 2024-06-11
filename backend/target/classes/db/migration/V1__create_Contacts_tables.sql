CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Create the contacts table

CREATE TABLE contacts (
                          contact_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          name VARCHAR(255),
                          email VARCHAR(255) UNIQUE NOT NULL,
                          title VARCHAR(255),
                          phone VARCHAR(255),
                          address VARCHAR(255),
                          status VARCHAR(50),
                          photo_url VARCHAR(255)
);

-- Insert initial data
INSERT INTO contacts (contact_id, name, email, title, phone, address, status, photo_url) VALUES
                                                                                             (gen_random_uuid(), 'John Doe', 'john.doe@example.com', 'Manager', '123-456-7890', '123 Main St', 'Active', 'http://example.com/photo1.jpg'),
                                                                                             (gen_random_uuid(), 'Jane Smith', 'jane.smith@example.com', 'Developer', '098-765-4321', '456 Elm St', 'Active', 'http://example.com/photo2.jpg');
