Sqlite3 allShop.db

CREATE TABLE State(
    Code INTEGER PRIMARY KEY AUTOINCREMENT,
    Name varchar(80),
    UF varchar(2) NOT NULL
);

CREATE TABLE City(
    Code INTEGER PRIMARY KEY AUTOINCREMENT,
    Name varchar(80),
    CodeState INTEGER NOT NULL,
    FOREIGN KEY (CodeState) REFERENCES State (Code)
);

CREATE TABLE Shop(
	Id INTEGER PRIMARY KEY AUTOINCREMENT,
	Name varchar(80) NOT NULL,
	Address varchar(80) NOT NULL,
	Phone varchar(10) NOT NULL,
	Cnpj varchar(14) NOT NULL,
	WorkingHour varchar(80) NOT NULL,
	city_Code INTEGER	
);



