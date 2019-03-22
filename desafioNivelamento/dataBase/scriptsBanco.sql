INSERT INTO loja VALUES(0,'TAQI','centro','3232','01234','tododia',4315602);
INSERT INTO loja VALUES(1,'Marisa','centro','3232','01234','tododia',4315602);
INSERT INTO loja VALUES(2,'Renner','centro','3232','01234','tododia',4315602);




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