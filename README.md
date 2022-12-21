# Desafio Técnico South System

## 1.Objetivo

**Domínio**: Cadastro de **Pautas** e contabilização de **Votos** em **sSessão** por **Associado**.

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

* Cadastrar uma nova pauta;
* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
* Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
* Contabilizar os votos e dar o resultado da votação na pauta.

## 2.Arquitetura da Solução

O serviço é uma API que deverá receber dados de pautas, sessão e votos, validar o cpf do associado e persistir os dados da votação em banco.
No momento do encerramento da sessão será feito o envio de uma mensagem para uma fila.

![alt-text](.images/architecture.png)
