-- Create the books table

create table authors (
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    bio       TEXT,
    created_at timestamp with time zone default timezone('utc'::text, now()),
    updated_at timestamp with time zone default timezone('utc'::text, now())
);
create table books (
  id             SERIAL PRIMARY KEY,
  title          VARCHAR(255) NOT NULL,
  isbn           VARCHAR(20) UNIQUE,
  publisher      VARCHAR(255),
  author         VARCHAR(255),
  price          DECIMAL(10,2) NOT NULL,
  stock_quantity INT NOT NULL DEFAULT 0,
  category_id    INT,
  author_id     INT,
  created_at timestamp with time zone default timezone('utc'::text, now()),
  updated_at timestamp with time zone default timezone('utc'::text, now()),
  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (author_id) REFERENCES authors(id)
);
create table books_authors (
  book_id   INT,
  author_id INT,
  PRIMARY KEY (book_id, author_id),
  FOREIGN KEY (book_id) REFERENCES Books(id) ON DELETE CASCADE,
  FOREIGN KEY (author_id) REFERENCES Authors(id) ON DELETE CASCADE
);


CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        customer_name VARCHAR(255) NOT NULL,
                        customer_email VARCHAR(255),
                        customer_phone VARCHAR(20),
                        order_date timestamp with time zone default timezone('utc'::text, now()),
                        order_type VARCHAR(20) CHECK (order_type IN ('online', 'walk-in')) NOT NULL,
                        status VARCHAR(20) CHECK (status IN ('pending', 'completed', 'cancelled')) DEFAULT 'pending',
                        total_amount DECIMAL(10, 2) NOT NULL,
                        shipping_address VARCHAR(255),  -- Only for online orders
                        created_at timestamp with time zone default timezone('utc'::text, now()),
                        updated_at timestamp with time zone default timezone('utc'::text, now())
);

CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             order_id INT REFERENCES orders(id) ON DELETE CASCADE,
                             book_id INT REFERENCES books(id) ON DELETE CASCADE,
                             quantity INT NOT NULL,
                             price DECIMAL(10, 2) NOT NULL,
                             total DECIMAL(10, 2) NOT NULL,  -- quantity * price
                             created_at timestamp with time zone default timezone('utc'::text, now())
);



create table category (
                          id          SERIAL PRIMARY KEY,
                          name        VARCHAR(100) NOT NULL UNIQUE,
                          description TEXT
);

create table customer (
                          id         SERIAL PRIMARY KEY,
                          name       VARCHAR(255) NOT NULL,
                          email      VARCHAR(255),
                          phone      VARCHAR(20),
                          address    TEXT,
                          created_at timestamp with time zone default timezone('utc'::text, now()),
                          updated_at timestamp with time zone default timezone('utc'::text, now())
)

-- Invoices Table
-- The Invoices table links to the Orders table and stores the invoice details like the invoice number, payment method, and total.

CREATE TABLE invoices (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id) ON DELETE CASCADE,
    invoice_number VARCHAR(50) UNIQUE NOT NULL,
    issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50) CHECK (payment_method IN ('credit_card', 'cash', 'e_wallet','KPay','Wave','Banking')) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('unpaid', 'paid')) DEFAULT 'unpaid',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- InvoiceItems Table
-- This table stores the details of the books in the invoice. It’s similar to order_items, but this table is used to keep track of items that have been invoiced.

CREATE TABLE invoice_items (
    id SERIAL PRIMARY KEY,
    invoice_id INT REFERENCES invoices(id) ON DELETE CASCADE,
    book_id INT REFERENCES books(id) ON DELETE CASCADE,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,  -- quantity * price
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


