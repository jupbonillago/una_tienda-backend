DROP TABLE IF EXISTS "public"."answer";
DROP TABLE IF EXISTS "public"."question";
DROP TABLE IF EXISTS "public"."review";
DROP TABLE IF EXISTS "public"."cartshop-item";
DROP TABLE IF EXISTS "public"."cartshop";
DROP TABLE IF EXISTS "public"."transaction";
DROP TABLE IF EXISTS "public"."coupon";
DROP TABLE IF EXISTS "public"."post";
DROP TABLE IF EXISTS "public"."category";
DROP TABLE IF EXISTS "public"."person";
DROP TABLE IF EXISTS "public"."role";

-- ---------------------
-- Table "public"."role"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."role"(
  "role_id"  SERIAL  NOT NULL,
  "name"     TEXT    NOT NULL,
  PRIMARY KEY ("role_id")
);

-- ---------------------
-- Table "public"."person"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."person"(
  "person_id"  SERIAL NOT NULL,
  "role_id"    INT    NOT NULL,
  "name"       TEXT   NOT NULL,
  "username"   TEXT   NOT NULL,
  "email"      TEXT   NOT NULL,
  "password"   TEXT   NOT NULL,
  "photo"      TEXT,
  "location"   TEXT,
  "paypal_id"  TEXT   NOT NULL,
  PRIMARY KEY ("person_id"),
  CONSTRAINT "fk_role_id"
    FOREIGN KEY ("role_id")
      REFERENCES "public"."role" ("role_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."category"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."category"(
  "category_id"  SERIAL  NOT NULL,
  "name"         TEXT    NOT NULL,
  "image"        TEXT,
  PRIMARY KEY ("category_id")
);

-- ---------------------
-- Table "public"."post"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."post"(
  "post_id"       SERIAL  NOT NULL,
  "person_id"       INT     NOT NULL,
  "category_id"   INT     ,
  "title"         TEXT    NOT NULL,
  "product_name"  TEXT    NOT NULL,
  "image"         TEXT,
  "description"   TEXT,
  "total_review"  DOUBLE PRECISION,
  "price"         INT     NOT NULL,
  "stock"         INT     NOT NULL,
  "state"         BOOLEAN     NOT NULL, 
  PRIMARY KEY ("post_id"),
  CONSTRAINT "fk_person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT "fk_category_id"
    FOREIGN KEY ("category_id")
      REFERENCES "public"."category" ("category_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."coupon"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."coupon"(
  "coupon_id"     SERIAL  NOT NULL,
  "category_id"   INT     NOT NULL,
  "start_date"    DATE    NOT NULL,
  "end_date"      DATE,
  "amount"        INT,
  "code"          TEXT,
  PRIMARY KEY ("coupon_id"),
  CONSTRAINT "fk_category_id"
    FOREIGN KEY ("category_id")
      REFERENCES "public"."category" ("category_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."transaction"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."transaction"(
  "transaction_id"     SERIAL  NOT NULL,
  "person_id"          INT     NOT NULL,
  "post_id"            INT     NOT NULL,
  "stock_price"        INT     NOT NULL,
  "quantity"           INT     NOT NULL,
  PRIMARY KEY ("transaction_id"),
  CONSTRAINT "fk_buyer_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT "fk_post_id"
    FOREIGN KEY ("post_id")
      REFERENCES "public"."post" ("post_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."cartshop"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."cartshop"(
  "cartshop_id"  SERIAL  NOT NULL,
  "person_id"    INT     NOT NULL,
  "total"        INT     NOT NULL,
  "state"        TEXT    NOT NULL,
  PRIMARY KEY ("cartshop_id"),
  CONSTRAINT "fk_person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."cartshop-item"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."cartshop_item"(
  "cartshop_item_id"  SERIAL  NOT NULL,
  "cartshop_id"       INT     NOT NULL,
  "post_id"           INT     NOT NULL,
  "quantity"          INT     NOT NULL,
  PRIMARY KEY ("cartshop_item_id"),
  CONSTRAINT "fk_cartshop_id"
    FOREIGN KEY ("cartshop_id")
      REFERENCES "public"."cartshop" ("cartshop_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT "fk_post_id"
    FOREIGN KEY ("post_id")
      REFERENCES "public"."post" ("post_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."review"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."review"(
  "review_id"    SERIAL  NOT NULL,
  "person_id"    INT     NOT NULL,
  "post_id"      INT     NOT NULL,
  "value"        INT     NOT NULL,
  "text"         TEXT,
  PRIMARY KEY ("review_id"),
  CONSTRAINT "fk_person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT "fk_post_id"
    FOREIGN KEY ("post_id")
      REFERENCES "public"."post" ("post_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."question"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."question"(
  "question_id"    SERIAL  NOT NULL,
  "person_id"      INT     NOT NULL,
  "post_id"        INT     NOT NULL,
  "text"           TEXT,
  PRIMARY KEY ("question_id"),
  CONSTRAINT "fk_person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT "fk_post_id"
    FOREIGN KEY ("post_id")
      REFERENCES "public"."post" ("post_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."answer"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."answer"(
  "answer_id"    SERIAL  NOT NULL,
  "question_id"  INT     NOT NULL,
  "person_id"    INT     NOT NULL,
  "text"         TEXT,
  PRIMARY KEY ("answer_id"),
  CONSTRAINT "fk_question_id"
    FOREIGN KEY ("question_id")
      REFERENCES "public"."question" ("question_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT "fk_person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

-- ---------------------
-- Table "public"."recover_token"
-- ---------------------
CREATE TABLE IF NOT EXISTS "public"."recover_token"(
  "token_id"    SERIAL  NOT NULL,
  "person_id"  INT     NOT NULL,
  "token"    TEXT     NOT NULL,
  "generated_at"         REAL,
  PRIMARY KEY ("token_id"),
  CONSTRAINT "fk_person_id"
    FOREIGN KEY ("person_id")
      REFERENCES "public"."person" ("person_id")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);