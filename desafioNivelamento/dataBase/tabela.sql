Sqlite3 lojas.db

CREATE TABLE estado(
    codigo INTEGER PRIMARY KEY AUTOINCREMENT,
    nome varchar(80),
    uf varchar(2) NOT NULL
);

CREATE TABLE cidade(
    codigo INTEGER PRIMARY KEY AUTOINCREMENT,
    nome varchar(80),
    codigoEstado INTEGER NOT NULL,
    FOREIGN KEY (codigoEstado) REFERENCES estado(Codigo)
);

CREATE TABLE loja(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	nome varchar(80) NOT NULL,
	endereco varchar(80) NOT NULL,
	telefone varchar(10) NOT NULL,
	cnpj varchar(14) NOT NULL,
	horarioAtendimento varchar(80) NOT NULL,
	codigoCidade INTEGER NOT NULL,
    FOREIGN KEY (codigoCidade) REFERENCES cidade(codigo)
);



