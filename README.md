# *Semeando 🌱*

## *Visão Geral do Projeto*
O *Semeando 🌱* é um aplicativo mobile criado para educar adolescentes e jovens-adultos sobre energia renovável e práticas sustentáveis. Inspirado no modelo de aprendizado do Duolingo, o aplicativo utiliza quizzes interativos, gamificação e recompensas reais para engajar e conscientizar os usuários sobre sustentabilidade e economia de energia.

### *Público-Alvo*
Adolescentes e jovens-adultos (Gerações Z e Alpha) interessados em práticas sustentáveis.

### *Temas do Quiz*
- Fontes de Energia Renovável
- Redução de Consumo Doméstico
- Transporte Sustentável
- Impacto Ambiental
- Mudanças Climáticas

### *Estrutura do Quiz*
- Cada nível contém *10 perguntas* no formato de múltipla escolha ou verdadeiro/falso.

### *Gamificação e Incentivo*
- *Pontuação*: Baseada em níveis atingidos, velocidade de progresso e streaks.
- *Recompensas*: Os usuários acumulam pontos e medalhas, resgatáveis em benefícios reais, como descontos e ingressos.
- *Classificação*: Rankings mensais por região.

---

## *Integrantes do Grupo*
- *Felipe Arcanjo Matos dos Santos* - RM 554018
- *Gustavo Rabelo Frere* - RM 553326
- *Marcelo Vieira Junior* - RM 553640

---

## *Requisitos Implementados*

### *Autenticação de Usuário*
- Login integrado ao *Firebase Authentication*.
- Registro de novos usuários.
- Logout de contas autenticadas.

### *CRUD para Desenvolvedores*
- Inserção de novos desenvolvedores via *Firebase Realtime Database*.
- Listagem de desenvolvedores cadastrados.
- Edição e exclusão de desenvolvedores (com proteção para registros imutáveis).

---

## *Passo-a-Passo para Executar o Projeto*

### *1. Pré-requisitos*
Certifique-se de ter instalado:
- *Android Studio* (versão mais recente recomendada).
- *Java Development Kit (JDK)* 8 ou superior.
- Um dispositivo Android ou um emulador configurado no Android Studio.
- Conexão com a internet.

---

### *2. Configuração do Firebase*

#### *2.1. Criar um projeto no Firebase*
1. Acesse o [console do Firebase](https://console.firebase.google.com/).
2. Clique em *Adicionar Projeto* e configure conforme necessário.
3. Habilite *Authentication* e escolha *E-mail/Senha* como método de login.
4. Habilite o *Realtime Database*:
    - Defina as regras de leitura/escrita como abaixo para o desenvolvimento:
      json
      {
        "rules": {
          ".read": "auth != null",
          ".write": "auth != null"
        }
      }
      

#### *2.2. Adicionar o Firebase ao Projeto Android*
1. Baixe o arquivo google-services.json no console do Firebase.
2. Cole o arquivo na pasta app/ do projeto.

#### *2.3. Configuração no build.gradle*
Adicione as seguintes dependências ao projeto:

gradle
// build.gradle (nível do projeto)
dependencies {
    classpath 'com.google.gms:google-services:4.3.15'
}

// build.gradle (nível do módulo)
apply plugin: 'com.google.gms.google-services'

dependencies {
    implementation 'com.google.firebase:firebase-auth-ktx'
    implementation 'com.google.firebase:firebase-database-ktx'
}


### 3. Execução no Android Studio
1. Abra o projeto no Android Studio.
2. Certifique-se de selecionar um dispositivo/emulador.
3. Clique em Run ou pressione Shift + F10.

## 4. Funcionalidades Implementadas

### Autenticação
- *Login e Registro*: Criação de conta e autenticação utilizando o Firebase Authentication.
- *Logout*: Permite que o usuário saia de sua conta de forma segura e volte à tela inicial.

### CRUD de Desenvolvedores
#### Inserção de Dados:
- Adição de novos desenvolvedores com os campos:
    - RM (Registro do Aluno)
    - Nome
    - Cidade
    - Instituição
    - GitHub
- Validações implementadas para garantir que todos os campos obrigatórios sejam preenchidos.

#### Listagem:
- Todos os desenvolvedores cadastrados são exibidos em uma lista interativa.
- Os dados são carregados diretamente do Firebase Realtime Database.

#### Edição de Dados:
- Opção para editar informações de um desenvolvedor específico, acessível através de um menu contextual no card do desenvolvedor.
- Desenvolvedores protegidos (pré-cadastrados) não podem ser editados.

#### Exclusão de Dados:
- Permite excluir registros de desenvolvedores diretamente pelo menu contextual.
- Desenvolvedores protegidos não podem ser removidos.

## Estrutura do Projeto

### Páginas Implementadas
- *LoginPageActivity*: Tela de login para autenticação do usuário.
- *RegisterPageActivity*: Tela de registro para novos usuários.
- *MainActivity*: Tela inicial que apresenta quizzes e informações sobre amigos.
- *DevsPageActivity*: Tela para gerenciamento de desenvolvedores (CRUD).
- *UserPageActivity*: Tela de perfil do usuário, permitindo edições básicas.

---

### Estrutura de Diretórios

plaintext
semeandoapp/
├── manifests/
│   ├── AndroidManifest.xml
├── kotlin+java/
│   ├── com.example.semeandoapp/
│   │   ├── activities/
│   │   │   ├── DevsPageActivity.kt
│   │   │   ├── LoginPageActivity.kt
│   │   │   ├── MainActivity.kt
│   │   │   ├── RegisterPageActivity.kt
│   │   │   ├── SplashScreenActivity.kt
│   │   │   ├── UserPageActivity.kt
│   │   ├── adapters/
│   │   │   ├── DevAdapter.kt
│   │   ├── models/
│   │   │   ├── Dev.kt
│   │   ├── utils/
│   │       ├── MenuNavigation.kt
├── res/
│   ├── layout/
│   │   ├── activity_login.xml
│   │   ├── activity_register.xml
│   │   ├── activity_main.xml
│   │   ├── activity_devs.xml
│   │   ├── activity_user.xml
│   │   ├── dialog_add_dev.xml
│   ├── drawable/
│   │   ├── header_bg.png
│   │   ├── user_placeholder.png
│   │   ├── lipe_img.png
│   │   ├── celo_img.png
│   │   ├── gus_img.png
│   │   ├── world_img.png
│   │   ├── solar_energy.png
│   │   ├── co2_img.png
│   │   ├── claudinha_avatar.png
│   │   ├── ze_avatar.png
│   │   ├── manu_avatar.png
│   │   ├── arrow_icon.png
│   ├── values/
│   │   ├── colors.xml
│   │   ├── nav_item_color.xml
│   │   ├── strings.xml
│   │   ├── styles.xml
│   ├── menu/
│   │   ├── bottom_nav_menu.xml
│   │   ├── dev_menu.xml
├── Gradle Scripts/
│   ├── build.gradle (Module: app)
│   ├── build.gradle (Project)
│   ├── settings.gradle

## Vídeo de Demonstração

Confira a demonstração completa do aplicativo no link: [Vídeo de Demonstração](https://youtu.be/8F2fMrP251Y).

---

## Notas Finais

- Para dúvidas ou sugestões, entre em contato com os integrantes do projeto:
    - Felipe Arcanjo Matos dos Santos - RM 554018
    - Gustavo Rabelo Frere - RM 553326
    - Marcelo Vieira Junior - RM 553640

- Colabore no repositório do projeto para melhorias contínuas.

---
