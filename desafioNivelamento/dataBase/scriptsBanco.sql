INSERT INTO loja VALUES(0,'TAQI','centro','5332323232','12345678901234','tododia',4315602);
INSERT INTO loja VALUES(1,'Marisa','centro','5332323232','01234567890123','tododia',4315602);
INSERT INTO loja VALUES(2,'Renner','centro','5332323232','0987654321098','tododia',5300108);




UPDATE loja SET nome = '?', endereco = '?',telefone = '?', cnpj = '?', horarioAtendimento = '?', codigoCidade = '?' WHERE id = '?';

DELETE loja WHERE id = '?';

SELECT * FROM loja;

SELECT * FROM loja WHERE id = '?';

SELECT * FROM estado;

SELECT loja.* FROM loja INNER JOIN cidade ON loja.codigoCidade = cidade.codigo WHERE cidade.codigo = '?';

SELECT loja.* FROM loja 
INNER JOIN cidade ON loja.codigoCidade = cidade.codigo
INNER JOIN estado ON cidade.codigoEstado = estado.codigo
WHERE estado.codigo = '?';