# Ordering System

> Sistema de pedidos de um totem de autoatendimento de um fastfood blues burger. 

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] implementação de clean code
- [x] implementaçao de kubernetes

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Você tem uma máquina `<Windows / Linux / Mac>`.
* Docker
* Postman ou insomnia
* Jdk 11 ou superior

## 🚀 Instalando ordering-system

Para instalar o ordering-system, siga estas etapas:

Clone o projeto com o git

```
git clone https://github.com/bluesburger/ordering-system.git
```

Instale e inicie o docker no seu sistema operacional: https://docs.docker.com/engine/install/

Acesse o diretório da aplicação


## Rodando o projeto localmente
```
docker compose -f docker-compose.yml up -d --build
```


## <img src="https://skillicons.dev/icons?i=kubernetes" width="30"> Rodando o projeto no Kubernetes

Para rodar no **Kubernetes** siga as seguintes etapas:

#### Instale o Docker

> No Windows, instale o Docker Desktop de acordo com o [manual oficial](https://docs.docker.com/desktop/install/windows-install/) e [habilite o Kubernetes](https://docs.docker.com/desktop/kubernetes/)

> No Linux, instale o docker engine de acordo com o [manual oficial](https://docs.docker.com/engine/install/) e [instale e configure o kubernetes](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)

> No Mac OS, instale o Docker Desktop de acordo com o [manual oficial](https://docs.docker.com/desktop/install/mac-install/) e [instale e habilite o kubernetes](https://docs.docker.com/desktop/kubernetes/)

#### Rode a aplicação

1º Aplique as configurações da aplicação no K8S estando na pasta do projeto
```shell
kubectl -- apply -f .\k8s
```
A inicialização deve levar de 5 a 10 minutos

2º Para abrir o swagger no navegador, obtenha o a porta de saída da aplicação através do comando:

```shell
kubectl get service bluesburguer-api
```
_Caso esteja rodando em alguma Cloud este comando será útil para obter o endereço externo da aplicação_

3º Após a utilização, caso deseje remover todas as configurações da aplicação, poderá executar:
```shell
kubectl delete -f .\k8s\
```


## Rodando no **Amazon Elastic Container Service** 
1. Efetuar build da imagem utilizando o docker compose: `docker compose build`
2. Efetuar push no repositório criado no Elastic Container Registry, através da infraestrutura criada no https://github.com/bluesburger/ordering-system-infra

## ☕ Usando ordering-system

Para usar ordering-system, siga estas etapas:

Acesse a documentação do swagger em http://localhost:30000/swagger-ui/

Importe o link no postman, quando adicionado coloque o localhost e a porta 30000: http://localhost:30000/v2/api-docs

Na documentação em Api voce encontrara exemplos de Json para utilizar no postman

https://bluesburguer.notion.site/Blues-Burger-ddf1fe9e62894a16aa402605b93a9fcc?pvs=4


## 📫 Contribuindo para ordering-system

Para contribuir com ordering-system, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).


## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](LICENSE.md) para mais detalhes.

## Autores
Made with by  [Fernando Marques](https://www.linkedin.com/in/fernando-pozo-marques-junior/), [Marlon Silva](https://www.linkedin.com/in/marlon-silva-43075a184/), [Leandro Araújo](https://www.linkedin.com/in/leandroaraujo-sp), [Quésia Santos
](https://www.linkedin.com/in/quesiasts/)