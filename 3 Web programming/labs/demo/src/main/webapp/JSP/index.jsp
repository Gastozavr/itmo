<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <title>Hello, Batman!</title>
    <script src="JS/jquery-3.7.1.min.js"></script>
    <script src="https://www.desmos.com/api/v1.9/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6"></script>
    <link rel="icon" href="img/icon.png" type="image/png">
</head>

<body>
<header>
    <div class="game-title">
        <span>Hit<br>the<br>Batman</span>
    </div>
    <a href="https://github.com/Gastozavr">
        <img alt="Logo" src="img/logo.jpg">
    </a>
    <div class="student-info">
        <span>Schmunk Andrew P3208<br>Variant 274</span>
    </div>
</header>
<table class="all-tables">
    <tr class="graph-data">
        <td>
            <div id="calculator"></div>
        </td>
        <td>
            <form method="get" class="coordinates">
                <div class="x-buttons">
                    X:
                    <label>
                        <button class="x-button" type="button" name="x" data-value="-5">-5</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="-4">-4</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="-3">-3</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="-2">-2</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="-1">-1</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="0">0</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="1">1</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="2">2</button>
                    </label>
                    <label>
                        <button class="x-button" type="button" name="x" data-value="3">3</button>
                    </label>
                </div>
                <div class="Y-text" id="Y">
                    <label>
                        Y:
                        <input id="Y-text" class="Y-text-input" type="text" name="y" placeholder="от -3 до 5"
                               maxlength="14" required/>
                    </label>
                </div>
                <div class="R-radio" id="R">
                    R:
                    <label>
                        <input type="radio" name="r" value="1" required>1
                    </label>
                    <label>
                        <input type="radio" name="r" value="2" required>2
                    </label>
                    <label>
                        <input type="radio" name="r" value="3" required>3
                    </label>
                    <label>
                        <input type="radio" name="r" value="4" required>4
                    </label>
                    <label>
                        <input type="radio" name="r" value="5" required>5
                    </label>
                </div>
                <button id="submit" type="submit">Отправить</button>
                <button id="clear" type="reset">Очистить таблицу</button>
            </form>
        </td>
    </tr>
</table>
<table id="result" class="table-result">
    <tr class="table-header">
        <th scope="col">X</th>
        <th scope="col">Y</th>
        <th scope="col">R</th>
        <th scope="col">Текущее время</th>
        <th scope="col">Время исполнения</th>
        <th scope="col">Результат попадания</th>
    </tr>
</table>
</body>