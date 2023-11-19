### Ordering System 


> Sistema de pedidos de um totem de autoatendimento de um fastfood blues burger.

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [ ] implementação de clean code
- [ ] implementaçao de kubernets

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
No terminal execute
```
docker compose -f docker-compose.yml up -d --build
```

Ou para rodar no **Kubernetes** siga as seguintes etapas:

No terminal execute
```
kubectl apply -f .\k8s\base\configmap.yml
kubectl apply -f .\k8s\base\secrets-opaque.yml
kubectl apply -f .\k8s\base\metrics.yml

kubectl apply -f .\k8s\volumes\ordering-system-pv.yml
kubectl apply -f .\k8s\volumes\ordering-system-pvc.yml

kubectl apply -f .\k8s\pods\ordering-system-database.yml
kubectl apply -f .\k8s\svc\svc-ordering-database.yml

kubectl apply -f .\k8s\deployment-ordering-system-api.yml

kubectl apply -f .\k8s\svc\svc-ordering-api.yml

kubectl apply -f .\k8s\hpa-ordering-system-api.yml

kubectl apply -f .\k8s\observability\efk\elastic\elastic-service.yml
kubectl apply -f .\k8s\observability\efk\elastic\elastic-pod.yml
kubectl apply -f .\k8s\observability\efk\fluentd\fluent-configmap.yml
kubectl apply -f .\k8s\observability\efk\fluentd\fluent-pod.yml
kubectl apply -f .\k8s\observability\efk\kibana\kibana-service.yml
kubectl apply -f .\k8s\observability\efk\kibana\kibana-pod.yml
```

Para conferir que os pods estão rodando execute no terminal
```
kubectl get pods
```

E para conferir que as services estão rodando, execute no terminal
```
kubectl get svc
```

## ☕ Usando ordering-system

Para usar ordering-system, siga estas etapas:

Acesse a documentação do swagger

```
localhost:8181/swagger-ui.html
```
Importa no postman

```
http://localhost:8181/v2/api-docs
```

Adicione comandos de execução e exemplos que você acha que os usuários acharão úteis. Fornece uma referência de opções para pontos de bônus!

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
Made with by  [Fernando Marques](https://www.linkedin.com/in/fernando-pozo-marques-junior/), [Marlon Silva](https://www.linkedin.com/in/marlon-silva-43075a184/),[Leandro Araújo](https://www.linkedin.com/in/leandroaraujo-sp), [Quésia Santos
](https://www.linkedin.com/in/quesiasts/)