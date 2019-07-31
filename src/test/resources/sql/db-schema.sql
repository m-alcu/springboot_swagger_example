CREATE TABLE product
(
   id bigserial PRIMARY KEY NOT NULL,
   str_description text,
   str_image_url text,
   amt_price numeric(13,2),
   cod_product text,
   version int,
  DAT_USER_CREATED       		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
  COD_USER_MODIFIED      		TEXT,
  COD_USER_CREATED       		TEXT			NOT NULL,
  DAT_USER_MODIFIED      		TIMESTAMP

)
;
