CREATE TABLE IF NOT EXISTS person (
    id UUID DEFAULT ${uuid_function} NOT NULL,
    first_name varchar(250) NOT NULL,
    last_name varchar(250) NOT NULL,
    cpf varchar (11) UNIQUE NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    gender VARCHAR (1),
    birth_date DATE NOT NULL,
    created_at timestamp(6),
    updated_at timestamp(6),

    PRIMARY KEY (id)
    );