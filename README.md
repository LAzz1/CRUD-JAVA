# A3 - Sistemas Distribuídos e Mobile

Projeto avaliativo da Universidade Anhembi Morumbi com os princípios CRUD.

Link para o repositório no GitHub
```
https://github.com/LAzz1/CRUD-JAVA.git
```

## ☕ Passo a passo

1. Abra um terminal e navegue até a pasta raiz do projeto;

2. Utilize o seguinte comando para iniciar o servidor:
```
java crudJava.Server
```
3. Em outra aba de terminal, use este comando para iniciar a interface como cliente:
```
java crudJava.Client
```

## ⚙️: Funcionalidades

### 1️⃣ Adicionar funcionário
Informe as seguintes informações e o funcionário será adicionado ao Servidor:
```
ID      - Insira apenas números. Único para cada funcionário.

Nome    - Insira apenas letras. Nomes com números no meio não são computados.

Salário - Insira apenas números. Caso precise informar casas decimais, use "." ao invés de ",".
(Tenha em mente os mínimos salariais legais. Consideramos o mínimo de R$810,00)
```
### 2️⃣ Exibir funcionários

Lista todos os funcionários cadastrados no servidor.

### 3️⃣ Procurar funcionário

Insira um ID de funcionário e o servidor retornará as informações referentes a ele.

### 4️⃣ Deletar funcionário

Insira um ID de funcionário para deletar suas informações do Servidor.

### 5️⃣ Atualizar funcionário

Escolha um funcionário informando seu ID e atualize seu Nome e Salário.
Siga os padrões informados na função 1:
```
Nome    - Insira apenas letras. Nomes com números no meio não são computados.

Salário - Insira apenas números. Caso precise informar casas decimais, use "." ao invés de ",".
(Tenha em mente os mínimos salariais legais. Consideramos o mínimo de R$810,00)
```
### 6️⃣ Procurar funcionários por Salário

Escolha entre as opções informadas:
```
1. Estagiários [R$ 810,00   - R$ 1.400,00]
2. Júnior      [R$ 2.000,00 - R$ 3.500,00]
3. Pleno       [R$ 4.000,00 - R$ 5.000,00]
4. Sênior      [R$ 5.500,00   em   diante]
```

### 0️⃣ Sair

Fecha a interface do cliente, porém todas informações passadas durante a sessão serão salvas no Servidor até ser fechado.

Para iniciar uma nova sessão de cliente, use o mesmo comando:
```
java crudJava.Client
```

## 🔧 Compilação do código

Caso ocorra algum problema e seja necessário recompilar o projeto, use os seguintes comandos em ordem no terminal:

No Windows 🏠
```
javac .\functions\*java
javac .\crudJava\*java
```

No Linux 🐧
```
javac functions/*java
javac crudJava/*java
```
