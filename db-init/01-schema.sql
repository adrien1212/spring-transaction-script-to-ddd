CREATE TABLE material_state (
    id BIGINT NOT NULL AUTO_INCREMENT,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE articles (
      id BIGINT NOT NULL AUTO_INCREMENT,
      label VARCHAR(255),
      material_state_id BIGINT,
      stock INT NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (material_state_id) REFERENCES material_state(id)
);

CREATE TABLE clients (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255)
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    created_at DATETIME(6)
);

CREATE TABLE order_lines (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     article_id BIGINT NOT NULL,
     quantity INT NOT NULL,
     order_id BIGINT NOT NULL,
     FOREIGN KEY (order_id) REFERENCES orders (id)
);