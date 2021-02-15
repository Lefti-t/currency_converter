# Currency-converter

A currency converter which allows you to convert multiple currencies from one to another.

The default input currency is Euro.

The default output is USD but can be changed in ``` application-properties -> environment variables ``` as well as the value for the Api key.

The API that was used for this project is from Fixer.io (https://fixer.io/)


## Project setup

1)Install Java in your system(http://www.oracle.com/technetwork/java/javase/downloads/index.html)

2)Install  the latest version of Maven (https://maven.apache.org/download.cgi)

3)Install an IDE like [NetBeans](https://netbeans.org/), [Intellij IDEA](https://www.jetbrains.com/idea/), or [Eclipse](https://eclipse.org/ide/).

4)Clone this repo and run the app.


## Convert

1) open ```src/main/resources/static/input.txt``` and write each one of the amounts you need converted in a new line.

example: 
```
929.19
21.1
7829.9989
92933.8311127
395.8209
```

2) Use Postman to make a GET request to ```http://localhost:8080/convert``` or use your preffered browser to send the request.

If no error has occured the app will response with a message providing the path of the output file which contains the amounts converted to your currency of preference.

