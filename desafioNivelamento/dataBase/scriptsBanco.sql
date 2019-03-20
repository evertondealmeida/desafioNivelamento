INSERT INTO loja VALUES('?','?','?','?','?','?');

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