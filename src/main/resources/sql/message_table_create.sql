CREATE TABLE IF NOT EXISTS message (
  id INT PRIMARY KEY AUTO_INCREMENT,
  subject VARCHAR(150),
  text VARCHAR(255) NOT NULL,
  send_message BOOLEAN,
  message_viewed BOOLEAN
);