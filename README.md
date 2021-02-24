<h3>Funny currency dynamic</h3>

* Сервис, который отдает рандомную картинку соответствующию движению курса со вчера на сегодня.

* Сравнивается последний курс вчера и последний полученый курс за сегодня. 

* Курсы получаем с внешнего API.

* API с курсами умеет отдавать средний курс за день, что сравнивать более логично, но это дорого :)


***

<b>Внимание!</b>
Для смены базовой валюты отличной от USD, понадобится другой API KEY от внешнего API openexchangerates.org. 
API KEY указанный в настройках, является бесплатным и позволяет получать данные только с базовой валютой USD.

***

<h3>Запуск Docker:</h3>

```
gradlew clean test build
```

```
docker build -t currency .
```

```
docker run -d -p 8080:8080 currency
```

***

<h3>Запуск  Jar:</h3>

```
gradlew clean test build
```

```
java -jar build/libs/currency-0.0.1-SNAPSHOT.jar
```

***

<h3>Используемые API:</h3>

REST API курсов валют - https://docs.openexchangerates.org/  

REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide  
