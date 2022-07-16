# twtr
<h1 align="center">Twitter Clone for practice</h1>

# MySQL 起動
```
docker run -p 3306:3306 --name mysql_80 -e MYSQL_ROOT_PASSWORD=password -d mysql:8 mysqld --default-authentication-plugin=mysql_native_password
```

# `mysql` コマンド
```
docker exec -it mysql_80 mysql -u root -p
```
パスワードは `password`

その後，`src/resources/init.sql` を実行．
