# Documentação do Sistema de Cadastro

Micro-serviço para o desenvolvimento do sistema de cadastro da aplicação do prontuário clínico

## Como Rodar a Aplicação

### Pré-requisitos

Antes de executar a aplicação, certifique-se de que você tem os seguintes pré-requisitos instalados:

- **Java 17** ou superior
- **Maven** 3.6.0 ou superior
- **PostgreSQL** 16

### Passo a Passo

1. **Clone o Repositório**

   Abra o terminal e clone o repositório usando o seguinte comando:

   ```bash
   git clone https://github.com/FSW-FPB/sistema-cadastro.git
   ```

2. **Configurar Banco de Dados**

   Crie um banco de dados no PostgreSQL com algum nome (sugerimos **"cadastro-prontuario"**).

   Atualize as credenciais e o nome do banco de dados no arquivo application.properties localizado em src/main/resources:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=usuario
   spring.datasource.password=senha
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```

3. **Construir e rodar o projeto**

   Execute o comando Maven para compilar o projeto e baixar as dependências:

   ```bash
    mvn clean install
   ```

   Após a construção bem-sucedida, você pode rodar a aplicação com o seguinte comando:

   ```bash
    mvn spring-boot:run
   ```

4. **Acessar a Aplicação**

   Após a aplicação ser iniciada, você pode acessá-la no navegador em:

   ```bash
    http://localhost:8080
   ```

   Para visualizar os endpoints da API, acesse:

   ```bash
    http://localhost:8080/swagger-ui/index.html
   ```

### Observação

A estrutura do projeto utiliza do JPA para o gerenciamento da aplicação, a aplicação deve rodar utilizando 1 banco pré-criado e **APENAS** no formato **SQL**!!

## Estrutura do Banco de Dados

O sistema de cadastro possui uma tabela de **Dados Pessoais** e três tabelas que consomem essa tabela:

### Tabela: Dados Pessoais (`tb_dados_pessoais`)

| Coluna            | Tipo      | Descrição                              |
| ----------------- | --------- | -------------------------------------- |
| `id`              | Long      | Identificador único dos dados pessoais |
| `nome`            | String    | Nome completo do paciente              |
| `cpf`             | String    | CPF do paciente                        |
| `telefone`        | String    | Telefone do paciente                   |
| `cep`             | String    | CEP do endereço do paciente            |
| `data_nascimento` | LocalDate | Data de nascimento do paciente         |
| `status`          | String    | Status do paciente (ativo/inativo)     |

### Tabela: Atendente (`tb_atendente`)

| Coluna              | Tipo   | Descrição                                |
| ------------------- | ------ | ---------------------------------------- |
| `id`                | Long   | Identificador único do atendente         |
| `email`             | String | Email do atendente                       |
| `senha`             | String | Senha do atendente                       |
| `id_dados_pessoais` | Long   | Referência ao registro de dados pessoais |

### Tabela: Médico (`tb_medico`)

| Coluna              | Tipo   | Descrição                                |
| ------------------- | ------ | ---------------------------------------- |
| `id`                | Long   | Identificador único do médico            |
| `crm`               | String | CRM do médico                            |
| `especialidade`     | String | Especialidade do médico                  |
| `email`             | String | Email do médico                          |
| `senha`             | String | Senha do médico                          |
| `id_dados_pessoais` | Long   | Referência ao registro de dados pessoais |

### Tabela: Paciente (`tb_paciente`)

| Coluna              | Tipo   | Descrição                                |
| ------------------- | ------ | ---------------------------------------- |
| `id`                | Long   | Identificador único do paciente          |
| `tipoSanguineo`     | String | Tipo sanguíneo do paciente               |
| `alergia`           | String | Alergias do paciente                     |
| `doencasCronicas`   | String | Doenças crônicas do paciente             |
| `email`             | String | Email do paciente                        |
| `senha`             | String | Senha do paciente                        |
| `id_dados_pessoais` | Long   | Referência ao registro de dados pessoais |

## Endpoints

Os endpoints do sistema estão disponíveis em:

```bash
http://localhost:8080/swagger-ui/index.html
```

### Exemplos de Endpoints

- `GET /pacientes`: Lista todos os pacientes
- `POST /pacientes`: Cria um novo paciente
- `PUT /pacientes/{id}`: Atualiza os dados de um paciente específico
- `DELETE /pacientes/{id}`: Remove um paciente específico

- `GET /atendentes`: Lista todos os atendentes
- `POST /atendentes`: Cria um novo atendente
- `PUT /atendentes/{id}`: Atualiza os dados de um atendente específico
- `DELETE /atendentes/{id}`: Remove um atendente específico

- `GET /medicos`: Lista todos os médicos
- `POST /medicos`: Cria um novo médico
- `PUT /medicos/{id}`: Atualiza os dados de um médico específico
- `DELETE /medicos/{id}`: Remove um médico específico

- `GET /dados-pessoais`: Lista todos os dados pessoais
- `POST /dados-pessoais`: Cria novos dados pessoais
- `PUT /dados-pessoais/{id}`: Atualiza dados pessoais específicos
- `DELETE /dados-pessoais/{id}`: Remove dados pessoais específicos

## Membros do Projeto

| Nome        | Foto                                                                                                                   | Perfil                                         |
| ----------- | ---------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------- |
| **Reed0ne** | <img src="https://avatars.githubusercontent.com/u/115191418?v=4" width="45" height="45" style="border-radius: 50%;" /> | [Perfil do GitHub](https://github.com/Reed0ne) |
| **Gustahx** | <img src="https://avatars.githubusercontent.com/u/127751205?v=4" width="45" height="45" style="border-radius: 50%;" /> | [Perfil do GitHub](https://github.com/Gustahx) |
