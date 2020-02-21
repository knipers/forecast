# forecast
A aplicação foi desenvolvida para retornar a previsão do tempo de uma cidade, para isto deve ser permitido cadastrar novas cidades.
Foram criados dois endpoints:

1. /city - para fazer o CRD CREATE/READ/DELETE da cidade.

2. /forecast/id - para fazer a leitura da previsão do tempo de hoje e +4 dias.

É uma aplicação REST simples que utiliza o MongoDB como banco de dados para armazenar a cidade, no caso a previsão do tempo sempre busca atualizada.
Aplicação criada dentro dos padrões SPRING REST, com controllers, services, dto's e beans.
Também existem 4 testes unitários para garantir o funcionamento da rotina de cadastrar cidade e de consulta do forecast.

### Para rodar:
É necessario ter o maven instalado bem como acesso a internet, pois a API de consulta é externa.
BackEnd:
> mvn install

depois:
> mvn spring-boot:run

Após rodar o backend é só ir na pasta ForecastWeb e abrir o index.html
O index utiliza o AngularJS para fazer as chamadas de API e Serealizar os objetos de retorno.