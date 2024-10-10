# javabibi
# javabibi
Documento Técnico do Projeto

1. Introdução
O sistema de biblioteca desenvolvido tem como objetivo facilitar o gerenciamento de empréstimos de livros, cadastro de usuários e controle do acervo. Ele foi projetado utilizando Java com a biblioteca Swing para a interface gráfica e PostgreSQL como banco de dados.

2. Arquitetura do Sistema
Camadas do Sistema:
Apresentação: Interface gráfica desenvolvida com Java Swing.
Serviço: Lida com a lógica de negócio e interage com os DAOs.
DAO (Data Access Object): Faz a conexão com o banco de dados e executa as operações CRUD.

4. Estrutura de Classes
Modelos:

Usuario: Representa os usuários do sistema, contendo atributos como id, nome, email, senha e CPF.
Livro: Representa os livros disponíveis na biblioteca, com atributos como id, título, autor e categoria.
Emprestimo: Representa os empréstimos realizados, com atributos como id, usuário, livro, data de empréstimo e data de devolução.
Serviços:

UsuarioService: Contém métodos para cadastro, listagem, edição e exclusão de usuários.
LivroService: Contém métodos para cadastro, listagem, edição e exclusão de livros.
EmprestimoService: Contém métodos para cadastrar, listar, editar e deletar empréstimos.

4. Banco de Dados
Estrutura do Banco:
Tabelas:
usuarios: id, nome, email, senha, cpf
livros: id, titulo, autor, categoria, disponivel
emprestimos: id, usuario_id, livro_id, data_emprestimo, data_devolucao

5. Fluxo de Operações
Cadastro de Usuário: O usuário preenche um formulário com nome, email, senha e CPF. O sistema valida os dados e salva no banco.
Empréstimo de Livro: O usuário seleciona um livro e o sistema registra o empréstimo com as datas correspondentes.
Devolução de Livro: O sistema permite a atualização das datas de devolução dos livros emprestados.
6. Exceções e Validações
O sistema inclui validações para entradas de dados, como verificar se os campos estão vazios e se a senha atende aos requisitos de segurança.

7. Tecnologias Utilizadas
Linguagem: Java
Banco de Dados: PostgreSQL
Bibliotecas: JDBC para conexão com o banco de dados, Swing para a interface gráfica.




Para elaborar um documento técnico e um manual do usuário para o seu projeto de sistema de biblioteca, precisamos abordar alguns aspectos fundamentais. Abaixo estão os esboços para ambos os documentos, que você pode expandir e detalhar conforme necessário.

Documento Técnico do Projeto
1. Introdução
O sistema de biblioteca desenvolvido tem como objetivo facilitar o gerenciamento de empréstimos de livros, cadastro de usuários e controle do acervo. Ele foi projetado utilizando Java com a biblioteca Swing para a interface gráfica e PostgreSQL como banco de dados.

2. Arquitetura do Sistema
Camadas do Sistema:
Apresentação: Interface gráfica desenvolvida com Java Swing.
Serviço: Lida com a lógica de negócio e interage com os DAOs.
DAO (Data Access Object): Faz a conexão com o banco de dados e executa as operações CRUD.
3. Estrutura de Classes
Modelos:

Usuario: Representa os usuários do sistema, contendo atributos como id, nome, email, senha e CPF.
Livro: Representa os livros disponíveis na biblioteca, com atributos como id, título, autor e categoria.
Emprestimo: Representa os empréstimos realizados, com atributos como id, usuário, livro, data de empréstimo e data de devolução.
Serviços:

UsuarioService: Contém métodos para cadastro, listagem, edição e exclusão de usuários.
LivroService: Contém métodos para cadastro, listagem, edição e exclusão de livros.
EmprestimoService: Contém métodos para cadastrar, listar, editar e deletar empréstimos.
4. Banco de Dados
Estrutura do Banco:
Tabelas:
usuarios: id, nome, email, senha, cpf
livros: id, titulo, autor, categoria, disponivel
emprestimos: id, usuario_id, livro_id, data_emprestimo, data_devolucao
5. Fluxo de Operações
Cadastro de Usuário: O usuário preenche um formulário com nome, email, senha e CPF. O sistema valida os dados e salva no banco.
Empréstimo de Livro: O usuário seleciona um livro e o sistema registra o empréstimo com as datas correspondentes.
Devolução de Livro: O sistema permite a atualização das datas de devolução dos livros emprestados.
6. Exceções e Validações
O sistema inclui validações para entradas de dados, como verificar se os campos estão vazios e se a senha atende aos requisitos de segurança.

7. Tecnologias Utilizadas
Linguagem: Java
Banco de Dados: PostgreSQL
Bibliotecas: JDBC para conexão com o banco de dados, Swing para a interface gráfica.





Manual do Usuário

1. Introdução
Este manual tem como objetivo guiar o usuário na utilização do sistema de biblioteca. Nele, você encontrará instruções sobre como realizar operações comuns, como cadastrar usuários, livros e realizar empréstimos.

2. Acesso ao Sistema
Para acessar o sistema, você deve executar o arquivo JAR correspondente ou iniciar a aplicação via IDE.

3. Funcionalidades
   
3.1 Cadastro de Usuário
Acesse a aba de cadastro.
Preencha os campos: nome, email, senha e CPF.
Clique em "Cadastrar".
Se a operação for bem-sucedida, uma mensagem de confirmação aparecerá.

3.2 Cadastro de Livro
Vá para a aba de livros.
Insira as informações do livro: título, autor e categoria.
Clique em "Cadastrar".
A confirmação aparecerá se tudo ocorrer corretamente.

3.3 Realizar Empréstimo
Acesse a lista de livros.
Selecione o livro desejado.
Preencha as datas de empréstimo e devolução.
Clique em "Realizar Empréstimo".
Uma mensagem de sucesso aparecerá.

3.4 Devolver Livro
Vá para a lista de empréstimos.
Selecione o empréstimo a ser devolvido.
Clique em "Devolver".
O sistema atualizará as informações correspondentes.

4. Erros e Soluções
Erro ao cadastrar usuário: Verifique se todos os campos obrigatórios estão preenchidos corretamente.
Erro ao emprestar livro: Certifique-se de que o livro está disponível.
