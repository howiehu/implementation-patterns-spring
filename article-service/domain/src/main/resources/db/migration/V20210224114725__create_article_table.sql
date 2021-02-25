CREATE TABLE `article`
(
    `slug`        VARCHAR(60) PRIMARY KEY,
    `title`       VARCHAR(60)  NOT NULL,
    `description` VARCHAR(160),
    `body`        TEXT,
    -- UUID 类型必须保存成为 BINARY(16)，否则 Spring Data JPA 会在 MySql 环境下报错，类似：
    -- java.sql.SQLException: Incorrect string value: '\x9Bra\x9A\xC22...'
    -- 这也是一个 H2 与 MySql 的差异，如果用 H2 作为 Fake DB，则可以使用 CHAR(36) 那么就会有潜在的不一致风险
    `author_id`   BINARY(16)   NOT NULL,
    `created_at`  TIMESTAMP(6) NOT NULL,
    `updated_at`  TIMESTAMP(6) NOT NULL
)
