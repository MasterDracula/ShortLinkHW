﻿# ShortLinkHW
Итоговое задание "сервис коротких ссылок". Программа сделана как итоговое задание 1 семестра обучения в НИЯУ МИФИ.
Для создания приложения разработал основной интерфейс для работы с приложением в консоли. Все подсказки для пользователя выводятся на экран.
Так же были разработаны классы, для преобразования коротких ссылок, для логирования пользователя и контроля количества использования ссылок и
периода их активности. Инструкция работы с приложением подразумевает использование ответов на вопросы,
которые задает приложение:
1. "Новый/ существующий". 
Если пользователь "новый", то ему будет присвоен соответсвующий UUID. Если пользователь существующий, то система попросит ввести UUID, чтобы
авторизоваться. Если по каким-то причинам UUID утерян, программа предлагает создание нового UUID.
2. "Введите длинную ссылку"
Необходимо ввести ссылку, для преобразования в "короткий" вариант.
3. "Переход по короткой ссылке. Введите короткую ссылку".
Для использования короткой ссылки, которая была сгенерирована ранее, необходимо скопировать и ввести её в указанное поле. 
После этого Вы будете автоматически перенаправлены на сайт, ссылка которого была сокращена.
4. "Хотите создать еще одну короткую ссылку".
Если хотите создать короткую ссылку другого сайта, отвечаем "Да", затем см. п. 2.
Если необходимости в создании короткой ссылки нет. Отвечаем "нет".
Кроме того предусмотрена навигация с использованием цифр после первого запуска. В зависимости от того, какой пункт Вас интересует,
вводите соответсвующую цифру и пользуйтесь инструкциями выше.
5. Для выхода из программы есть соответствующая цифра в меню.
Хотелось бы добавить, что в программе предусмотрено неправильное введение ссылок, уведомление пользователя о том, что ссылка просрочена
или неправильно введены какие-то данные.
