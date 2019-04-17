# Instruções de utilização
# Banco de Dados  

  
Para levantar o banco:     
     
     *Entrar na pasta desafioDesenvovimento;
     *Abrir o terminal através dessa pasta;
     *Escrever no terminal o comando > Sqlite3 lojas.db 
  
Informações:
    
    *Na pasta dataBase contem os scripts de inserção e criação, que foram utilizados no banco.
  
# DEV - Métodos HTTP:
  
Inserir:
    
    *Aceita JSON via POST;
    
    *Exemplo de entrada:
          {
              "name": "Compasso",
              "address": "Rua b, 171",
              "phone": "(53)3230-1013",
              "cnpj": "09876543210919",
              "workingHour": "Diariamente das 11hs às 23hs",
              "city": {		
                "code": 5300108,
                "name": "Brasilia",
                "codeState": 53
               }
          }
Editar:  
    
    *Aceita ID de uma loja via PUT;  
    
Excluir:  
    
    *Aceita ID de uma loja via DELETE;
    
Listar lojas por Cidade:
    
    *Aceita o código do IBGE da Cidade via GET;
    
Listar dados de uma loja:
    
    *Aceita ID de uma loja via GET;

Listar Estados:

    *Somente a chamada dos Estados por GET;

Listar Cidades por Estado:

    *Aceita o código do IBGE do Estado via GET;

Listar Log:

    *Somente a chamada dos Logs por GET;
    
   

# Front:

Para abrir a página html de lojas:
  
    *Entra na pasta Front do projeto;
    *Dentro da pasta clique em index.html;

# Log:
    
    *Conforme especificações pode ser ordenado por qualquer coluna da tabela; 

    *As linhas que indiquem erros na aplicação devem ser vermelhas, as de sucesso verdes e as de informação pretas. 

