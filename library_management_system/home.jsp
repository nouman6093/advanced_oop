<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>

  <style>
    /* Global Styles */
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      margin: 0;
      padding: 0;
    }

    h1 {
      text-align: center;
      color: #333;
    }

    .container {
      max-width: 1100px;
      margin: 20px auto;
      padding: 20px;
      background: white;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    /* Navigation Links */
    .nav-links {
      display: flex;
      justify-content: space-between;
      margin: 20px 0;
      padding: 0;
      list-style: none;
    }

    .nav-links a {
      text-decoration: none;
      color: white;
      background-color: #0056b3;
      padding: 10px 20px;
      border-radius: 5px;
      transition: background-color 0.3s;
    }

    .nav-links a:hover {
      background-color: #003f8a;
    }

    /* Form Styles */
    .form-container {
      max-width: 600px;
      margin: 40px auto;
      background: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    label {
      display: block;
      margin-bottom: 5px;
      color: #333;
      font-weight: bold;
    }

    input {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }

    button {
      display: block;
      width: 100%;
      padding: 10px;
      background-color: #0056b3;
      color: white;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
    }

    button:hover {
      background-color: #003f8a;
    }

    /* Table Styles */
    table {
      width: 90%;
      margin: 20px auto;
      border-collapse: collapse;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    table, th, td {
      border: 1px solid #ddd;
    }

    th, td {
      padding: 10px;
      text-align: center;
    }

    th {
      background-color: #0056b3;
      color: white;
    }

    td a {
      text-decoration: none;
      color: white;
      background-color: #0056b3;
      padding: 5px 10px;
      border-radius: 5px;
    }

    td a:hover {
      background-color: #003f8a;
    }





    body {



      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      margin: 0;
      padding: 0;
    }

    .container {
      width: 30%;
      margin: 100px auto;
      padding: 20px;
      background: #fff;
      border-radius: 8px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
      color: #333;
    }

    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    button {
      width: 100%;
      padding: 10px;
      border: none;
      background: #5cb85c;
      color: white;
      border-radius: 5px;
      cursor: pointer;
    }

    button:hover {
      background: #4cae4c;
    }

    .message {
      text-align: center;
      margin-top: 15px;
    }

    .message a {
      color: #5cb85c;
      text-decoration: none;
    }

    .message a:hover {
      text-decoration: underline;
    }
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f9f9f9;
    }

    .dashboard-container {
      max-width: 1200px;
      margin: auto;
      padding: 20px;
    }

    h1 {
      text-align: center;
      color: #333;
    }

    .dashboard-links {
      display: flex;
      justify-content: space-around;
      margin: 20px 0;
    }

    .dashboard-links a {
      text-decoration: none;
      color: white;
      background-color: #007bff;
      padding: 10px 20px;
      border-radius: 5px;
      font-weight: bold;
    }

    .dashboard-links a:hover {
      background-color: #0056b3;
    }

    .dashboard-content {
      padding: 20px;
      background: white;
      border: 1px solid #ddd;
      border-radius: 10px;
    }

    .dashboard-content h2 {
      color: #444;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Admin Dashboard</h1>
  <ul class="nav-links">
    <li><a href="addBook.jsp">Add Book</a></li>
    <li><a href="viewBooks.jsp">View Books</a></li>
    <li><a href="manageUsers.jsp">Manage Users</a></li>
    <li><a href="logout.jsp">Logout</a></li>
  </ul>
  <div class="content">
    <h2>Welcome to the Library Admin Panel</h2>
    <p>Manage books, users, and library resources efficiently.</p>
  </div>
</div>
</body>
</html>
