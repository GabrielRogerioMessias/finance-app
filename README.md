# Descrição do Projeto - API de Controle Financeiro

## Visão Geral
Este projeto é uma API sólida e eficiente desenvolvida em Java 17, utilizando o framework Spring Boot, com foco no gerenciamento abrangente de finanças pessoais. Oferece operações CRUD para entidades como despesas, receitas, categorias e usuários, proporcionando uma solução completa para o controle seguro e eficiente das transações financeiras.

## Tecnologias Utilizadas
- **Java 17:** Utilizado para aproveitar as últimas funcionalidades e melhorias de desempenho.
- **Spring Boot:** Framework que fornece uma base sólida para o desenvolvimento de APIs RESTful e integração fácil com outros módulos do Spring.
- **Spring Data JPA:** Facilita a implementação de operações CRUD no banco de dados, utilizando o Spring para mapeamento objeto-relacional eficiente.
- **Spring Security:** Oferece recursos de segurança robustos, garantindo autenticação segura e controle de acesso.
- **MySQL:** Banco de dados relacional escolhido para armazenar dados de maneira estruturada e confiável.
- **Token JWT (JSON Web Token):** Sistema de autenticação baseado em token JWT para garantir a segurança das operações.

## Principais Recursos
1. **Usuários:** CRUD completo para criação, leitura, atualização e exclusão de registros de usuários, com autenticação segura.
2. **Despesas e Receitas:** Operações CRUD para o gerenciamento eficiente de transações financeiras.
3. **Categorias:** CRUD para classificação organizada de despesas e receitas.
4. **Atualização de Saldo:** Sistema automático que mantém o saldo do usuário atualizado com base nas despesas e receitas registradas.
5. **Tratamento de Exceções:** Implementação robusta para o tratamento de exceções, garantindo a estabilidade da aplicação.

## Como Utilizar
1. Clone o repositório.
2. Configure as propriedades do banco de dados no arquivo `application.properties`.
3. Execute a aplicação usando o Spring Boot.

## Conclusão
Este projeto destaca-se como uma solução abrangente para o controle financeiro pessoal. A combinação de tecnologias modernas, implementação cuidadosa de funcionalidades robustas e práticas sólidas de tratamento de exceções resulta em um ambiente confiável para o gerenciamento eficaz e seguro das finanças pessoais.
