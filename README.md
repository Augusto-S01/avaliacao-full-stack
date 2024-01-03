# Sistema de Transferências Financeiras

![](ttps://img.shields.io/badge/Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white)
![](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![](https://img.shields.io/badge/Sass-CC6699?style=for-the-badge&logo=sass&logoColor=white)
![](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![](https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)
![](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

## Descrição 

Esse projeto foi desenvolvido para atender a avaliação técnica , que consiste em desenvolver um sistema de agendamento de transferências financeiras. as especificações podem ser encontradas no arquivo [AVALIACAO.md](AVALIACAO.md)
![image](https://github.com/Augusto-S01/avaliacao-full-stack/assets/43907990/5a930f74-8f45-42b9-b04f-07e9f8db157e)

##Temporariamente disponivel 

Atualmente o projeto esta temporariamente no meu servidor para fins de teste, esta disponivel em http://augustosouza.tech


## Tecnologias utilizadas

- Java 17
- Node 20
- Spring Boot 3.2.1
- Angular: 17

## Como executar o projeto

o projeto inteiro foi Dockerizado para facilitar a execução, para isso é necessário ter o docker e o docker-compose instalados na máquina.
para executar o projeto basta executar o comando abaixo na raiz do projeto:

```bash
docker-compose up -d
```

### Decisões de projeto

- O projeto foi dividido em dois módulos, um para o backend e outro para o frontend, isso foi feito para facilitar a manutenção e o deploy do projeto.
- Por conta do escopo do projeto ser pequeno , não foi utilzado um gerenciador de estado no front como o ngrx ou redux
- por conta da baixa complexidade do projeto não foi utilizado um banco de dados relacional, foi utilizado o banco de dados em memória H2
- O projeto foi dockerizado para facilitar a execução e o deploy do projeto

### Melhorias futuras

- Implementar testes unitários e de integração
- Implementar um gerenciador de estado no front
- Implementar um banco de dados relacional
- Implementar um sistema que mensageria para tratar as transferências 
- Melhorar o retorno das mensagens de erro



