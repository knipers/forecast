(function () {
  'use strict';

  angular.module('cadastro', [])

      .controller('CidadeController', function ($scope, $http) {
        $scope.deuErro = false;
        $scope.cadastroErro = '';
        $scope.connectionError = false;
        $scope.cidades = [];
        $http({
            method : "GET",
            url : "http://localhost:5151/city"
        }).then(function success(response) {
          $scope.connectionError = false;
          $scope.cidades = response.data;
        }, function error(response) {
          $scope.connectionError = true;
        });
        $scope.cidade = {nome: ''};
        $scope.previsao = {cidade: '', dias: [
          {dt: '', min: '', max: '', day: '', night: '', morn: '', eve: '', weather: '', class: ''}
        ]};

        $scope.weather = [{ en: "Thunderstorm", pt: "Trovoada", class: "fas fa-poo-storm" },
        { en: "Drizzle", pt: "Garoa", class: "cardIcon fas fa-cloud-rain"},
        { en: "Rain", pt: "Chuva", class: "cardIcon fas fa-cloud-showers-heavy"},
        { en: "Snow", pt: "Neve", class: "cardIcon fas fa-snowflake"},
        { en: "Mist", pt: "Névoa", class: "cardIcon fas fa-cloud" },
        { en: "Smoke", pt: "Nebuloso", class: "cardIcon fas fa-cloud" },
        { en: "Haze", pt: "Neblina", class: "cardIcon fas fa-cloud" },
        { en: "Dust", pt: "Redemoinhos de peira", class: "cardIcon fas fa-wind" },
        { en: "Sand", pt: "Areia", class: "cardIcon fas fa-wind" },
        { en: "Ash", pt: "Cinza", class: "cardIcon fas fa-meteor" },
        { en: "Squall", pt: "Tempestades", class: "cardIcon fas fa-poo-storm" },
        { en: "Clear", pt: "Céu limpo", class: "cardIcon fas fa-sun" },
        { en: "Clouds", pt: "Nuvens", class: "cardIcon fas fa-cloud" }];


          //método para adicionar o usuário a lista
          $scope.cadastrar = function () {
            $scope.deuErro = false;
            var city = {name: $scope.cidade.nome}
            console.log(JSON.stringify(city));
            $http.post("http://localhost:5151/city", JSON.stringify(city)).then(function success(response) {
              $scope.cidades.push(response.data);
              $scope.cidade = {nome: ''};
            }, function error(response){
              $scope.cadastroErro = response.data;
              $scope.deuErro = true;
            });
          };


          $scope.excluir = function(id) {
            var url = "http://localhost:5151/city/"+id;
            $http.delete(url).then(
              function success(response) {
                $scope.cidades = arrayRemove(id);
              },
              function error(response){
                console.log(response);
              }
            );
            console.log("excluir " + id);
          };

          function arrayRemove(id) {
            return $scope.cidades.filter(function(x){
              return x.id != id;
            })
          }

          $scope.buscarPrevisao = function(id) {
            $scope.previsao = {cidade: '', dias: [
              {dt: '', min: '', max: '', day: '', night: '', morn: '', eve: '', weather: '', class: ''}
            ]};            
            var urlForecast = "http://localhost:5151/forecast/" +id;
            $http({
              method : "GET",
              url : urlForecast
          }).then(function success(response) { 
            var previsao = {cidade: response.data[0].cityName, dias: [] };
            var dados = response.data;
            for (var i = 0; i < dados.length; i++) {
              var data = formatDate(new Date(dados[i].date));
              var weather = returnWeatherPortuguese($scope.weather, dados[i].weather);
              var dia = {dt: data,
                          min:  dados[i].tempMin, 
                          max:  dados[i].tempMax, 
                          day:  dados[i].tempDay, 
                          night:  dados[i].tempNight, 
                          morn:  dados[i].tempMorn, 
                          eve:  dados[i].tempEve,
                          weather: weather[0].pt,
                          class:  weather[0].class };
              previsao.dias.push(dia);
            }
            console.log(previsao);
            $scope.previsao = previsao;
            console.log(response) }, function error(response) { console.log(response) }) };

        });

        function formatDate(data) {
          var dia  = data.getDate().toString(),
              diaF = (dia.length == 1) ? '0'+dia : dia,
              mes  = (data.getMonth()+1).toString(),
              mesF = (mes.length == 1) ? '0'+mes : mes,
              anoF = data.getFullYear();
          return diaF+"/"+mesF+"/"+anoF;
        }

        function returnWeatherPortuguese(arr, weather) {
          return arr.filter(function(x){
            return x.en == weather;
          })
        }

})();