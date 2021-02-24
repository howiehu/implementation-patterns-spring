CREATE TABLE `article`
(
    `slug`        VARCHAR(60) PRIMARY KEY,
    `title`       VARCHAR(60) NOT NULL,
    `description` VARCHAR(160),
    `body`        TEXT,
    `author_id`   CHAR(36),
    `created_at`  TIMESTAMP(6),
    `updated_at`  TIMESTAMP(6)
)
