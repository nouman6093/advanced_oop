<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f4f6f8;
      margin: 0;
      padding: 0;
    }

    .container {
      width: 40%;
      margin: 100px auto;
      padding: 30px;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    h1 {
      color: #2c3e50;
      font-size: 32px;
      margin-bottom: 20px;
    }

    p {
      font-size: 18px;
      color: #34495e;
    }

    .username {
      font-size: 22px;
      color: #3498db;
      font-weight: bold;
    }

    .button {
      display: inline-block;
      margin-top: 30px;
      padding: 12px 20px;
      font-size: 16px;
      background-color: #3498db;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .button:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Welcome to Your Dashboard</h1>
  <p>Hello, <span class="username"><%= session.getAttribute("username") %></span>!</p>
  <a href="index.jsp" class="button">Logout</a>
</div>
</body>
</html>
