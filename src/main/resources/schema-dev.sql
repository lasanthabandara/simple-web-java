DROP TABLE IF EXISTS todo;
CREATE TABLE todo (id SERIAL PRIMARY KEY, description VARCHAR(255), details VARCHAR(4096), done BOOLEAN);

create Sequence If NOT EXISTS HIBERNATE_SEQUENCE
minvalue 100000
maxvalue 9999999999999999
start with 100060
increment by 1
cache 20;