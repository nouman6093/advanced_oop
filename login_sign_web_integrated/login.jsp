<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f3f6f7;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 35%;
            margin: 80px auto;
            padding: 25px;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 12px 0;
            border: 1px solid #bdc3c7;
            border-radius: 8px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            background: #3498db;
            color: white;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #2980b9;
        }

        .message {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
        }

        .message a {
            color: #3498db;
            text-decoration: none;
        }

        .message a:hover {
            text-decoration: underline;
        }
    </style>
    <title>Login</title>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <input type="text" name="username" placeholder="Enter username" required>
        <input type="password" name="password" placeholder="Enter password" required>
        <button type="submit">Login</button>
    </form>
    <div class="message">
        Don't have an account? <a href="index.jsp">Sign Up</a>
    </div>
</div>
</body>
</html>
