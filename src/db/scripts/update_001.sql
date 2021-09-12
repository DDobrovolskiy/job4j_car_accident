CREATE TABLE IF NOT EXISTS rules (
   rule_id SERIAL PRIMARY KEY,
   rule_name TEXT NOT NULL,
   UNIQUE (rule_name)
);

CREATE TABLE IF NOT EXISTS accident_types (
   type_id SERIAL PRIMARY KEY,
   type_name TEXT NOT NULL,
   UNIQUE (type_name)
);

CREATE TABLE IF NOT EXISTS accidents (
   accident_id SERIAL PRIMARY KEY,
   accident_name TEXT NOT NULL,
   accident_text TEXT NOT NULL,
   accident_address TEXT NOT NULL,
   type_id int NOT NULL,
   FOREIGN KEY (type_id) REFERENCES accident_types (type_id)
);

CREATE TABLE IF NOT EXISTS accidents_rules (
    accident_id integer NOT NULL,
    rule_id integer NOT NULL,
    FOREIGN KEY (accident_id) REFERENCES accidents (accident_id),
    FOREIGN KEY (rule_id) REFERENCES rules (rule_id),
    UNIQUE (accident_id, rule_id)
);