DROP SEQUENCE IF EXISTS token_id_seq;
CREATE SEQUENCE token_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS token (
                       id BIGINT DEFAULT nextval('token_id_seq') PRIMARY KEY,
                       access_token VARCHAR(255),
                       refresh_token VARCHAR(255),
                       is_logged_out BOOLEAN,
                       customer_id BIGINT,
                       CONSTRAINT fk_customer FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);