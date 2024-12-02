<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete Book</title>
    <link rel="stylesheet" href="styles.css"> <!-- Add a modern UI CSS -->
</head>
<body>
<h1>Delete Book</h1>

<form action="AdminServlet" method="post">
    <input type="hidden" name="action" value="delete">
    <label>Book ID:</label>
    <input type="number" name="id" required>
    <button type="submit">Delete Book</button>
</form>
</body>
</html>
