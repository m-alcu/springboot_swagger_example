CREATE TABLE product
(
   id serial PRIMARY KEY NOT NULL,
   description text,
   image_url text,
   price numeric(13,2),
   product_id text,
   version int
)
;
