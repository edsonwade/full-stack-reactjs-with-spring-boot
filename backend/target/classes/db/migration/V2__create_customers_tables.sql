-- Create sequence for customer_id
DROP SEQUENCE IF EXISTS customer_id_seq;
CREATE SEQUENCE customer_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS customers (
                                         customer_id SERIAL PRIMARY KEY,
                                         first_name VARCHAR(255),
                                         last_name VARCHAR(255),
                                         username VARCHAR(255) UNIQUE,
                                         password VARCHAR(255),
                                         email VARCHAR(255) UNIQUE,
                                         phone VARCHAR(20),
                                         role VARCHAR(20)
);

-- ALTER TABLE customers
--     ALTER COLUMN role SET DATA TYPE smallint USING role::smallint;

INSERT INTO customers (first_name, last_name, username, password, email, phone, role)
VALUES ('John', 'Doe', 'johndoe', 'password123', 'john.doe@example.com', '1234567890', 'USER');