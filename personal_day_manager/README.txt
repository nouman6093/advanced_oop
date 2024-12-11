-- Create a table for contact inquiries
CREATE TABLE contact_us (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    subject VARCHAR(150) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a table for signup information
CREATE TABLE signup_info (
    id INT AUTO_INCREMENT PRIMARY KEY,    -- Unique ID for each entry
    username VARCHAR(255) NOT NULL UNIQUE, -- Ensures the username is unique
    password VARCHAR(255) NOT NULL         -- Password column
);

-- Create a table for to-do items with all columns
CREATE TABLE to_do_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    to_do VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE NOT NULL,
    due_time TIME NOT NULL,
    send_mail BOOLEAN NOT NULL DEFAULT FALSE,
    important BOOLEAN NOT NULL DEFAULT FALSE -- Column added directly
);

-- Create a table for user profiles with all columns
CREATE TABLE profile (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image LONGBLOB NOT NULL,
    email VARCHAR(255) NOT NULL -- Column added directly
);
