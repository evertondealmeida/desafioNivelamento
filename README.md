# desafioNivelamento
#Banco de Dados  

  
Para levantar o banco:     
     
     *Entrar na pasta desafioDesenvovimento;
     *Abrir o terminal através dessa pasta;
     *Escrever no terminal o comando > Sqlite3 lojas.db 
  
Informações:
    
    *Na pasta dataBase contem os scripts de inserção e criação, que foram utilizados no banco.
  
#DEV - Métodos HTTP:
  
Inserir:
    
    *Aceita JSON via POST;
    *Exemplo de entrada:
          {
            "nome": "Teste",
            "endereco": "Rua Abc, 000",
            "telefone": "(53)3233-1318",
            "cnpj": "098.765.432/109-8",
            "horarioAtendimento": "Diariamente das 11hs às 23hs",
            "codigoCidade": 4314407
          }
Editar:  
    
    *Aceita ID de uma loja via PUT;  
    
Excluir:  
    
    *Aceita ID de uma loja via DELETE;
    
Listar lojas por Estado:
    
    *Aceita código do IBGE do Estado via GET;
    
Listar lojas por Cidade:
    
    *Aceita código do IBGE do Estado / o código do IBGE da Cidade via GET;
    
Listar dados de uma loja:
    
    *Aceita ID de uma loja via GET;

#Front:

Para abrir a página html:
  
    *Entra na pasta Front do projeto;
    *Dentro da pasta clique em index.html
