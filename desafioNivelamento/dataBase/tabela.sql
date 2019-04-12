Sqlite3 allShop.db

CREATE TABLE State(
    Code INTEGER PRIMARY KEY AUTOINCREMENT,
    Name varchar(80),
    UF varchar(2) NOT NULL
);

CREATE TABLE City(
    Code INTEGER PRIMARY KEY AUTOINCREMENT,
    Name varchar(80),
    state_Code INTEGER
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

CREATE TABLE Log(
	Id INTEGER PRIMARY KEY AUTOINCREMENT,
	Local varchar(80) NOT NULL,
	TypeMessage varchar(80) NOT NULL,
	Description varchar(80) NOT NULL,
	DateLog varchar(10) NOT NULL
);




