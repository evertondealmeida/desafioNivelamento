Sqlite3 allShop.db

CREATE TABLE state(
    code INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(80),
    uf varchar(2) NOT NULL
);

CREATE TABLE city(
    code INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(80),
    codeState INTEGER NOT NULL,
    FOREIGN KEY (codeState) REFERENCES state(code)
);

CREATE TABLE shop(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name varchar(80) NOT NULL,
	address varchar(80) NOT NULL,
	phone varchar(10) NOT NULL,
	cnpj varchar(14) NOT NULL,
	workingHour varchar(80) NOT NULL,
	codeCity INTEGER NOT NULL,
    FOREIGN KEY (codeCity) REFERENCES city(code)
);



